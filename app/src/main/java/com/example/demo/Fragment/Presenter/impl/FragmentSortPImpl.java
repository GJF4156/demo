package com.example.demo.Fragment.Presenter.impl;

import com.example.demo.Fragment.Model.IFragmentSortM;
import com.example.demo.Fragment.Model.impl.FragmentSortMImpl;
import com.example.demo.Fragment.Presenter.IFragmentSortP;
import com.example.demo.Fragment.View.IFragmentSortV;
import com.example.demo.beans.SortsBean;

import java.util.List;

public class FragmentSortPImpl implements IFragmentSortP {
    private IFragmentSortM mModel;
    private IFragmentSortV mView;

    public FragmentSortPImpl(IFragmentSortV view) {
        this.mView = view;
        mModel=new FragmentSortMImpl(this);
    }

    @Override
    public void getData(int type) {
        mModel.getData(type);
    }

    @Override
    public void returnData(List<SortsBean.DatalistBean> resultBeanList) {
        mView.getData(resultBeanList);
    }
}
