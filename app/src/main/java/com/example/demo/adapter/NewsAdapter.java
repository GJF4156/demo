package com.example.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.activity.ContentActivity;
import com.example.demo.beans.NewsBeans;

import org.xutils.x;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<NewsBeans.NewslistBean> mDataBeanList;//保存数据源的列表
    private boolean mFlag;
    private Intent intent;

    public NewsAdapter(Context context, List<NewsBeans.NewslistBean> dataBeanList,boolean flag) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        this.mFlag = flag;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsAdapter.ViewHolder(mInflater.inflate(R.layout.news_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, final int position) {
        holder.NewsTitle.setText(mDataBeanList.get(position).getTitle());
        holder.NewsDescription.setText(mDataBeanList.get(position).getDescription());
        holder.NewsCtime.setText(mDataBeanList.get(position).getCtime());
        if (!mDataBeanList.get(position).getPicUrl().isEmpty()){
            x.image().bind(holder.NewsImg,"http://env.people.com.cn/"+mDataBeanList.get(position).getPicUrl());
        }else {
            x.image().bind(holder.NewsImg,"http://seopic.699pic.com/photo/50054/5187.jpg_wh1200.jpg");
        }

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = mDataBeanList.get(position).getUrl();
                intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("type", "8");
                intent.putExtra("Url", Url);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        if (mFlag) {
            holder.btnTop.setVisibility(View.GONE);
            holder.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.btnTop.setVisibility(View.VISIBLE);
            holder.btnDelete.setVisibility(View.GONE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSlideListener.onDel(holder.getAdapterPosition());
            }
        });

        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSlideListener) {
                    mOnSlideListener.onTop(holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return null != mDataBeanList ? mDataBeanList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView NewsImg;
        private TextView NewsTitle,NewsDescription,NewsCtime;
        private LinearLayout content;
        Button btnDelete;
        Button btnTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnTop = itemView.findViewById(R.id.btnTop);
            content=itemView.findViewById(R.id.content);
            NewsImg=itemView.findViewById(R.id.news_img);
            NewsTitle=itemView.findViewById(R.id.news_title);
            NewsDescription=itemView.findViewById(R.id.news_description);
            NewsCtime=itemView.findViewById(R.id.news_ctime);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
        void onLongClick(int position);
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSlideListener {
        void onDel(int position);

        void onTop(int position);
    }

    private NewsAdapter.onSlideListener mOnSlideListener;

    public NewsAdapter.onSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public void setOnDelListener(NewsAdapter.onSlideListener mOnDelListener) {
        this.mOnSlideListener = mOnDelListener;
    }
}
