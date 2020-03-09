package com.example.demo.Login.fragment.model;

public interface ILoginModel {
    void startLogin(String userId, String password);
    //获得用户信息
    void getUserInfo(String token);
    //获得用户照片
    void getImage(String token);
}
