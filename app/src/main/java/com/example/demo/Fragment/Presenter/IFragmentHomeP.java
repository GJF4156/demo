package com.example.demo.Fragment.Presenter;

import com.example.demo.beans.NewsBeans;

import java.util.List;

public interface IFragmentHomeP {

    public void getData();

    public void returnData(List<NewsBeans.NewslistBean> newslistBeanList);
}
