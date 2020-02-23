package com.example.demo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DialogRvAdapter extends RecyclerView.Adapter<DialogRvAdapter.ViewHolder> {


    @NonNull
    @Override
    public DialogRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DialogRvAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
