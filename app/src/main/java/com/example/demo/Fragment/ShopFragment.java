package com.example.demo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentShopP;
import com.example.demo.Fragment.Presenter.impl.FragmentShopPImpl;
import com.example.demo.Fragment.View.IFragmentShopV;
import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.Utils.GlideImageLoader;
import com.example.demo.activity.ContentActivity;
import com.example.demo.adapter.ProductAdapter;
import com.example.demo.beans.Product;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * intent跳转时，intent传值type：0（更多页面） 1（详情页面）
 */
public class ShopFragment extends Fragment implements OnBannerListener, IFragmentShopV {
    private Banner banner;
    List<String> images = new ArrayList<>();   //定义图片集合
    private RecyclerView productRv;
    private IFragmentShopP mPresenter;
    private FontIconView more;
    private TextView searchtv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        mPresenter=new FragmentShopPImpl(this);
        mPresenter.getData();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        productRv.setLayoutManager(layoutManager);
        images.add("http://seopic.699pic.com/photo/40005/1749.jpg_wh1200.jpg");
        images.add("http://seopic.699pic.com/photo/50059/1442.jpg_wh1200.jpg");
        images.add("http://seopic.699pic.com/photo/50054/5187.jpg_wh1200.jpg");  //图片路径
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(images);  //设置banner中显示图片
        banner.setDelayTime(3000);
        banner.setOnBannerListener(this);
        banner.start();  //设置完毕后调用
    }

    private void initView(View view) {
        searchtv=view.findViewById(R.id.searchtv);
        productRv = view.findViewById(R.id.product_Rv);
        banner = view.findViewById(R.id.banner);
        searchtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type","9");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        more=view.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type","0");
                startActivity(intent);
            }
        });

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getData(List<Product.ProductInfoListBean> productInfoListBeanList) {
        productRv.setAdapter(new ProductAdapter(productInfoListBeanList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("type","1");
                intent.putExtra("pid",String.valueOf(productInfoListBeanList.get(position).getProduct().getPid()));
                startActivity(intent);
            }
        }, getActivity()));
    }
}
