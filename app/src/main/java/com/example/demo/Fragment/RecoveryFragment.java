package com.example.demo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryFragment extends Fragment implements View.OnClickListener {
    private FontIconView newspaper_icon;
    private FontIconView zhixiang_icon;
    private FontIconView dianqi_icon;
    private FontIconView suliao_icon;
    private FontIconView wanju_icon;
    private FontIconView baoxianhe_icon;
    private FontIconView jiuping_icon;
    private FontIconView boli_icon;
    private Button recovery_button;
    private TextView tv_title, bt_header_right;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery, container, false);
        init(view);
        initData();
        return view;
    }

    private void initData() {
        tv_title.setText("预约服务");
        bt_header_right.setText("查看");
    }

    private void init(View view) {

        newspaper_icon = view.findViewById(R.id.newspaper_icon);
        zhixiang_icon = view.findViewById(R.id.zhixiang_icon);
        dianqi_icon = view.findViewById(R.id.dianqi_icon);
        suliao_icon = view.findViewById(R.id.suliao_icon);
        wanju_icon = view.findViewById(R.id.wanju_icon);
        baoxianhe_icon = view.findViewById(R.id.baoxianhe_icon);
        jiuping_icon = view.findViewById(R.id.jiuping_icon);
        boli_icon = view.findViewById(R.id.boli_icon);
        recovery_button = view.findViewById(R.id.recovery_button);
        //toolBar
        tv_title = getActivity().findViewById(R.id.tv_title);
        bt_header_right = getActivity().findViewById(R.id.bt_header_right);

        bt_header_right.setOnClickListener(this);

        newspaper_icon.setOnClickListener(this);
        zhixiang_icon.setOnClickListener(this);
        dianqi_icon.setOnClickListener(this);
        suliao_icon.setOnClickListener(this);
        wanju_icon.setOnClickListener(this);
        baoxianhe_icon.setOnClickListener(this);
        jiuping_icon.setOnClickListener(this);
        boli_icon.setOnClickListener(this);
        recovery_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_header_right:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.web_content_frameLayout,new mRecoverFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
            case R.id.newspaper_icon:
                Toast.makeText(getActivity(), "报纸", Toast.LENGTH_SHORT).show();
                break;
            case R.id.zhixiang_icon:
                Toast.makeText(getActivity(), "纸箱", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dianqi_icon:
                Toast.makeText(getActivity(), "电器", Toast.LENGTH_SHORT).show();
                break;
            case R.id.suliao_icon:
                Toast.makeText(getActivity(), "塑料", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wanju_icon:
                Toast.makeText(getActivity(), "玩具", Toast.LENGTH_SHORT).show();
                break;
            case R.id.baoxianhe_icon:
                Toast.makeText(getActivity(), "保鲜盒", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jiuping_icon:
                Toast.makeText(getActivity(), "酒瓶", Toast.LENGTH_SHORT).show();
                break;
            case R.id.boli_icon:
                Toast.makeText(getActivity(), "玻璃", Toast.LENGTH_SHORT).show();
                break;
            case R.id.recovery_button:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.web_content_frameLayout, new RecoverInfoFragment())
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
                break;
        }

    }
}
