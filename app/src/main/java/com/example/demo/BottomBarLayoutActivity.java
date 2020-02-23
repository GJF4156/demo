package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.demo.Fragment.HomeFragment;
import com.example.demo.Fragment.MeFragment;
import com.example.demo.Fragment.ShopFragment;
import com.example.demo.Fragment.SortFragment;
import com.example.demo.base.BaseActivity;
import com.example.demo.beans.NewsBeans;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BottomBarLayoutActivity extends BaseActivity {
    private List<Fragment> pageLists;
    private HomeFragment homeFragment;
    private ShopFragment shopFragment;
    private SortFragment sortFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar_layout);
        init();
    }

    private void init() {
        ViewPager viewPager = findViewById(R.id.vp_content);
        BottomBarLayout bottomBarLayout = findViewById(R.id.bbl);
        pageLists = new ArrayList<>();
        if (homeFragment != null) {
            pageLists.add(homeFragment);
        } else {
            homeFragment = new HomeFragment();
            pageLists.add(homeFragment);
        }
        if (sortFragment != null) {
            pageLists.add(sortFragment);
        } else {
            sortFragment = new SortFragment();
            pageLists.add(sortFragment);
        }
        if (shopFragment != null) {
            pageLists.add(shopFragment);
        } else {
            shopFragment = new ShopFragment();
            pageLists.add(shopFragment);
        }
        if (meFragment != null) {
            pageLists.add(meFragment);
        } else {
            meFragment = new MeFragment();
            pageLists.add(meFragment);
        }
        viewPager.setAdapter(new fragmentAdapter(getSupportFragmentManager()));
        bottomBarLayout.setViewPager(viewPager);
        //bottomBarLayout.setUnread(1, 101);//设置第二个页签的未读书
        bottomBarLayout.showNotify(2);//设置第三个页签显示提示的小红点
        //bottomBarLayout.setMsg(3, "NEW");//设置第四个页签显示NEW提示文字
        //设置选中项的监听事件
//        bottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(BottomBarItem bottomBarItem, int i) {
//                Toast.makeText(BottomBarLayoutActivity.this,"这是第"+i+"页",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public class fragmentAdapter extends FragmentPagerAdapter {
        public fragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return pageLists.get(position);
        }

        @Override
        public int getCount() {
            return pageLists.size();
        }

        /**
         * 注销destroyItem()方法，防止页面切换时，fragment被销毁而导致数据重新加载，
         * 这样点击底部导航图标就不会有请求数据而造成的延时
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
        }

        /**
         * 注销destroyItem()方法，防止页面切换时，fragment被销毁而导致数据重新加载，
         * 这样点击底部导航图标就不会有请求数据而造成的延时
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
