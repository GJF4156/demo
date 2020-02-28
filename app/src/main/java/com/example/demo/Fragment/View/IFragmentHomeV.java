package com.example.demo.Fragment.View;

import android.content.Context;

import com.example.demo.beans.NewsBeans;

import java.util.List;

public interface IFragmentHomeV {
    public void getData(List<NewsBeans.NewslistBean> newslistBeanList);
}
