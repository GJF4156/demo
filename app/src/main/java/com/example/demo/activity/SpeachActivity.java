package com.example.demo.activity;

import android.os.Bundle;

import com.example.demo.Fragment.SpeachFragment;
import com.example.demo.R;
import com.example.demo.base.BaseActivity;

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
