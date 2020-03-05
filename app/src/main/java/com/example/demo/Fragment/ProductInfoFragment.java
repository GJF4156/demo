package com.example.demo.Fragment;

import android.annotation.SuppressLint;
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
import com.example.demo.beans.ShoppingCartBean;
import com.youth.banner.Banner;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductInfoFragment extends Fragment implements IFragmentProductInfoV, View.OnClickListener {
    private Banner banner;
    private TextView description, price, format, number, kefu, car, tvTitle, btHeaderRight;//format表示规格
    private Button addCar, buy;
    private ProductInfo.ProductInfoBean.ProductBean products;
    private IFragmentProductInfoP mPresenter;
    private CarFragment carFragment;
    private DbManager db;
    private ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
    List<ProductInfo.ProductInfoBean.ImagesPathBean> images1 = new ArrayList<>();   //定义图片集合
    List<String> images = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);
        mPresenter = new FragmentProductInfoPImpl(this);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String pid = bundle.getString("pid");
        mPresenter.getData(pid);
        //初始化本地数据库
        initDb();

        return view;
    }

    private void initDb() {
        //本地数据的初始化
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("shoppingCar") //设置数据库名
                .setDbVersion(1) //设置数据库版本
                .setDbOpenListener(db -> {
                    db.getDatabase().enableWriteAheadLogging();
                    //开启WAL, 对写入加速提升巨大(作者原话)
                })
                .setDbUpgradeListener((db, oldVersion, newVersion) -> {
                    //数据库升级操作
                });
        db = x.getDb(daoConfig);
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
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        tvTitle.setText("商品详情");
        btHeaderRight.setVisibility(view.GONE);
        format.setOnClickListener(this);
        kefu.setOnClickListener(this);
        car.setOnClickListener(this);
        addCar.setOnClickListener(this);
        buy.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getData(ProductInfo.ProductInfoBean productInfoBeans) {
        images1 = productInfoBeans.getImagesPath();
        products = productInfoBeans.getProduct();
        for (int i = 0; i < images1.size(); i++) {
            images.add(images1.get(i).getImgPath());
        }
        shoppingCartBean.setId(productInfoBeans.getProduct().getPid());
        shoppingCartBean.setShoppingName(productInfoBeans.getProduct().getDescription());
        shoppingCartBean.setCount(1);//默认数量均为1
        shoppingCartBean.setPrice(productInfoBeans.getProduct().getPrice());
        shoppingCartBean.setDressSize(20);
        shoppingCartBean.setImageUrl(images1.get(0).getImgPath());
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
                carFragment = new CarFragment();
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, carFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.add_car:
                try {
                    ShoppingCartBean byId = db.findById(ShoppingCartBean.class, shoppingCartBean.getId());
                    if (byId==null){
                        db.save(shoppingCartBean);
                        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "已在购物车", Toast.LENGTH_SHORT).show();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buy:
                List<ShoppingCartBean> list=new ArrayList<>();
                list.add(shoppingCartBean);
                PayFragment payFragment=new PayFragment();
                Bundle bundle=new Bundle();
                bundle.putSerializable("shoppingCartBeanList", (Serializable) list);
                payFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,payFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
