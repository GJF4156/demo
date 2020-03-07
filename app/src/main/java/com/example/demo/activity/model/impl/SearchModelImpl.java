package com.example.demo.activity.model.impl;

import com.example.demo.activity.model.SearchModel;
import com.example.demo.activity.presenter.SearchPres;
import com.example.demo.base.BaseModel;
import com.example.demo.beans.SortsBean;
import com.google.gson.Gson;

public class SearchModelImpl extends BaseModel implements SearchModel {
    //url1 = "https://service.xiaoyuan.net.cn/garbage/index/search?kw=";//已经被限制请求次数
    //url1="https://laji.lr3800.com/api.php?name=";//免费无限制，不需要账号
    //url="https://www.lajiflw.cn/rubbish/category";//根据类别检索垃圾（不全），免费不要账号
    //url1 = "http://api.tianapi.com/txapi/lajifenlei/index?key=a24ff874e046c94eb472e3a7692900e3&word=";//需要账号APIKEY，有次数限制，普通会员5000次
    private SearchPres mPresenter;
    private String url1 = "http://129.211.75.130:8080/demo/garbage/listGarbage?name=";

    public SearchModelImpl(SearchPres presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void getData(String query) {
        String url = url1 + query;
        loadData(url,0);
        url = url1;
    }

    @Override
    public void onSuccess(String result) {
        SortsBean sortsBean = new Gson().fromJson(result, SortsBean.class);
        mPresenter.returnData(sortsBean.getDatalist());

    }
}
