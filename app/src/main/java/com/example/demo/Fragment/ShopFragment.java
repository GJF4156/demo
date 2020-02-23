package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.Utils.GlideImageLoader;
import com.example.demo.adapter.ProductAdapter;
import com.example.demo.beans.Product;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements OnBannerListener {
    private Banner banner;
    List<String> images = new ArrayList<>();   //定义图片集合
    List<Product> products = new ArrayList<>();
    private RecyclerView productRv;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        productRv = view.findViewById(R.id.product_Rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        productRv.setLayoutManager(layoutManager);
        getData();
        productRv.setAdapter(new ProductAdapter(products, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
            }
        }, getActivity()));
        images.add("http://seopic.699pic.com/photo/40005/1749.jpg_wh1200.jpg");
        images.add("http://seopic.699pic.com/photo/50059/1442.jpg_wh1200.jpg");
        images.add("http://seopic.699pic.com/photo/50054/5187.jpg_wh1200.jpg");  //图片路径
        banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(images);  //设置banner中显示图片
        banner.setDelayTime(3000);
        banner.setOnBannerListener(this);
        banner.start();  //设置完毕后调用
        return view;
    }

    private void getData() {
        for (int i = 0; i < 6; i++) {
            Product product = new Product();
            product.setImgUrl("http://seopic.699pic.com/photo/40005/1749.jpg_wh1200.jpg");
            product.setDescription("商品一商品一");
            product.setPrice("￥23.00");
            product.setSell("已售0");
            products.add(product);
        }
        System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈\n" + products.get(1).toString());
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }
}
