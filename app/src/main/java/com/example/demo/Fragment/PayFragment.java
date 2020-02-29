package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.beans.ShoppingCartBean;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment {
    private TextView payText,tvTitle,btHeaderRight;


    public PayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        payText=view.findViewById(R.id.pay_text);
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        tvTitle.setText("支付");
        List<ShoppingCartBean> list = (List<ShoppingCartBean>) getArguments().getSerializable("shoppingCartBeanList");
            payText.setText(list.get(0).getShoppingName()+list.get(0).getPrice());
        return view;
    }

}
