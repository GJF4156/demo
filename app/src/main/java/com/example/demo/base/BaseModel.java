package com.example.demo.base;

import android.content.Context;

import com.example.demo.model.Model;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class BaseModel implements Callback.CommonCallback<String> {
    public void loadData(String path, int requestType) {
        RequestParams params = new RequestParams(path);
        switch (requestType) {
            case 0:
                x.http().get(params, this);
                break;
            case 1:
                x.http().post(params, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
    }
}