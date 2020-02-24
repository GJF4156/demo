package com.example.demo.Fragment.Presenter.impl;

import com.example.demo.Fragment.Model.IFragmentProductInfoM;
import com.example.demo.Fragment.Model.impl.FragmentProductInfoMImpl;
import com.example.demo.Fragment.Presenter.IFragmentProductInfoP;
import com.example.demo.Fragment.View.IFragmentProductInfoV;
import com.example.demo.beans.ProductInfo;

import java.util.List;

public class FragmentProductInfoPImpl implements IFragmentProductInfoP {

    private IFragmentProductInfoM mModel;
    private IFragmentProductInfoV mView;

    public FragmentProductInfoPImpl(IFragmentProductInfoV view) {
        this.mView = view;
        mModel=new FragmentProductInfoMImpl(this);
    }

    @Override
    public void getData(String pid) {
        mModel.getData(pid);
    }

    @Override
    public void returnData(ProductInfo.ProductInfoBean productInfoBeans) {
        mView.getData(productInfoBeans);
    }
}
