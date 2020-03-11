package com.example.demo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.demo.base.UniteApp;

public class SPUtil {
    public static String getToken(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("token","");
    }

    public static String getUserId(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId","");
    }

    public static String getUserName(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }

    public static String getNickName(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("nickName","");
    }


    public static String getImgBitMap(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("imgBitMap","");
    }

    public static String getUImage(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("uImage","");
    }

    public static String getBrith(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("brith","");
    }

    public static String getSex(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("sex","");
    }

    public static String getLocation(){
        SharedPreferences sharedPreferences = UniteApp.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("location","");
    }

    public static boolean putImage(String bitmap){
        SharedPreferences.Editor sEditor =  UniteApp.getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        sEditor.putString("imgBitMap",bitmap);
        sEditor.apply();
        return true;
    }


}
