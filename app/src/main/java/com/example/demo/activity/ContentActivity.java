package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.demo.Fragment.AboutFragment;
import com.example.demo.Fragment.FavoriteFragment;
import com.example.demo.Fragment.WalletFragment;
import com.example.demo.Fragment.MainSearchFragment;
import com.example.demo.Fragment.MoreFragment;
import com.example.demo.Fragment.MyOrderFragment;
import com.example.demo.Fragment.NoticeFragment;
import com.example.demo.Fragment.PersonalInfoFragment;
import com.example.demo.Fragment.ProductInfoFragment;
import com.example.demo.Fragment.SettingFragment;
import com.example.demo.Fragment.WebFragment;
import com.example.demo.R;

public class ContentActivity extends AppCompatActivity {
    private ProductInfoFragment productInfoFragment;
    private MoreFragment moreFragment;
    private PersonalInfoFragment personalInfoFragment;
    private TextView btBack;
    private FavoriteFragment favoriteFragment;
    private WalletFragment walletFragment;
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
                Bundle bundle = new Bundle();
                bundle.putString("type","0");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, moreFragment)
                        .commitAllowingStateLoss();
                break;
            case 1:
                productInfoFragment = new ProductInfoFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("pid", intent.getStringExtra("pid"));
                productInfoFragment.setArguments(bundle2);//数据传递到fragment中
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
                walletFragment = new WalletFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout, walletFragment)
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
            case 9:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,new MainSearchFragment())
                        .commitAllowingStateLoss();
                break;
            case 10:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,new MyOrderFragment())
                        .commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

}
