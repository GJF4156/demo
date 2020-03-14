package com.example.demo.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.adapter.AddressAdapter;
import com.example.demo.base.UniteApp;
import com.example.demo.beans.Address;
import com.example.demo.beans.AddressBean;
import com.example.demo.beans.JsonBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressManagerFragment extends Fragment {
    private TextView tvTitle, btHeaderRight, btBack;
    private RecyclerView address_manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_manager, container, false);
        initView(view);
        initData();

        return view;
    }

    private void initData() {
        tvTitle.setText("收货地址管理");
        btHeaderRight.setText("添加");
        btHeaderRight.setVisibility(View.VISIBLE);
        btBack.setVisibility(View.GONE);
        address_manager.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        address_manager.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        getDataForUrl();
    }

    private void getDataForUrl() {
        String url = "http://129.211.75.130:8080/demo/address/select";
        Map<String, Object> map = new HashMap<>();
        map.put("token", SPUtil.getToken());
        XutilsHttp.getInstance().get(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                String msg = jsonBean.getMsg();
                int code = jsonBean.getCode();
                if (code == 100) {
                    Map<String, Object> info = jsonBean.getInfo();
                    Object o = info.get("addresses");
                    String s = gson.toJson(o);
                    List<AddressBean.InfoBean.AddressesBean> list = new ArrayList<>();
                    Type type = new TypeToken<List<AddressBean.InfoBean.AddressesBean>>() {
                    }.getType();
                    list = gson.fromJson(s, type);
                    setRecyclerView(list);
                }


            }

            @Override
            public void onFail(String result) {

            }

            @Override
            public void onCancel(Callback.CancelledException cex) {

            }
        });
    }

    private void setRecyclerView(List<AddressBean.InfoBean.AddressesBean> list) {
        if (list.size() > 0) {
            AddressAdapter addressAdapter = new AddressAdapter(getActivity(), list);
            addressAdapter.setOnDelListener(new AddressAdapter.onSlideListener() {
                @Override
                public void onDel(int position) {
                    deleteAddress(position);
                }
            });
            address_manager.setAdapter(addressAdapter);
        } else {
            address_manager.setVisibility(View.GONE);
            SharedPreferences.Editor editor = UniteApp.getContext()
                    .getSharedPreferences("address", Context.MODE_PRIVATE).edit();
            editor.putString("aid", "");
            editor.putString("receiver", "");
            editor.putString("phone", "");
            editor.putString("address", "请填写收货地址");
            editor.apply();
        }
    }

    private void deleteAddress(int position) {
        String url = "http://129.211.75.130:8080/demo/address/delete";
        Map<String, Object> map = new HashMap<>();
        map.put("aid", position);
        map.put("token", SPUtil.getToken());
        XutilsHttp.getInstance().get(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                String msg = jsonBean.getMsg();
                int code = jsonBean.getCode();
                if (code==100){
                    getDataForUrl();
                }
                Toast.makeText(getActivity(), code+msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String result) {

            }

            @Override
            public void onCancel(Callback.CancelledException cex) {

            }
        });
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        btBack = getActivity().findViewById(R.id.btn_back);
        address_manager = view.findViewById(R.id.address_manager);
        btHeaderRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, new AddressFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
}
