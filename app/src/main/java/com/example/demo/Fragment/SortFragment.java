package com.example.demo.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.demo.Fragment.Presenter.IFragmentSortP;
import com.example.demo.Fragment.Presenter.impl.FragmentSortPImpl;
import com.example.demo.Fragment.View.IFragmentSortV;
import com.example.demo.R;
import com.example.demo.activity.SearchActivity;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.adapter.SortMenuAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.SortCard;
import com.example.demo.beans.SortsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends BaseFragment implements View.OnClickListener, IFragmentSortV {

    private TextView searchtv;
    private IFragmentSortP mPresenter;
    private RecyclerView sort_menu;
    private List<SortCard> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        mPresenter = new FragmentSortPImpl(this);
        init(view);
        initData();
        return view;
    }

    private void initData() {
        SortCard sortCard1 = new SortCard();
        sortCard1.setSortIcon(getActivity().getString(R.string.recyclableFont));
        sortCard1.setSortName("可回收垃圾");
        sortCard1.setSortDescrip("指适宜回收可循环利用的废弃物");
        sortCard1.setContent1("投放时尽量保持清洁干燥，避免污染");
        sortCard1.setContent2("有内容物的，应清空并清洁后压扁投放");
        sortCard1.setContent3("易碎尖锐的，应包裹后投放");
        list.add(sortCard1);
        SortCard sortCard2 = new SortCard();
        sortCard2.setSortIcon(getActivity().getString(R.string.hazardousFont));
        sortCard2.setSortName("有害垃圾");
        sortCard2.setSortDescrip("指对人体健康或者自然环境造成直接或潜在危害的废弃物");
        sortCard2.setContent1("分类投放有害垃圾时，应注意轻放");
        sortCard2.setContent2("压力罐装容器，应排空内容物后投放");
        sortCard2.setContent3("易碎尖锐的，应包裹后投放");
        list.add(sortCard2);
        SortCard sortCard3 = new SortCard();
        sortCard3.setSortIcon(getActivity().getString(R.string.kitchenFont));
        sortCard3.setSortName("厨余垃圾");
        sortCard3.setSortDescrip("指易腐垃圾，易腐生物质生活废弃物");
        sortCard3.setContent1("沥干水分");
        sortCard3.setContent2("有外包装的应去除外包装");
        sortCard3.setContent3("与其他种类垃圾分开投放");
        list.add(sortCard3);
        SortCard sortCard4 = new SortCard();
        sortCard4.setSortIcon(getActivity().getString(R.string.otherFont));
        sortCard4.setSortName("其他垃圾");
        sortCard4.setSortDescrip("除其他三类垃圾外的生活废弃物");
        sortCard4.setContent1("易碎或已污染的，应包裹后投放");
        sortCard4.setContent2("投放时，应保持周边环境整洁");
        sortCard4.setContent3("与其他种类垃圾分开投放");
        list.add(sortCard4);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sort_menu.setLayoutManager(linearLayoutManager);
        sort_menu.setAdapter(new SortMenuAdapter(getActivity(), list, new SortMenuAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                mPresenter.getData(position);
            }
        }));

    }

    /**
     * 初始化数据
     *
     * @param view
     */
    private void init(View view) {
        searchtv = view.findViewById(R.id.searchtv);
        sort_menu = view.findViewById(R.id.sort_menu);
        searchtv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchtv:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 显示数据
     *
     * @param data
     */
    private void ShowData(List<SortsBean.DatalistBean> data) {
        Dialog dialog = new Dialog(getActivity(), R.style.custom_dialog);
        //设置布局
        dialog.setContentView(R.layout.dialog_rv_layout);
        RecyclerView rv = dialog.findViewById(R.id.dialog_rv);
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(ms);
        rv.setAdapter(new SearchRvAdapter(getActivity(), data, new SearchRvAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        }));
        WindowManager manager = getActivity().getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        dialog.show();
        // 设置宽度
        Point size = new Point();
        display.getSize(size);
        // 宽度为当前屏幕的90%
        params.width = (int) (size.x * 0.95);
        params.height = (int) (size.y * 0.9);
        dialog.getWindow().setAttributes(params);
    }

    @Override
    public void getData(List<SortsBean.DatalistBean> resultBeanList) {
        ShowData(resultBeanList);
    }
}
