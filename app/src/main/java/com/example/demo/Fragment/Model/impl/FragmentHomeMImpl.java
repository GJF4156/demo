package com.example.demo.Fragment.Model.impl;

import com.example.demo.Fragment.Model.IFragmentHomeM;
import com.example.demo.Fragment.Presenter.IFragmentHomeP;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.NewsBeans;
import com.google.gson.Gson;

import java.util.List;

public class FragmentHomeMImpl extends BaseModel implements IFragmentHomeM {

    private IFragmentHomeP mPresenter;

    public FragmentHomeMImpl(IFragmentHomeP presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData() {
        String url="http://api.tianapi.com/huanbao/index?key=a24ff874e046c94eb472e3a7692900e3&num=20";
        loadData(url);
    }

    @Override
    public void onSuccess(String result) {
        NewsBeans newsBeans=new Gson().fromJson(result,NewsBeans.class);
        List<NewsBeans.NewslistBean> newslistBeanList=newsBeans.getNewslist();
        mPresenter.returnData(newslistBeanList);
    }
}
