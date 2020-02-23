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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.Fragment.Presenter.IFragmentSortP;
import com.example.demo.Fragment.Presenter.impl.FragmentSortPImpl;
import com.example.demo.Fragment.View.IFragmentSortV;
import com.example.demo.R;
import com.example.demo.Utils.DrawableUtils;
import com.example.demo.activity.SearchActivity;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.SortsBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends BaseFragment implements View.OnClickListener, IFragmentSortV {

    private TextView searchtv;
    private ImageView kehuishoulaji, chuyulaji, youhailaji, qitalaji;
    private List<SortsBean.DatalistBean> resultBeanList;
    private String url;
    private IFragmentSortP mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        mPresenter = new FragmentSortPImpl(this);
        init(view);
        return view;
    }

    /**
     * 初始化数据
     *
     * @param view
     */
    private void init(View view) {
        kehuishoulaji = view.findViewById(R.id.kehuishoulaji);
        chuyulaji = view.findViewById(R.id.chuyulaji);
        youhailaji = view.findViewById(R.id.youhailaji);
        qitalaji = view.findViewById(R.id.qitalaji);
        searchtv = view.findViewById(R.id.searchtv);

        kehuishoulaji.setImageDrawable(DrawableUtils.ZoomDrawable(getResources().getDrawable(R.drawable.kehuishoulaji), 2300, 3800));
        chuyulaji.setImageDrawable(DrawableUtils.ZoomDrawable(getResources().getDrawable(R.drawable.chuyulaji), 2300, 3800));
        youhailaji.setImageDrawable(DrawableUtils.ZoomDrawable(getResources().getDrawable(R.drawable.youhailaji), 2300, 3800));
        qitalaji.setImageDrawable(DrawableUtils.ZoomDrawable(getResources().getDrawable(R.drawable.qitalaji), 2300, 3800));

        searchtv.setOnClickListener(this);
        kehuishoulaji.setOnClickListener(this);
        chuyulaji.setOnClickListener(this);
        youhailaji.setOnClickListener(this);
        qitalaji.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kehuishoulaji:
                mPresenter.getData(0);
                break;
            case R.id.chuyulaji:
                mPresenter.getData(2);
                break;
            case R.id.youhailaji:
                mPresenter.getData(1);
                break;
            case R.id.qitalaji:
                mPresenter.getData(3);
                break;
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
