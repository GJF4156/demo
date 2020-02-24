package com.example.demo.Fragment.Presenter;

import com.example.demo.beans.ProductInfo;

import java.util.List;

public interface IFragmentProductInfoP {
    public void getData(String pid);
    public void returnData(ProductInfo.ProductInfoBean productInfoBean);
}
