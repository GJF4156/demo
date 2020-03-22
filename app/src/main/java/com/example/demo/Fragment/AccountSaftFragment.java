package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.Utils.SPUtil;

/**
 * A simple {@link Fragment} subclass.
 * 账号与安全
 */
public class AccountSaftFragment extends Fragment {
    private TextView tvTitle,btHeaderRight,tv_saft_phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_saft, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tvTitle.setText("账户与安全");
        btHeaderRight.setVisibility(View.GONE);
        tv_saft_phone.setText(SPUtil.getPhoneNum());
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        tv_saft_phone=view.findViewById(R.id.tv_saft_phone);
    }

}
