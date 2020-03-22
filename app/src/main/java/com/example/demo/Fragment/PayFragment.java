package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.adapter.PayAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.OrderSimple;
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
    private TextView tvTitle, btHeaderRight, allPrice, receiver, phone_num, address_info;
    private RecyclerView orderRv;
    private Button submitOrder;
    private LinearLayout set_address;
    ProductInfoFragment productInfoFragment;
    //192.168.0.105
    private String url = "http://192.168.0.105:8080/orders/insert?orders=";

    private List<OrdersBean.OrderBean> orderBeanList = new ArrayList<>();
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
        receiver.setText(SPUtil.getReceiver());
        phone_num.setText(SPUtil.getPhoneNum());
        address_info.setText(SPUtil.getAddressInfo());
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
        List<OrderSimple> orderSimples = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String s = simpleDateFormat.format(date);
        for (int i = 0; i < list.size(); i++) {
            OrderSimple orderSimple = new OrderSimple();
            orderSimple.setPid(list.get(i).getId());
            orderSimple.setNum(list.get(i).getCount());
            orderSimples.add(orderSimple);
        }
        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验钱包，金额足够就提交订单，不够就提示充值
                createOrders(orderSimples);
            }
        });
    }

    private void createOrders(List<OrderSimple> orderSimples) {
        String url = "http://129.211.75.130:8080/demo/orders/insert";
        String s = new Gson().toJson(orderSimples);
        System.out.println("======================================\n" + s + "\n=======================================");
        Map<String, Object> map = new HashMap<>();
        map.put("orderSimples", s);
        map.put("aid", SPUtil.getAid());
        map.put("token", SPUtil.getToken());
        XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                JsonBean jsonBean = new Gson().fromJson(result, JsonBean.class);
                String msg = jsonBean.getMsg();
                int code = jsonBean.getCode();
                if (code == 100) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frameLayout,new MyOrderFragment())
                            .commitAllowingStateLoss();
                } else {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String result) {
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(CancelledException cex) {
                Toast.makeText(getActivity(), "取消", Toast.LENGTH_SHORT).show();
            }
        });

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
        receiver = view.findViewById(R.id.receiver);
        phone_num = view.findViewById(R.id.phone_num);
        address_info = view.findViewById(R.id.address_info);
        submitOrder = view.findViewById(R.id.submit_order);
        set_address = view.findViewById(R.id.set_address);
        set_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, new AddressManagerFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
}
