package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.IFragmentSortM;
import com.example.demo.Fragment.Presenter.IFragmentSortP;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.SortsBean;
import com.example.demo.model.Model;
import com.google.gson.Gson;

public class FragmentSortMImpl extends BaseModel implements IFragmentSortM {

    private IFragmentSortP mPresenter;
    private String url1="http://129.211.75.130:8080/demo/garbage/selectbytype?type=";
    public FragmentSortMImpl(IFragmentSortP presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData(int type) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String url=url1+type;
                loadData(url,0);
                url=url1;
                System.out.println(url);
            }
        });
    }

    @Override
    public void onSuccess(String result) {
        SortsBean sortsBean=new Gson().fromJson(result,SortsBean.class);
        mPresenter.returnData(sortsBean.getDatalist());

    }
}
