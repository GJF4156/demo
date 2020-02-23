package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.IconFont.FontIconView;

public class RecoveryActivity extends AppCompatActivity implements View.OnClickListener {
    private FontIconView newspaper_icon;
    private FontIconView zhixiang_icon;
    private FontIconView dianqi_icon;
    private FontIconView suliao_icon;
    private FontIconView wanju_icon;
    private FontIconView baoxianhe_icon;
    private FontIconView jiuping_icon;
    private FontIconView boli_icon;
    private Button recovery_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        init();
    }

    private void init() {
        newspaper_icon = findViewById(R.id.newspaper_icon);
        zhixiang_icon = findViewById(R.id.zhixiang_icon);
        dianqi_icon = findViewById(R.id.dianqi_icon);
        suliao_icon = findViewById(R.id.suliao_icon);
        wanju_icon = findViewById(R.id.wanju_icon);
        baoxianhe_icon = findViewById(R.id.baoxianhe_icon);
        jiuping_icon = findViewById(R.id.jiuping_icon);
        boli_icon = findViewById(R.id.boli_icon);
        recovery_button = findViewById(R.id.recovery_button);

        newspaper_icon.setOnClickListener(this);
        zhixiang_icon.setOnClickListener(this);
        dianqi_icon.setOnClickListener(this);
        suliao_icon.setOnClickListener(this);
        wanju_icon.setOnClickListener(this);
        baoxianhe_icon.setOnClickListener(this);
        jiuping_icon.setOnClickListener(this);
        boli_icon.setOnClickListener(this);
        recovery_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newspaper_icon:
                Toast.makeText(RecoveryActivity.this,"报纸",Toast.LENGTH_SHORT).show();
                break;
            case R.id.zhixiang_icon:
                Toast.makeText(RecoveryActivity.this,"纸箱",Toast.LENGTH_SHORT).show();
                break;
            case R.id.dianqi_icon:
                Toast.makeText(RecoveryActivity.this,"电器",Toast.LENGTH_SHORT).show();
                break;
            case R.id.suliao_icon:
                Toast.makeText(RecoveryActivity.this,"塑料",Toast.LENGTH_SHORT).show();
                break;
            case R.id.wanju_icon:
                Toast.makeText(RecoveryActivity.this,"玩具",Toast.LENGTH_SHORT).show();
                break;
            case R.id.baoxianhe_icon:
                Toast.makeText(RecoveryActivity.this,"保鲜盒",Toast.LENGTH_SHORT).show();
                break;
            case R.id.jiuping_icon:
                Toast.makeText(RecoveryActivity.this,"酒瓶",Toast.LENGTH_SHORT).show();
                break;
            case R.id.boli_icon:
                Toast.makeText(RecoveryActivity.this,"玻璃",Toast.LENGTH_SHORT).show();
                break;
            case R.id.recovery_button:
                Toast.makeText(RecoveryActivity.this,"上门服务",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
