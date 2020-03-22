package com.example.demo.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Login.MainActivity;
import com.example.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * 设置
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private TextView tvTitle,btHeaderRight,tv_set_name,tv_set_saft,tv_set_psw;
    private Button btn_exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tvTitle.setText("设置");
        btHeaderRight.setVisibility(View.GONE);
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        tv_set_name=view.findViewById(R.id.tv_set_name);
        tv_set_saft=view.findViewById(R.id.tv_set_saft);
        tv_set_psw=view.findViewById(R.id.tv_set_psw);
        btn_exit=view.findViewById(R.id.btn_exit);

        tv_set_name.setOnClickListener(this);
        tv_set_saft.setOnClickListener(this);
        tv_set_psw.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_set_name:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,new PersonalInfoFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.tv_set_saft:
                //账号与安全
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,new AccountSaftFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.tv_set_psw:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frameLayout,new UpdatePswFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.btn_exit:
                Toast.makeText(getActivity(),"退出登陆",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().finish();
                break;
        }
    }
}
