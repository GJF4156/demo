package com.example.demo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentProductInfoP;
import com.example.demo.Fragment.Presenter.impl.FragmentProductInfoPImpl;
import com.example.demo.Fragment.View.IFragmentProductInfoV;
import com.example.demo.R;
import com.example.demo.Utils.GlideImageLoader;
import com.example.demo.beans.ProductInfo;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductInfoFragment extends Fragment implements IFragmentProductInfoV, View.OnClickListener {
    private Banner banner;
    private TextView description, price, format, number, kefu, car;//format表示规格
    private Button addCar, buy;
    List<ProductInfo.ProductInfoBean.ImagesPathBean> images1 = new ArrayList<>();   //定义图片集合
    List<String> images = new ArrayList<>();
    private IFragmentProductInfoP mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);
        mPresenter = new FragmentProductInfoPImpl(this);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String pid = bundle.getString("pid");
        mPresenter.getData(pid);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = view.findViewById(R.id.product_banner);
        description = view.findViewById(R.id.description);
        price = view.findViewById(R.id.price);
        format = view.findViewById(R.id.format);
        number = view.findViewById(R.id.number);
        kefu = view.findViewById(R.id.kefu);
        car = view.findViewById(R.id.car);
        addCar = view.findViewById(R.id.add_car);
        buy = view.findViewById(R.id.buy);

        format.setOnClickListener(this);
        kefu.setOnClickListener(this);
        car.setOnClickListener(this);
        addCar.setOnClickListener(this);
        buy.setOnClickListener(this);
    }

    @Override
    public void getData(ProductInfo.ProductInfoBean productInfoBeans) {
        images1 = productInfoBeans.getImagesPath();
        for (int i = 0; i < images1.size(); i++) {
            images.add(images1.get(i).getImgPath());
        }
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(images);  //设置banner中显示图片
        banner.setDelayTime(3000);
        banner.start();  //设置完毕后调用
        description.setText(productInfoBeans.getProduct().getDescription());
        price.setText("￥" + String.valueOf(productInfoBeans.getProduct().getPrice()));
        number.setText("已售" + String.valueOf(productInfoBeans.getProduct().getSold()) + "件");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.format:
                Toast.makeText(getActivity(), "规格", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kefu:
                Toast.makeText(getActivity(), "客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.car:
                Toast.makeText(getActivity(), "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_car:
                Toast.makeText(getActivity(), "添加购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buy:
                Toast.makeText(getActivity(), "立即购买", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
