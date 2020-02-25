package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.Fragment.ProductInfoFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;

public class ContentActivity extends AppCompatActivity {
    private ProductInfoFragment productInfoFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        productInfoFragment=new ProductInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pid",intent.getStringExtra("pid"));
        productInfoFragment.setArguments(bundle);//数据传递到fragment中
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frameLayout,productInfoFragment).commitAllowingStateLoss();
        toolbar=findViewById(R.id.toolBar);
        toolbar.setTitle(name);
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary2));
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
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
