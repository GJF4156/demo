package com.example.demo.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.SortsBean;
import com.example.demo.myView.CustomDialog;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {
    private String name, url, url1;
    private List<SortsBean.DatalistBean> dataBeansList;
    private RecyclerView SpeachSearchRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_speach_search, container, false);
        SpeachSearchRecyclerview = view.findViewById(R.id.speach_search_recyclerview);
        url1 = "http://129.211.75.130:8080/demo/garbage/listGarbage?name=";
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        name = bundle.getString("name");
        //设置recyclerview的布局管理器
        SpeachSearchRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        url = url1 + name;
        loadData(url,0);
        url = url1;
        return view;
    }

    /**
     * 请求成功回调的方法，对请求的数据进行进一步处理
     *
     * @param result 请求的结果
     */
    @Override
    public void onSuccess(String result) {
        SortsBean sortsBean = new Gson().fromJson(result, SortsBean.class);
        if (sortsBean.getCode() == 200) {
            dataBeansList = sortsBean.getDatalist();
            SpeachSearchRecyclerview.setAdapter(new SearchRvAdapter(getActivity(), dataBeansList, new SearchRvAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    toshowDialog(dataBeansList, position);
                }
            }));
        } else {
            Toast.makeText(getActivity(), "没有搜索到或暂未收入......", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示dialog的方法
     *
     * @param mStrs    数据源列表
     * @param position 列表下标
     */
    public void toshowDialog(List<SortsBean.DatalistBean> mStrs, int position) {
        CustomDialog dialog = new CustomDialog(getActivity(), R.style.custom_dialog);
        dialog.setName(mStrs.get(position).getGname())
                .setDescription(mStrs.get(position).getExplain())
                .setChangjian("常见包括")
                .setChangjiancontent(mStrs.get(position).getContain())
                .setDelivery("投放要求")
                .setDeliverycontent(mStrs.get(position).getTip());
        switch (mStrs.get(position).getGtype()) {
            case 0:
                //可回收垃圾
                dialog.setIcon(R.string.recyclableFont);
                dialog.setCardVieDgColor(getActivity().getColor(R.color.recyclableFontColor));
                dialog.setIconColor(getActivity().getColor(R.color.recyclableFontColor));
                dialog.setSortNameColor(getActivity().getColor(R.color.recyclableFontColor));
                dialog.setChangJianColor(getActivity().getColor(R.color.recyclableFontColor));
                dialog.setDeliveryColor(getActivity().getColor(R.color.recyclableFontColor));
                dialog.setSortname("可回收垃圾");
                break;
            case 1:
                //有害垃圾
                dialog.setIcon(R.string.hazardousFont);
                dialog.setCardVieDgColor(getActivity().getColor(R.color.hazardousFontColor));
                dialog.setIconColor(getActivity().getColor(R.color.hazardousFontColor));
                dialog.setSortNameColor(getActivity().getColor(R.color.hazardousFontColor));
                dialog.setChangJianColor(getActivity().getColor(R.color.hazardousFontColor));
                dialog.setDeliveryColor(getActivity().getColor(R.color.hazardousFontColor));
                dialog.setSortname("有害垃圾");
                break;
            case 2:
                //厨余垃圾
                dialog.setIcon(R.string.kitchenFont);
                dialog.setCardVieDgColor(getActivity().getColor(R.color.kitchenFontColor));
                dialog.setIconColor(getActivity().getColor(R.color.kitchenFontColor));
                dialog.setSortNameColor(getActivity().getColor(R.color.kitchenFontColor));
                dialog.setChangJianColor(getActivity().getColor(R.color.kitchenFontColor));
                dialog.setDeliveryColor(getActivity().getColor(R.color.kitchenFontColor));
                dialog.setSortname("厨余垃圾");
                break;
            case 3:
                //其他垃圾
                dialog.setIcon(R.string.otherFont);
                dialog.setCardVieDgColor(getActivity().getColor(R.color.otherFontColor));
                dialog.setIconColor(getActivity().getColor(R.color.otherFontColor));
                dialog.setSortNameColor(getActivity().getColor(R.color.otherFontColor));
                dialog.setChangJianColor(getActivity().getColor(R.color.otherFontColor));
                dialog.setDeliveryColor(getActivity().getColor(R.color.otherFontColor));
                dialog.setSortname("其他垃圾");
                break;
            default:
                break;
        }
        dialog.show();
    }
}
