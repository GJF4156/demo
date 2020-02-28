package com.example.demo.Fragment.Presenter;

import android.content.Context;

import com.example.demo.beans.NewsBeans;

import java.util.List;

public interface IFragmentHomeP {

    public void getData(Context context);

    public void returnData(List<NewsBeans.NewslistBean> newslistBeanList);
}
