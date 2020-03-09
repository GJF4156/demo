package com.example.demo.base;

import android.app.Application;
import android.content.Context;

import com.example.demo.model.Model;

import org.xutils.DbManager;
import org.xutils.x;

public class UniteApp extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
        Model.getInstance().init(this);
        sContext = getApplicationContext();
    }
    public static Context getContext(){
        return sContext;
    }
}
