package com.example.demo.Login.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Login.fragment.presenter.ILoginPresenter;
import com.example.demo.Login.fragment.presenter.impl.LoginPresenterImpl;
import com.example.demo.Login.fragment.view.ILoginView;
import com.example.demo.R;
import com.example.demo.activity.BottomBarLayoutActivity;
import com.example.demo.base.BaseFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private TextView forgetPsw, register;
    private EditText edit_login_phone, edit_login_psw;
    private Button login_btn;

    private ILoginPresenter mILoginPresenter;

    private RegisterFragment registerFragment;
    private String phoneNum, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mILoginPresenter = new LoginPresenterImpl(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        forgetPsw = view.findViewById(R.id.forgetPsw);
        register = view.findViewById(R.id.register);
        edit_login_phone = view.findViewById(R.id.edit_login_phone);
        edit_login_psw = view.findViewById(R.id.edit_login_psw);
        login_btn = view.findViewById(R.id.login_btn);

        forgetPsw.setOnClickListener(this);
        register.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetPsw:
                break;
            case R.id.register:
                registerFragment=new RegisterFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_content,registerFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.login_btn:
                phoneNum = edit_login_phone.getText().toString().trim();
                password = edit_login_psw.getText().toString().trim();
                boolean b = isPhone(phoneNum);
                if (TextUtils.isEmpty(phoneNum)) {
                    toast("手机号码不能为空");
                } else if (!isPhone(phoneNum)) {
                    toast("请输入正确的手机号码");
                } else if (TextUtils.isEmpty(password)) {
                    toast("请填写密码");
                } else if (password.length() < 4 || password.length() > 20) {
                    toast("密码为4-20位字符");
                } else {
                    mILoginPresenter.getUserInfo(phoneNum,password);
                }
                break;
        }
    }

    //验证手机号格式
    private boolean isPhone(String phoneNum) {
        String regex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNum);
        return m.find();
    }

    //toast
    private void toast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(getActivity(), BottomBarLayoutActivity.class));
        getActivity().finish();
    }

    @Override
    public void loginFailed(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String userId() {
        return edit_login_phone.getText().toString();
    }

    @Override
    public String password() {
        return edit_login_psw.getText().toString();
    }

    @Override
    public void showLoginServerError(String message) {
        Toast.makeText(getActivity(), "服务器异常"+message, Toast.LENGTH_SHORT).show();
    }
}
