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
import com.example.demo.beans.ShoppingCartBean;

import org.xutils.x;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.ViewHolder>{

    private Context mContext;
    private List<ShoppingCartBean> mList;
    private OnItemClickListener mListener;

    public PayAdapter(Context context, List<ShoppingCartBean> list, OnItemClickListener listener) {
        this.mContext = context;
        this.mList = list;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PayAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.pay_order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int a=position+1;
        x.image().bind(holder.proImage,mList.get(position).getImageUrl());
        holder.proTitle.setText(mList.get(position).getShoppingName());
        holder.proPrice.setText(String.valueOf(mList.get(position).getPrice()));
        holder.orderTitle.setText("订单"+a);
        holder.proCount.setText("x"+String.valueOf(mList.get(position).getCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderTitle,proTitle,proPrice,proCount;
        private ImageView proImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderTitle=itemView.findViewById(R.id.order_title);
            proTitle=itemView.findViewById(R.id.pro_title);
            proPrice=itemView.findViewById(R.id.pro_price);
            proCount=itemView.findViewById(R.id.pro_count);
            proImage=itemView.findViewById(R.id.pro_img);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }


}
