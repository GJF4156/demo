package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.IFragmentShopM;
import com.example.demo.Fragment.Presenter.IFragmentShopP;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.Product;
import com.example.demo.model.Model;
import com.google.gson.Gson;

public class FragmentShopMImpl extends BaseModel implements IFragmentShopM {

    private IFragmentShopP mPresenter;

    public FragmentShopMImpl(IFragmentShopP presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData() {

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String url="http://129.211.75.130:8080/demo/products/findAll";
                loadData(url,0);
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        Product product=new Gson().fromJson(result,Product.class);
        mPresenter.returnData(product.getProductInfoList());
    }
}
