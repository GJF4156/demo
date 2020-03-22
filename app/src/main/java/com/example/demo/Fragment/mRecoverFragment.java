package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.adapter.RecoveryAdapter;
import com.example.demo.beans.AddressBean;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.Reservation;
import com.example.demo.model.Model;
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
public class mRecoverFragment extends Fragment {
    private RecyclerView recovery_rv;
    private TextView tv_title, bt_header_right;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_m_recover, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tv_title.setText("预约订单");
        bt_header_right.setText("");
        getUrlData();
        recovery_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        recovery_rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void getUrlData() {
        String url = "http://129.211.75.130:8080/demo/reservation/select";
        Map<String, Object> map = new HashMap<>();
        map.put("token", SPUtil.getToken());
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
                    @Override
                    public void onResponse(String result) {
                        Gson gson = new Gson();
                        JsonBean jsonBean = gson.fromJson(result, JsonBean.class);
                        int code = jsonBean.getCode();
                        String msg = jsonBean.getMsg();
                        if (code == 100) {
                            Map<String, Object> info = jsonBean.getInfo();
                            Object o = info.get("reservations");
                            String s = gson.toJson(o);
                            List<Reservation> list = new ArrayList<>();
                            Type type = new TypeToken<List<Reservation>>() {
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
        });


    }

    private void setRecyclerView(List<Reservation> list) {
        recovery_rv.setAdapter(new RecoveryAdapter(getActivity(), list, new RecoveryAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        }));


    }

    private void initView(View view) {
        recovery_rv = view.findViewById(R.id.recovery_rv);
        tv_title = getActivity().findViewById(R.id.tv_title);
        bt_header_right = getActivity().findViewById(R.id.bt_header_right);
    }

}
