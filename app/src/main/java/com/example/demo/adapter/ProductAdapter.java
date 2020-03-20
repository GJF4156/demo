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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHoider>{

    private List<Product.ProductInfoListBean> mProducts;
    private OnItemClickListener mListener;
    private Context mContext;

    public ProductAdapter(List<Product.ProductInfoListBean> products, OnItemClickListener listener, Context context) {
        this.mProducts = products;
        this.mListener = listener;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHoider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHoider(LayoutInflater.from(mContext).inflate(R.layout.product_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoider holder, int position) {
        holder.productDescription.setText(mProducts.get(position).getProduct().getDescription());
        holder.price.setText("￥"+String.valueOf(mProducts.get(position).getProduct().getPrice()));
        holder.sell.setText("已售"+String.valueOf(mProducts.get(position).getProduct().getSold()));
        x.image().bind(holder.productImg,mProducts.get(position).getImagesPath().get(0).getImgPath());
        System.out.println(mProducts.get(position).getImagesPath().get(0).getImgPath());
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

    public class ViewHoider extends RecyclerView.ViewHolder {

        private TextView productDescription,price,sell;
        private ImageView productImg;

        public ViewHoider(@NonNull View itemView) {
            super(itemView);
            productDescription=itemView.findViewById(R.id.product_description);
            price=itemView.findViewById(R.id.product_price);
            sell=itemView.findViewById(R.id.product_sell);
            productImg=itemView.findViewById(R.id.product_img);
        }
    }
    //暴露监听器接口
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
