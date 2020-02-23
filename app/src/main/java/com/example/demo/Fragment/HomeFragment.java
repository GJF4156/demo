package com.example.demo.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.Fragment.Presenter.IFragmentHomeP;
import com.example.demo.Fragment.Presenter.impl.FragmentHomePImpl;
import com.example.demo.Fragment.View.IFragmentHomeV;
import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;
import com.example.demo.RecoveryActivity;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.Utils.ImageUtils;
import com.example.demo.activity.SpeachActivity;
import com.example.demo.activity.WebActivity;
import com.example.demo.adapter.NewsAdapter;
import com.example.demo.base.BaseFragment;
import com.example.demo.beans.NewsBeans;

import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener, IFragmentHomeV {
    private Intent intent;
    private FontIconView center_ar_icon;
    private FontIconView center_voice_icon;
    private FontIconView center_recovery_icon;
    private String url;
    private RecyclerView NewsRv;
    private ImageView NewsImg,imageView;
    private List<NewsBeans.NewslistBean> newslistBeanList;

    private IFragmentHomeP mPresenter;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mPresenter=new FragmentHomePImpl(this);
        mPresenter.getData();
        //初始化数据
        init(view);
        return view;
    }

    private void init(View view) {
        imageView = view.findViewById(R.id.main_image);
        center_ar_icon=view.findViewById(R.id.center_ar_icon);
        center_voice_icon=view.findViewById(R.id.center_voice_icon);
        center_recovery_icon=view.findViewById(R.id.center_recovery_icon);
        NewsRv=view.findViewById(R.id.news_rv);
        NewsImg=view.findViewById(R.id.news_img);

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
        switch (v.getId()){
            case R.id.center_ar_icon:
                Toast.makeText(getActivity(),"点击了AR识别",Toast.LENGTH_SHORT).show();
                break;
            case R.id.center_voice_icon:
                startActivity(new Intent(getActivity(), SpeachActivity.class));
                break;
            case  R.id.center_recovery_icon:
                intent=new Intent(getActivity(), RecoveryActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }

    @Override
    public void getData(List<NewsBeans.NewslistBean> newslistBeanList) {
        NewsRv.setAdapter(new NewsAdapter(getActivity(), newslistBeanList, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String Url=newslistBeanList.get(position).getUrl();
                Intent intent=new Intent(getActivity(), WebActivity.class);
                intent.putExtra("Url",Url);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }));
    }
}

