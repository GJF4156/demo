package com.example.demo.Fragment.Presenter.impl;

import com.example.demo.Fragment.Model.IFragmentMoreM;
import com.example.demo.Fragment.Model.impl.FragmentMoreMImpl;
import com.example.demo.Fragment.Presenter.IFragmentMoreP;
import com.example.demo.Fragment.View.IFragmentMoreV;
import com.example.demo.beans.Product;

import java.util.List;

public class FragmentMorePImpl implements IFragmentMoreP {

    private IFragmentMoreM mModel;
    private IFragmentMoreV mView;

    public FragmentMorePImpl(IFragmentMoreV view) {
        this.mView = view;
        mModel = new FragmentMoreMImpl(this);
    }

    @Override
    public void getData(String url) {
        mModel.getData(url);
    }

    @Override
    public void returnData(List<Product.ProductInfoListBean> productInfoListBeanList) {
        mView.getData(productInfoListBeanList);
    }
}
