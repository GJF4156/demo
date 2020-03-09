package com.example.demo.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chumu.dt.v24.permissions.DynamicPermissions;
import com.example.demo.Login.fragment.LoginFragment;
import com.example.demo.R;
import com.example.demo.activity.BottomBarLayoutActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.CHANGE_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_SETTINGS;

public class MainActivity extends AppCompatActivity {
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] str = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE,
                INTERNET, RECORD_AUDIO, ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE, CHANGE_NETWORK_STATE, READ_PHONE_STATE,
                READ_CONTACTS, READ_EXTERNAL_STORAGE, WRITE_SETTINGS, ACCESS_FINE_LOCATION, CAMERA, READ_SMS,RECEIVE_SMS
        };
        //调用本库方法(Call the library method)
        DynamicPermissions dynamicPermissions = new DynamicPermissions(MainActivity.this, str);
        //判断有没有权限,没有的话让他获取(Determine whether there is access, if not let him get)
        if (!dynamicPermissions.isFlag()) {
            dynamicPermissions.init();
            init();
        } else {
            //你想要进行的操作
            init();
        }


    }

    private void init() {
        loginFragment=new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_content,loginFragment)
                .commitAllowingStateLoss();
    }
}
