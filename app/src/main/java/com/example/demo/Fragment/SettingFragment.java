package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * 设置
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private TextView tvTitle,btHeaderRight,tv_set_name,tv_set_saft;
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

        tv_set_name.setOnClickListener(this);
        tv_set_saft.setOnClickListener(this);
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
                Toast.makeText(getActivity(),"安全",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
