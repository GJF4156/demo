package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.IFragmentMoreM;
import com.example.demo.Fragment.Presenter.IFragmentMoreP;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.Product;
import com.google.gson.Gson;

public class FragmentMoreMImpl extends BaseModel implements IFragmentMoreM {
    private IFragmentMoreP mPresenter;

    public FragmentMoreMImpl(IFragmentMoreP presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData(String url) {
        loadData(url);
    }

    @Override
    public void onSuccess(String result) {
        Product product=new Gson().fromJson(result,Product.class);
        mPresenter.returnData(product.getProductInfoList());

    }
}
