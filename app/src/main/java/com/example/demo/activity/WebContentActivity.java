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
    private TextView btn_back, tv_title, bt_header_right;
    private RecoveryFragment recoveryFragment;
    private List<Fragment> fragmentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_content);
        initView();
        initData();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.web_content_frameLayout, recoveryFragment)
                .commitAllowingStateLoss();
    }

    private void initData() {
        tv_title.setText("预约服务");
        bt_header_right.setText("查看");
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        tv_title = findViewById(R.id.tv_title);
        bt_header_right = findViewById(R.id.bt_header_right);
        btn_back.setOnClickListener(this);
        bt_header_right.setOnClickListener(this);
        recoveryFragment = new RecoveryFragment();
        fragmentsList.add(recoveryFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.bt_header_right:
                Toast.makeText(WebContentActivity.this, "查看预约订单", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
