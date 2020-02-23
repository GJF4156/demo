package com.example.demo.Fragment.Presenter;

import com.example.demo.beans.Product;

import java.util.List;

public interface IFragmentShopP {
    public void getData();
    public void returnData(List<Product.ProductInfoListBean> productInfoListBeanList);
}
