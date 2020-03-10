package com.example.demo.Login.fragment.presenter.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.demo.Login.fragment.model.ILoginModel;
import com.example.demo.Login.fragment.model.impl.LoginModelImpl;
import com.example.demo.Login.fragment.presenter.ILoginPresenter;
import com.example.demo.Login.fragment.view.ILoginView;
import com.example.demo.base.UniteApp;

public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginModel mILoginModel;
    private ILoginView mILoginView;

    public LoginPresenterImpl(ILoginView ILoginView) {
        this.mILoginView = ILoginView;
        mILoginModel = new LoginModelImpl(this);
    }

    @Override
    public void getUserInfo(String userId, String password) {
        mILoginModel.startLogin(userId,password);
    }

    @Override
    public void putToken(String token) {
        SharedPreferences.Editor editor = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.apply();
    }

    @Override
    public void parseStatus(int code, String msg) {
        switch (code){
            case 100:
                Log.d("loginMsg", "登陆成功");
                mILoginView.loginSuccess();
                break;
            case 300:
                Log.d("loginMsg", "登陆失败");
                mILoginView.loginFailed(msg);
                break;
        }
    }

    @Override
    public void loginServerError(String message) {
        mILoginView.showLoginServerError(message);
    }
}
