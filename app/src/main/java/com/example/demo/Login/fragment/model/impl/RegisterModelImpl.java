package com.example.demo.Login.fragment.model.impl;

import com.example.demo.Login.fragment.model.IRegisterModel;
import com.example.demo.Login.fragment.presenter.IRegisterPresenter;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.beans.JsonBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

public class RegisterModelImpl implements IRegisterModel {
    private IRegisterPresenter mIRegisterPresenter;

    public RegisterModelImpl(IRegisterPresenter mIRegisterPresenter) {
        this.mIRegisterPresenter = mIRegisterPresenter;
    }

    @Override
    public void startRegister(String userId, String psw) {
        String url = "http://129.211.75.130:8080/demo/user/register";
        Map<String,Object> map=new HashMap<>();
        map.put("phone",userId);
        map.put("psw",psw);
        XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                parseJSON(result);
            }

            @Override
            public void onFail(String result) {

            }

            @Override
            public void onCancel(Callback.CancelledException cex) {

            }
        });
    }

    @Override
    public void parseJSON(String responseData) {
        Gson gson=new Gson();
        JsonBean jsonBean=gson.fromJson(responseData,JsonBean.class);
        int code = jsonBean.getCode();
        String msg = jsonBean.getMsg();
        mIRegisterPresenter.getStatus(code,msg);
    }
}
