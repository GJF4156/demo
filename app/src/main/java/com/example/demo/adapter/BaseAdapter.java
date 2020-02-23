package com.example.demo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    /**
     * 构造函数
     */
    public BaseAdapter() {
    }

    /**
     * 加载布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 绑定组件数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {

    }

    /**
     * 展示列数
     * @return
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        /**
         * 组件初始化
         * @param itemView
         */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
