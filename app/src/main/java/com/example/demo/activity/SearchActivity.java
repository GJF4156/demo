package com.example.demo.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.Utils.DividerItemDecoration;
import com.example.demo.activity.presenter.SearchPres;
import com.example.demo.activity.presenter.impl.SearchPresImpl;
import com.example.demo.adapter.RvAdapter;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.base.BaseActivity;
import com.example.demo.beans.SortsBean;
import com.example.demo.myView.CustomDialog;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_search)//相当于setContentView(R.layout.activity_search);
public class SearchActivity extends BaseActivity implements com.example.demo.activity.view.SearchView {
    @ViewInject(R.id.recyclerview)//注解形式初始化组建，类似于findViewById();
    private RecyclerView recyclerView;
    @ViewInject(R.id.searchView)
    private SearchView searchView;
    private String text;
    private List<SortsBean.DatalistBean> mStrs = null;//可通过获取网络数据进行实例化,保存请求来的数据
    private SearchPres mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mPresenter=new SearchPresImpl(this);
        //设置recyclerview的布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        //设置recyclerview每项的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //设置searchview的监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {//点击 搜索 按钮后出发的事件
                text=query;
                if (!query.isEmpty()) {
                    mPresenter.getData(query);
                    showDialog();
                    return true;
                } else {
                    Toast.makeText(SearchActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            //当输入框内容变化时调用的方法
            @Override
            public boolean onQueryTextChange(String newText) {//输入框内文本变化时触发的事件
                text=newText;
                if (!newText.isEmpty()) {
                    mPresenter.getData(newText);
                } else {
                }
                return false;
            }
        });
    }
    private void showDialog() {
        Dialog dialog = new Dialog(SearchActivity.this, R.style.custom_dialog);
        //设置布局
        dialog.setContentView(R.layout.dialog_rv_layout);
        RecyclerView rv = dialog.findViewById(R.id.dialog_rv);
        LinearLayoutManager ms = new LinearLayoutManager(SearchActivity.this);
        ms.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(ms);
        rv.setAdapter(new SearchRvAdapter(SearchActivity.this, mStrs, new SearchRvAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        }));
        WindowManager manager = getWindow().getWindowManager();
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
    /**
     * 显示自定义dialog的方法
     *
     * @param mStrs    数据源列表
     * @param position 列表下标
     */
    public void toshowDialog(List<SortsBean.DatalistBean> mStrs, int position) {
        CustomDialog dialog = new CustomDialog(SearchActivity.this, R.style.custom_dialog);
        dialog.setName(mStrs.get(position).getGname())
                .setDescription(mStrs.get(position).getExplain())
                .setChangjian("常见包括")
                .setChangjiancontent(mStrs.get(position).getContain())
                .setDelivery("投放要求")
                .setDeliverycontent(mStrs.get(position).getTip());
        /**
         * 根据类型的不同显示不同的结果
         */
        switch (mStrs.get(position).getGtype()) {
            case 0:
                //可回收垃圾
                dialog.setIcon(R.string.recyclableFont);
                dialog.setCardVieDgColor(getColor(R.color.recyclableFontColor));
                dialog.setIconColor(getColor(R.color.recyclableFontColor));
                dialog.setSortNameColor(getColor(R.color.recyclableFontColor));
                dialog.setChangJianColor(getColor(R.color.recyclableFontColor));
                dialog.setDeliveryColor(getColor(R.color.recyclableFontColor));
                dialog.setSortname("可回收垃圾");
                break;
            case 1:
                //有害垃圾
                dialog.setIcon(R.string.hazardousFont);
                dialog.setCardVieDgColor(getColor(R.color.hazardousFontColor));
                dialog.setIconColor(getColor(R.color.hazardousFontColor));
                dialog.setSortNameColor(getColor(R.color.hazardousFontColor));
                dialog.setChangJianColor(getColor(R.color.hazardousFontColor));
                dialog.setDeliveryColor(getColor(R.color.hazardousFontColor));
                dialog.setSortname("有害垃圾");
                break;
            case 2:
                //厨余垃圾
                dialog.setIcon(R.string.kitchenFont);
                dialog.setCardVieDgColor(getColor(R.color.kitchenFontColor));
                dialog.setIconColor(getColor(R.color.kitchenFontColor));
                dialog.setSortNameColor(getColor(R.color.kitchenFontColor));
                dialog.setChangJianColor(getColor(R.color.kitchenFontColor));
                dialog.setDeliveryColor(getColor(R.color.kitchenFontColor));
                dialog.setSortname("厨余垃圾");
                break;
            case 3:
                //其他垃圾
                dialog.setIcon(R.string.otherFont);
                dialog.setCardVieDgColor(getColor(R.color.otherFontColor));
                dialog.setIconColor(getColor(R.color.otherFontColor));
                dialog.setSortNameColor(getColor(R.color.otherFontColor));
                dialog.setChangJianColor(getColor(R.color.otherFontColor));
                dialog.setDeliveryColor(getColor(R.color.otherFontColor));
                dialog.setSortname("其他垃圾");
                break;
            default:
                break;
        }
        dialog.show();
    }

    @Override
    public void getData(List<SortsBean.DatalistBean> dataList) {
        mStrs=dataList;
        if (mStrs.size() > 0) {
            //设置recyclerview的适配器，并设置点击事件
            recyclerView.setAdapter(new RvAdapter(SearchActivity.this, mStrs, new RvAdapter.OnItemClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(int position) {
                    //显示dialog
                    toshowDialog(mStrs, position);
                }
            }));
        }else {
            SortsBean.DatalistBean datalistBean=new SortsBean.DatalistBean();
            datalistBean.setGname(text);
            datalistBean.setGtype(4);
            datalistBean.setAipre(2);
            datalistBean.setExplain("输入错误或暂未收录");
            datalistBean.setContain("输入错误或暂未收录");
            datalistBean.setTip("输入错误或暂未收录");
            mStrs.add(datalistBean);
            recyclerView.setAdapter(new RvAdapter(SearchActivity.this, mStrs, new RvAdapter.OnItemClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(int position) {

                }
            }));
        }
    }
}
