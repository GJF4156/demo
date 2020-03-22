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
public class MyOrderFragment extends Fragment {
    private TextView tv_title, bt_header_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        tv_title.setText("商品订单");
        bt_header_right.setText("");

    }

    private void initView(View view) {
        tv_title = getActivity().findViewById(R.id.tv_title);
        bt_header_right = getActivity().findViewById(R.id.bt_header_right);
    }

}
