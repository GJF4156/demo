package com.example.demo.Fragment.Presenter.impl;

import com.example.demo.Fragment.Model.IFragmentHomeM;
import com.example.demo.Fragment.Model.impl.FragmentHomeMImpl;
import com.example.demo.Fragment.Presenter.IFragmentHomeP;
import com.example.demo.Fragment.View.IFragmentHomeV;
import com.example.demo.beans.NewsBeans;

import java.util.List;

public class FragmentHomePImpl implements IFragmentHomeP {

    private IFragmentHomeM mModel;
    private IFragmentHomeV mView;

    public FragmentHomePImpl(IFragmentHomeV view) {
        this.mView = view;
        mModel=new FragmentHomeMImpl(this);
    }

    @Override
    public void getData() {
        mModel.getData();
    }

    @Override
    public void returnData(List<NewsBeans.NewslistBean> newslistBeanList) {
        mView.getData(newslistBeanList);
    }

}
