package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.beans.Product;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private Context mContext;
    private List<Product.ProductInfoListBean> mList;
    private OnItemClickListener mListener;

    public BaseAdapter(Context context, List<Product.ProductInfoListBean> list, OnItemClickListener listener) {
        this.mContext = context;
        this.mList = list;
        this.mListener = listener;
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
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.base_item,parent,false));
    }

    /**
     * 绑定组件数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {
        holder.description.setText(mList.get(position).getProduct().getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });

    }

    /**
     * 展示列数
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView description;

        /**
         * 组件初始化
         * @param itemView
         */

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description=itemView.findViewById(R.id.description);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
