package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Fragment.MoreFragment;
import com.example.demo.Fragment.ProductInfoFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;

import org.xutils.DbManager;

import java.util.Objects;

import static android.view.View.GONE;

public class ContentActivity extends AppCompatActivity {
    private ProductInfoFragment productInfoFragment;
    private MoreFragment moreFragment;
    private TextView btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        btBack = findViewById(R.id.btn_back);
        btBack.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int a = Integer.parseInt(type);
        switch (a) {
            case 0:
                moreFragment = new MoreFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, moreFragment)
                        .commitAllowingStateLoss();
                break;
            case 1:
                productInfoFragment = new ProductInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("pid", intent.getStringExtra("pid"));
                productInfoFragment.setArguments(bundle);//数据传递到fragment中
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, productInfoFragment)
                        .commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

}
