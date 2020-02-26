package com.example.demo.Fragment.Presenter;

import com.example.demo.beans.Product;

import java.util.List;

public interface IFragmentMoreP {
    public void getData(String url);
    public void returnData(List<Product.ProductInfoListBean> productInfoListBeanList);
}
