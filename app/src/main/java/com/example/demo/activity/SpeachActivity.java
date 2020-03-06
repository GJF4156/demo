package com.example.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.BottomBarLayoutActivity;
import com.example.demo.Fragment.SpeachFragment;
import com.example.demo.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.BaseActivity;
import com.example.demo.beans.SortsBean;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.List;

public class SpeachActivity extends BaseActivity {
    private SpeachFragment speachFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speach);
        speachFragment = new SpeachFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.speach_content, speachFragment)
                .commitAllowingStateLoss();
    }
}
