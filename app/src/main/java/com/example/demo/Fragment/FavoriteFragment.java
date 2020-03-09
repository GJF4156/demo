package com.example.demo.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.adapter.RecyclerSlideAdapter;
import com.example.demo.beans.NewsData;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 收藏
 */
public class FavoriteFragment extends Fragment {
    private TextView tvTitle, btHeaderRight;
    private RecyclerView favoriteRv;
    private List<NewsData> newslistBeans = new ArrayList<>();
    private DbManager db;
    private RecyclerSlideAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        //初始化本地数据库
        initDb();
        initView(view);
        initData();
        return view;
    }

    //本地数据的初始化
    private void initDb() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("favorite") //设置数据库名
                .setDbVersion(1) //设置数据库版本
                .setDbOpenListener(db -> {
                    db.getDatabase().enableWriteAheadLogging();
                    //开启WAL, 对写入加速提升巨大(作者原话)
                })
                .setDbUpgradeListener((db, oldVersion, newVersion) -> {
                    //数据库升级操作
                });
        db = x.getDb(daoConfig);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initData() {
        tvTitle.setText("收藏");
        btHeaderRight.setVisibility(View.GONE);
        try {
            newslistBeans = db.findAll(NewsData.class);
            adapter = new RecyclerSlideAdapter(getActivity(),newslistBeans,true);
            adapter.setOnDelListener(new RecyclerSlideAdapter.onSlideListener() {

                //删除
                @Override
                public void onDel(int position) {
                    try {
                        deleteData(position, newslistBeans);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                //置顶
                @Override
                public void onTop(int position) {
                }
            });
            favoriteRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            favoriteRv.setAdapter(adapter);
            favoriteRv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                        if (null != viewCache) {
                            viewCache.smoothClose();
                        }
                    }
                    return false;
                }
            });
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void deleteData(int position, List<NewsData> all) throws DbException {
        db.delete(all.get(position));
        initData();
    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        favoriteRv = view.findViewById(R.id.favorite_rv);
    }

}
