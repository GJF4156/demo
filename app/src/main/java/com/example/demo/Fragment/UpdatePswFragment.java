package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePswFragment extends Fragment {
    private TextView tvTitle,btHeaderRight;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_psw, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tvTitle.setText("修改密码");
        btHeaderRight.setVisibility(View.GONE);

    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
    }

}
