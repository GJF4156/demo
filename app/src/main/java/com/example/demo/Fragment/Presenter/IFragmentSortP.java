package com.example.demo.Fragment.Presenter;

import com.example.demo.beans.SortsBean;

import java.util.List;

public interface IFragmentSortP {
    public void getData(int type);
    public void returnData(List<SortsBean.DatalistBean> resultBeanList);

}
