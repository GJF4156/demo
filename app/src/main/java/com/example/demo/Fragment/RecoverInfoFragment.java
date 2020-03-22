package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.Reservation;
import com.example.demo.model.Model;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoverInfoFragment extends Fragment {
    private TextView tv_title,bt_header_right;
    private EditText time,person,phone,address,sort;
    private Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_info, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tv_title.setText("信息填写");
        bt_header_right.setText("");
        getViewData();
    }

    private Reservation getViewData() {
        String time = this.time.getText().toString();
        String person = this.person.getText().toString();
        String phoone = this.phone.getText().toString();
        String address = this.address.getText().toString();
        String sort = this.sort.getText().toString();
        Reservation reservation=new Reservation();
        reservation.setGotime(time);
        reservation.setPerson(person);
        reservation.setTelephone(phoone);
        reservation.setAddress(address);
        reservation.setSort(sort);
        return reservation;
    }

    private void initView(View view) {
        tv_title=getActivity().findViewById(R.id.tv_title);
        bt_header_right=getActivity().findViewById(R.id.bt_header_right);
        time=view.findViewById(R.id.time);
        person=view.findViewById(R.id.person);
        phone=view.findViewById(R.id.phone);
        address=view.findViewById(R.id.address);
        sort=view.findViewById(R.id.sort);
        submit=view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation data = getViewData();
                String json = new Gson().toJson(data);
                String url="http://129.211.75.130:8080/demo/reservation/insert";
                Map<String,Object> map=new HashMap<>();
                map.put("token", SPUtil.getToken());
                map.put("reservation",json);
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
                            @Override
                            public void onResponse(String result) {
                                JsonBean jsonBean=new Gson().fromJson(result,JsonBean.class);
                                int code = jsonBean.getCode();
                                String msg = jsonBean.getMsg();
                                if (code==100){
                                    Map<String, Object> info = jsonBean.getInfo();
                                    Object success = info.get("success");
                                    String s = new Gson().toJson(success);
                                    String s1 = new Gson().fromJson(s, String.class);
                                    Toast.makeText(getActivity(),s1,Toast.LENGTH_SHORT).show();
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.web_content_frameLayout,new RecoveryFragment())
                                            .commitAllowingStateLoss();
                                }else {
                                    Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                                    Log.d("EDemo",msg);
                                }
                            }

                            @Override
                            public void onFail(String result) {
                                Log.d("EDemo","Fail");
                            }

                            @Override
                            public void onCancel(Callback.CancelledException cex) {
                                Log.d("EDemo","Cancel");
                            }
                        });
                    }
                });
            }
        });
    }

}
