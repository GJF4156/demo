package com.example.demo.Login.fragment.view;

public interface ILoginView {
    void loginSuccess();
    void loginFailed(String msg);
    String userId();
    String password();
    //服务器异常
    void showLoginServerError(String message);
}
