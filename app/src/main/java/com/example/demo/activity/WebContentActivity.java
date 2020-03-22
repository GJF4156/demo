package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Fragment.RecoveryFragment;
import com.example.demo.R;

import java.util.ArrayList;
import java.util.List;

public class WebContentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btn_back;
    private RecoveryFragment recoveryFragment;
    private List<Fragment> fragmentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_content);
        initView();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.web_content_frameLayout, recoveryFragment)
                .commitAllowingStateLoss();
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        recoveryFragment = new RecoveryFragment();
        fragmentsList.add(recoveryFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
