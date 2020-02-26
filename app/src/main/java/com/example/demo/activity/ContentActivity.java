package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.Fragment.MoreFragment;
import com.example.demo.Fragment.ProductInfoFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;

import java.util.Objects;

public class ContentActivity extends AppCompatActivity {
    private ProductInfoFragment productInfoFragment;
    private MoreFragment moreFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        toolbar = findViewById(R.id.toolBar);
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary2));
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int a = Integer.parseInt(type);
        switch (a) {
            case 0:
                toolbar.setTitle("");
                moreFragment = new MoreFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, moreFragment)
                        .commitAllowingStateLoss();
                break;
            case 1:
                toolbar.setTitle("");
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
        setSupportActionBar(toolbar);
        //设置是否有NvagitionIcon（返回图标）
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
