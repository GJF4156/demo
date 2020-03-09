package com.example.demo.Login.fragment.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.demo.Login.fragment.presenter.ILoginPresenter;
import com.example.demo.Utils.SPUtil;
import com.example.demo.Utils.XutilsHttp;
import com.example.demo.base.UniteApp;
import com.example.demo.beans.JsonBean;
import com.example.demo.beans.User;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;
import java.util.Map;

public class LoginModelImpl implements ILoginModel{
    private ILoginPresenter loginPresenter;
    private String mToken = SPUtil.getToken();

    public LoginModelImpl(ILoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void startLogin(String userId, String password) {
        String url="http://129.211.75.130:8080/demo/user/login";
        Map<String,Object> map=new HashMap<>();
        map.put("phone",userId);
        map.put("psw",password);
        XutilsHttp.getInstance().get(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                JsonBean jsonBean=new Gson().fromJson(result,JsonBean.class);
                int code = jsonBean.getCode();
                String msg = jsonBean.getMsg();
                Map<String, Object> info = jsonBean.getInfo();
                Object token = info.get("token");
                if (code==100){
                    getUserInfo(String.valueOf(token));
                    loginPresenter.putToken(String.valueOf(token));
                }else {
                    loginPresenter.parseStatus(code,msg);
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


    @Override
    public void getUserInfo(String token) {
        String url="http://129.211.75.130:8080/demo/user/findUserInfo";
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        XutilsHttp.getInstance().post(url, map, new XutilsHttp.XCallBack() {
            @Override
            public void onResponse(String result) {
                Gson gson=new Gson();
                JsonBean jsonBean=gson.fromJson(result,JsonBean.class);
                int code = jsonBean.getCode();
                String msg = jsonBean.getMsg();
                switch (code){
                    case 100:
                        Map<String, Object> info = jsonBean.getInfo();
                        Object o = info.get("user");
                        String json = gson.toJson(o);
                        User user = gson.fromJson(json, User.class);
                        SharedPreferences.Editor editor = UniteApp.getContext()
                                .getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                        editor.putString("userId",user.getUsertelephone());
                        editor.putString("brith",user.getUser_brith());
                        editor.putString("idcard",user.getUser_idcard());
                        editor.putString("location",user.getUser_location());
                        editor.putString("name",user.getUser_name());
                        editor.putString("nickName",user.getUser_nickName());
                        editor.putString("photo",user.getUser_photo());
                        editor.putString("sex",user.getUser_sex());
                        editor.putString("password",user.getUserpassword());
                        editor.putString("Wlid", String.valueOf(user.getWl_id()));
                        editor.apply();
                        loginPresenter.parseStatus(code,msg);
                        break;
                    case 200:
                        Log.d("userInfoMsg", " 获取用户信息失败");
                        break;
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

    @Override
    public void getImage(String token) {

    }
}
