package com.example.demo.Fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentHomeP;
import com.example.demo.Fragment.Presenter.impl.FragmentHomePImpl;
import com.example.demo.Fragment.View.IFragmentHomeV;
import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.ImageUtils;
import com.example.demo.activity.ContentActivity;
import com.example.demo.activity.SpeachActivity;
import com.example.demo.activity.WebActivity;
import com.example.demo.activity.WebContentActivity;
import com.example.demo.adapter.NewsAdapter;
import com.example.demo.adapter.RecyclerSlideAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.NewsBeans;
import com.example.demo.beans.NewsData;

import org.xutils.DbManager;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener, IFragmentHomeV {
    private Intent intent;
    private FontIconView center_ar_icon;
    private FontIconView center_voice_icon;
    private FontIconView center_recovery_icon;
    private RecyclerView NewsRv;
    private ImageView imageView;
    private RecyclerSlideAdapter adapter;
    private DbManager db;

    private IFragmentHomeP mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //初始化本地数据库
        initDb();
        mPresenter = new FragmentHomePImpl(this);
        mPresenter.getData(getActivity());
        //初始化数据
        init(view);
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

    private void init(View view) {
        imageView = view.findViewById(R.id.main_image);
        center_ar_icon = view.findViewById(R.id.center_ar_icon);
        center_voice_icon = view.findViewById(R.id.center_voice_icon);
        center_recovery_icon = view.findViewById(R.id.center_recovery_icon);
        NewsRv = view.findViewById(R.id.news_rv);
        //设置recyclerview的布局管理器
        NewsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        NewsRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        center_ar_icon.setOnClickListener(this);
        center_voice_icon.setOnClickListener(this);
        center_recovery_icon.setOnClickListener(this);

        Drawable drawable = getActivity().getDrawable(R.mipmap.main);
        //将Drawable转化为Bitmap
        Bitmap bitmap = ImageUtils.drawableToBitmap(drawable);
        //获取圆角图片
        Bitmap roundBitmap = ImageUtils.getRoundedCornerBitmap(bitmap, 25.0f);
        imageView.setImageBitmap(roundBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center_ar_icon:
                Toast.makeText(getActivity(), "点击了AR识别", Toast.LENGTH_SHORT).show();
                break;
            case R.id.center_voice_icon:
                startActivity(new Intent(getActivity(), SpeachActivity.class));
                break;
            case R.id.center_recovery_icon:
                intent = new Intent(getActivity(), WebContentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void getData(List<NewsBeans.NewslistBean> newslistBeanList) {
        NewsAdapter adapter = new NewsAdapter(getActivity(), newslistBeanList, false);
        adapter.setOnDelListener(new NewsAdapter.onSlideListener() {
            @Override
            public void onDel(int position) {
            }

            @Override
            public void onTop(int position) {
                NewsData newsData = new NewsData();
                newsData.setCtime(newslistBeanList.get(position).getCtime());
                newsData.setDescription(newslistBeanList.get(position).getDescription());
                newsData.setPicUrl(newslistBeanList.get(position).getPicUrl());
                newsData.setTitle(newslistBeanList.get(position).getTitle());
                newsData.setUrl(newslistBeanList.get(position).getUrl());
                try {
                    Selector<NewsData> newsDataSelector = db.selector(NewsData.class).where("title", "=", newsData.getTitle())
                            .orderBy("ctime");
                    if (newsDataSelector.count() > 0) {
                        Toast.makeText(getActivity(), "已经收藏过了", Toast.LENGTH_SHORT).show();
                    } else {
                        db.save(newsData);
                        Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
        NewsRv.setAdapter(adapter);
    }
}

