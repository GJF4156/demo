package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment{
    private TextView tvTitle,btHeaderRight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String pid = bundle.getString("pid");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle=getActivity().findViewById(R.id.tv_title);
        btHeaderRight=getActivity().findViewById(R.id.bt_header_right);
        tvTitle.setText("购物车");
        btHeaderRight.setVisibility(view.VISIBLE);


    }
}
