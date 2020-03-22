package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.MyOrderFragmentM;
import com.example.demo.Fragment.Presenter.MyOrderFragmentP;

public class MyOrderFragmentMImpl implements MyOrderFragmentM {
    private MyOrderFragmentP myOrderFragmentP;

    public MyOrderFragmentMImpl(MyOrderFragmentP myOrderFragmentP) {
        this.myOrderFragmentP = myOrderFragmentP;
    }

    @Override
    public void startFind(String token) {
        String url="";

    }

    @Override
    public void getProductInfo(String pid) {

    }
}
