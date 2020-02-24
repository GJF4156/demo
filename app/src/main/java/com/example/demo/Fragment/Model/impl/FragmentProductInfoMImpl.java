package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.IFragmentProductInfoM;
import com.example.demo.Fragment.Presenter.IFragmentProductInfoP;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.ProductInfo;
import com.google.gson.Gson;

public class FragmentProductInfoMImpl extends BaseModel implements IFragmentProductInfoM {

    private IFragmentProductInfoP mPresenter;
    private String url="http://129.211.75.130:8080/demo/products/findById?pid=";

    public FragmentProductInfoMImpl(IFragmentProductInfoP presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData(String pid) {

        loadData(url+pid);

    }

    @Override
    public void onSuccess(String result) {
        ProductInfo productInfo=new Gson().fromJson(result,ProductInfo.class);

        mPresenter.returnData(productInfo.getProductInfo());

    }
}
