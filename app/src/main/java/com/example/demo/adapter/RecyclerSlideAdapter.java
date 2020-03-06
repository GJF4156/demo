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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.activity.ContentActivity;
import com.example.demo.beans.NewsBeans;
import com.example.demo.beans.NewsData;

import org.xutils.x;

import java.util.List;

public class RecyclerSlideAdapter extends RecyclerView.Adapter<RecyclerSlideAdapter.Slide> {
    private Context context;
    private LayoutInflater mInflater;
    private List<NewsData> mDatas;
    private boolean mFlag;
    private Intent intent;

    public RecyclerSlideAdapter(Context context, List<NewsData> mDatas, boolean flag) {
        this.context = context;
        this.mDatas = mDatas;
        this.mFlag = flag;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Slide onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Slide(mInflater.inflate(R.layout.news_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final Slide holder, final int position) {
        holder.NewsTitle.setText(mDatas.get(position).getTitle());
        holder.NewsDescription.setText(mDatas.get(position).getDescription());
        holder.NewsCtime.setText(mDatas.get(position).getCtime());
        if (!mDatas.get(position).getPicUrl().isEmpty()) {
            x.image().bind(holder.NewsImg, "http://env.people.com.cn/" + mDatas.get(position).getPicUrl());
        } else {
            x.image().bind(holder.NewsImg, "http://seopic.699pic.com/photo/50054/5187.jpg_wh1200.jpg");
        }
        //item的长按事件
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
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

        //删除按钮的点击事件
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 如果删除时，不适用adapter.notifyItemRemoved(position),则删除没有动画效果
                 * 如果想让侧滑菜单同时关闭，需要同时调用(CstSwipeDelMenu)holder.itemView).quickClose();
                 */
                mOnSlideListener.onDel(holder.getAdapterPosition());
            }
        });
        //注意事项，设置item点击，不能对真个holder.itemView设置，只能对View设置
        //item的单击事件
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Url = mDatas.get(position).getUrl();
                intent = new Intent(context, ContentActivity.class);
                intent.putExtra("type", "8");
                intent.putExtra("Url", Url);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        //置顶
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnSlideListener) {
                    mOnSlideListener.onTop(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSlideListener {
        void onDel(int position);

        void onTop(int position);
    }

    private onSlideListener mOnSlideListener;

    public onSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public void setOnDelListener(onSlideListener mOnDelListener) {
        this.mOnSlideListener = mOnDelListener;
    }

    public class Slide extends RecyclerView.ViewHolder {
        Button btnDelete;
        Button btnTop;
        private ImageView NewsImg;
        private TextView NewsTitle, NewsDescription, NewsCtime;
        private LinearLayout content;

        public Slide(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnTop = itemView.findViewById(R.id.btnTop);
            NewsImg = itemView.findViewById(R.id.news_img);
            NewsTitle = itemView.findViewById(R.id.news_title);
            NewsDescription = itemView.findViewById(R.id.news_description);
            NewsCtime = itemView.findViewById(R.id.news_ctime);
        }
    }
}