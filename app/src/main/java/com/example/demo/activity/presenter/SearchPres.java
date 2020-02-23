package com.example.demo.activity.presenter;

import com.example.demo.beans.SortsBean;

import java.util.List;

public interface SearchPres {

    public void getData(String query);
    public void returnData(List<SortsBean.DatalistBean> dataList);
}
