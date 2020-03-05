package com.example.demo.Fragment;


import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.adapter.FavoriteAdapter;
import com.example.demo.adapter.NewsAdapter;
import com.example.demo.beans.NewsBeans;
import com.example.demo.beans.NewsData;

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
    private List<NewsBeans.NewslistBean> newslistBeans = new ArrayList<>();
    private DbManager db;
    private WebFragment webFragment;

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

    private void initData() {
        tvTitle.setText("收藏");
        btHeaderRight.setVisibility(View.GONE);
        favoriteRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置recyclerview每项的分割线
        favoriteRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //从数据库查询数据
        try {
            List<NewsData> all = db.findAll(NewsData.class);
            favoriteRv.setAdapter(new FavoriteAdapter(getActivity(), all, new FavoriteAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    webFragment = new WebFragment();
                    Bundle bundle = new Bundle();
                    String url = all.get(position).getUrl();
                    bundle.putString("url", url);
                    webFragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frameLayout,webFragment)
                            .addToBackStack(null)
                            .commitAllowingStateLoss();
                }

                @Override
                public void onLongClick(int position) {
                    PopupMenu popupMenu = new PopupMenu(getContext(),getView());
                    popupMenu.getMenuInflater().inflate(R.menu.menu_item,popupMenu.getMenu());
                    //弹出式菜单的菜单项点击事件
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            try {
                                deleteData(position, all);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            }));
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
