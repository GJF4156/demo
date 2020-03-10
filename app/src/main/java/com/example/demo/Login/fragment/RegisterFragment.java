package com.example.demo.Login.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Login.fragment.presenter.IRegisterPresenter;
import com.example.demo.Login.fragment.presenter.impl.RegisterPresenterImpl;
import com.example.demo.Login.fragment.view.IRegisterView;
import com.example.demo.R;
import com.mob.MobSDK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, IRegisterView {
    private EditText edit_register_phone,edit_register_psw,edit_register_yzcode;
    private Button send_msg_btn,register_btn;

    private IRegisterPresenter mIRegisterPresenter;

    private LoginFragment loginFragment;

    public EventHandler eh;
    private String phoneNum,password,checkcode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mIRegisterPresenter = new RegisterPresenterImpl(this);
        initView(view);
        MobSDK.init(getActivity());
        init();
        return view;
    }

    private void initView(View view) {
        edit_register_phone=view.findViewById(R.id.edit_register_phone);
        edit_register_psw=view.findViewById(R.id.edit_register_psw);
        edit_register_yzcode=view.findViewById(R.id.edit_register_yzcode);
        send_msg_btn=view.findViewById(R.id.send_msg_btn);
        register_btn=view.findViewById(R.id.register_btn);

        send_msg_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    //验证输入是否是电话号码格式
    private boolean isPhone(String phoneNum){
        String regex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNum);
        return m.find();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_msg_btn:
                getCheckCode();
                break;
            case R.id.register_btn:
                sendCheckCode();
                break;
        }
    }

    //获取验证码
    private void getCheckCode(){
        phoneNum = edit_register_phone.getText().toString().trim();
        password = edit_register_psw.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNum)){
            toast("手机号码不能为空");
        }else if(!isPhone(phoneNum)){
            toast("请输入正确的手机号码");
        }else if (TextUtils.isEmpty(password)){
            toast("请填写密码");
        }else if (password.length() < 4 || password.length() > 20){
            toast("密码为4-20位字符");
        }else {
            Log.d("myphone", phoneNum);
            SMSSDK.getVerificationCode("86", phoneNum);
            toast("发送成功！");
        }
    }

    //提交验证
    private void sendCheckCode(){
        checkcode = edit_register_yzcode.getText().toString().trim();
        if (TextUtils.isEmpty(checkcode)){
            toast("注册前请先验证");
        }else {
            SMSSDK.submitVerificationCode("86", phoneNum,checkcode);
            mIRegisterPresenter.getRegisterInfo(phoneNum,password);
            toast("注册成功");
        }
    }

    //初始化mob
    private void init(){
        phoneNum = edit_register_phone.getText().toString().trim();
        password = edit_register_psw.getText().toString().trim();
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){//回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        //回到登陆页面
                        onSuccess();
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh);
    }


    //toast
    private void toast(String content){
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onSuccess() {
        loginFragment=new LoginFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content,loginFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void inPswToast() {
        Toast.makeText(getActivity(), "密码不一致", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userIdToast() {
        Toast.makeText(getActivity(), "请输入您的手机号码", Toast.LENGTH_SHORT).show();
    }
}
