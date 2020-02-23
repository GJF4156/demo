package com.example.demo.activity.presenter.impl;

import com.example.demo.activity.model.SearchModel;
import com.example.demo.activity.model.impl.SearchModelImpl;
import com.example.demo.activity.presenter.SearchPres;
import com.example.demo.activity.view.SearchView;
import com.example.demo.beans.SortsBean;

import java.util.List;

public class SearchPresImpl implements SearchPres {
    private SearchModel mModel;
    private SearchView mView;

    public SearchPresImpl(SearchView view) {
        this.mView = view;
        mModel=new SearchModelImpl(this);
    }

    @Override
    public void getData(String query) {
        mModel.getData(query);
    }

    @Override
    public void returnData(List<SortsBean.DatalistBean> dataList) {
        mView.getData(dataList);
    }
}
