package com.example.demo.Login.fragment.presenter.impl;

import com.example.demo.Login.fragment.model.IRegisterModel;
import com.example.demo.Login.fragment.model.impl.RegisterModelImpl;
import com.example.demo.Login.fragment.presenter.IRegisterPresenter;
import com.example.demo.Login.fragment.view.IRegisterView;

public class RegisterPresenterImpl implements IRegisterPresenter {

    private IRegisterModel mIRegisterModel;
    private IRegisterView mIRegisterView;

    public RegisterPresenterImpl(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
        mIRegisterModel = new RegisterModelImpl(this);
    }

    @Override
    public void getRegisterInfo(String userId, String psd) {
        mIRegisterModel.startRegister(userId,psd);
    }

    @Override
    public void getStatus(int status, String msg) {
        switch (status) {
            case 100:
                mIRegisterView.onSuccess();
                break;
            case 200:
                mIRegisterView.onFailed(msg);
                break;
            default:
                break;
        }
    }
}
