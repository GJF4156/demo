package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Fragment.AboutFragment;
import com.example.demo.Fragment.FavoriteFragment;
import com.example.demo.Fragment.IntegralFragment;
import com.example.demo.Fragment.MoreFragment;
import com.example.demo.Fragment.NoticeFragment;
import com.example.demo.Fragment.PersonalInfoFragment;
import com.example.demo.Fragment.ProductInfoFragment;
import com.example.demo.Fragment.SettingFragment;
import com.example.demo.Fragment.WebFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;

import org.xutils.DbManager;

import java.util.Objects;

import static android.view.View.GONE;

public class ContentActivity extends AppCompatActivity {
    private ProductInfoFragment productInfoFragment;
    private MoreFragment moreFragment;
    private PersonalInfoFragment personalInfoFragment;
    private TextView btBack;
    private FavoriteFragment favoriteFragment;
    private IntegralFragment integralFragment;
    private NoticeFragment noticeFragment;
    private SettingFragment settingFragment;
    private AboutFragment aboutFragment;
    private WebFragment webFragment;

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
            case 2:
                personalInfoFragment=new PersonalInfoFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,personalInfoFragment)
                        .commitAllowingStateLoss();
                break;
            case 3:
                favoriteFragment = new FavoriteFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,favoriteFragment)
                        .commitAllowingStateLoss();
                break;
            case 4:
                integralFragment = new IntegralFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,integralFragment)
                        .commitAllowingStateLoss();
                break;
            case 5:
                noticeFragment = new NoticeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,noticeFragment)
                        .commitAllowingStateLoss();
                break;
            case 6:
                settingFragment = new SettingFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,settingFragment)
                        .commitAllowingStateLoss();
                break;
            case 7:
                aboutFragment = new AboutFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,aboutFragment)
                        .commitAllowingStateLoss();
                break;
            case 8:
                String url = intent.getStringExtra("Url");
                webFragment = new WebFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url);
                webFragment.setArguments(bundle1);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, webFragment)
                        .commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

}
