package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.beans.NewsBeans;

import org.xutils.x;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<NewsBeans.NewslistBean> mDataBeanList;//保存数据源的列表
    private NewsAdapter.OnItemClickListener mListener;//初始化监听接口

    public NewsAdapter(Context context, List<NewsBeans.NewslistBean> dataBeanList, OnItemClickListener listener) {
        this.mContext = context;
        this.mDataBeanList = dataBeanList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.news_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, final int position) {

        holder.NewsTitle.setText(mDataBeanList.get(position).getTitle());
        holder.NewsDescription.setText(mDataBeanList.get(position).getDescription());
        holder.NewsCtime.setText(mDataBeanList.get(position).getCtime());
        if (!mDataBeanList.get(position).getPicUrl().isEmpty()){
            x.image().bind(holder.NewsImg,"http://env.people.com.cn/"+mDataBeanList.get(position).getPicUrl());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView NewsImg;
        private TextView NewsTitle,NewsDescription,NewsCtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NewsImg=itemView.findViewById(R.id.news_img);
            NewsTitle=itemView.findViewById(R.id.news_title);
            NewsDescription=itemView.findViewById(R.id.news_description);
            NewsCtime=itemView.findViewById(R.id.news_ctime);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
