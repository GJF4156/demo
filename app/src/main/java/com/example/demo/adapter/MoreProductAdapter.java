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
import com.example.demo.beans.Product;

import org.xutils.x;

import java.util.List;

public class MoreProductAdapter extends RecyclerView.Adapter<MoreProductAdapter.ViewHolder> {

    private Context mContext;
    private List<Product.ProductInfoListBean> mProducts;
    private MoreProductAdapter.OnItemClickListener mListener;

    public MoreProductAdapter(Context context, List<Product.ProductInfoListBean> products, OnItemClickListener listener) {
        this.mContext = context;
        this.mProducts = products;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public MoreProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreProductAdapter
                .ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.more_product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoreProductAdapter.ViewHolder holder, int position) {
        holder.title.setText(mProducts.get(position).getProduct().getDescription());
        holder.price.setText("￥"+String.valueOf(mProducts.get(position).getProduct().getPrice()));
        holder.sold.setText("销量"+String.valueOf(mProducts.get(position).getProduct().getSold()));
        x.image().bind(holder.image, mProducts.get(position).getImagesPath().get(0).getImgPath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, price,sold;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            price = itemView.findViewById(R.id.product_price);
            image = itemView.findViewById(R.id.product_img);
            sold=itemView.findViewById(R.id.product_sold);
        }
    }

    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
