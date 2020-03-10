package com.example.demo.Login.fragment.presenter;

public interface IRegisterPresenter {

    void getRegisterInfo(String userId, String psd);
    void getStatus(int status, String msg);
}
