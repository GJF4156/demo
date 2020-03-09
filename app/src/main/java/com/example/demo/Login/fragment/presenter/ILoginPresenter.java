package com.example.demo.Login.fragment.presenter;

public interface ILoginPresenter {
    void getUserInfo(String userId, String password);
    void putToken(String token);
    //获取M层返回的状态和信息
    void parseStatus(int status, String msg);
    //服务器没开，报异常
    void loginServerError(String message);
}
