package com.example.demo.Fragment.Presenter.impl;

import com.example.demo.Fragment.Model.IFragmentShopM;
import com.example.demo.Fragment.Model.impl.FragmentShopMImpl;
import com.example.demo.Fragment.Presenter.IFragmentShopP;
import com.example.demo.Fragment.View.IFragmentShopV;
import com.example.demo.beans.Product;

import java.util.List;

public class FragmentShopPImpl implements IFragmentShopP {

    private IFragmentShopM mModel;
    private IFragmentShopV mView;

    public FragmentShopPImpl(IFragmentShopV view) {
        this.mView = view;
        mModel = new FragmentShopMImpl(this);
    }

    @Override
    public void getData() {
        mModel.getData();
    }

    @Override
    public void returnData(List<Product.ProductInfoListBean> productInfoListBeanList) {
        mView.getData(productInfoListBeanList);
    }
}
