package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.SPUtil;
import com.example.demo.adapter.PayAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.OrdersBean;
import com.example.demo.beans.ShoppingCartBean;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends BaseFragment {
    private TextView tvTitle, btHeaderRight, allPrice,address,mAddress;
    private RecyclerView orderRv;
    private Button submitOrder;
    ProductInfoFragment productInfoFragment;
    //192.168.0.105
    private String url="http://192.168.0.105:8080/orders/insert?orders=";

    private List<OrdersBean.OrderBean> orderBeanList=new ArrayList<>();
    private double v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        //初始化view
        initView(view);
        //初始化数据
        initData();
        return view;
    }

    private void initData() {
        List<ShoppingCartBean> list = (List<ShoppingCartBean>) getArguments().getSerializable("shoppingCartBeanList");
        tvTitle.setText("支付");
        btHeaderRight.setVisibility(View.GONE);
        address.setText(SPUtil.getLocation());
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        orderRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        orderRv.setAdapter(new PayAdapter(getActivity(), list, new PayAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                productInfoFragment = new ProductInfoFragment();
                Bundle bundle = new Bundle();
                String pid = String.valueOf(list.get(position).getId());
                bundle.putString("pid", pid);
                productInfoFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, productInfoFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        }));
        //获取总价
        getAllPrice(list);
        initOrder(list);

    }

    private void initOrder(List<ShoppingCartBean> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String s = simpleDateFormat.format(date);
        for (int i = 0; i < list.size(); i++) {
            OrdersBean.OrderBean orderBean = new OrdersBean.OrderBean();
            orderBean.setOdId(0);
            orderBean.setOdAddress("四川省遂宁市大英县");//从用户信息中获取
            orderBean.setOdPrice(list.get(i).getPrice() * list.get(i).getCount());
            orderBean.setOdStatus("未处理");//默认未处理
            orderBean.setOdProductId(list.get(i).getId());
            orderBean.setOdUserId(1);//从用户信息中获取
            orderBean.setOdTele("13777777777");//从用户信息中获取
            orderBean.setOdExpressType("中通");//快递类型
            orderBean.setOdNumber("222222");//用随机生成器生成
            orderBean.setOdShipTime(null);//发货时间
            orderBean.setOdClosingTime(s);//成交时间
            orderBean.setOdCreationTime(s);//创建时间
            orderBeanList.add(orderBean);
        }
        String OrdersList=new Gson().toJson(orderBeanList);
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","成功");
        map.put("OrdersList",OrdersList);
        System.out.println(orderBeanList.get(0).toString());
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            loadData(url+OrdersList,1);
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
    }

    //获取总价
    private void getAllPrice(List<ShoppingCartBean> list) {
        for (int i = 0; i < list.size(); i++) {
            v += list.get(i).getPrice() * list.get(i).getCount();
        }
        allPrice.setText("合计：" + String.valueOf(v) + "元");
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        orderRv = view.findViewById(R.id.order_rv);
        allPrice = view.findViewById(R.id.all_price);
        submitOrder = view.findViewById(R.id.submit_order);
        address=view.findViewById(R.id.mAddress);
        mAddress=view.findViewById(R.id.mAddress);
        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
