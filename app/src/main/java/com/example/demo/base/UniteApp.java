package com.example.demo.base;

import android.app.Application;

import com.example.demo.model.Model;

import org.xutils.DbManager;
import org.xutils.x;

public class UniteApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        Model.getInstance().init(this);
    }
}
