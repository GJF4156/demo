package com.example.demo.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.adapter.SearchRvAdapter;
import com.example.demo.base.BaseActivity;
import com.example.demo.beans.SortsBean;
import com.example.demo.myView.CustomDialog;
import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_speach_search)
public class SpeachSearchActivity extends BaseActivity {
    private String name, url, url1;
    private List<SortsBean.DatalistBean> dataBeansList;
    @ViewInject(R.id.speach_search_recyclerview)
    private RecyclerView SpeachSearchRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        url1 = "http://129.211.75.130:8080/demo/garbage/listGarbage?name=";
        try {
            Intent intent = getIntent();
            name = intent.getStringExtra("name");
        } catch (Exception e) {
            Log.i("animee", "程序出现问题了！！");
        }
        //设置recyclerview的布局管理器
        SpeachSearchRecyclerview.setLayoutManager(new LinearLayoutManager(SpeachSearchActivity.this));
        //设置recyclerview每项的分割线
//        SpeachSearchRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        url = url1 + name;
        loadData(url);
        url = url1;
    }

    /**
     * 书记请求成功回调的方法，对请求的数据进行进一步处理
     * @param result 请求的结果
     */
    @Override
    public void onSuccess(String result) {
        SortsBean sortsBean = new Gson().fromJson(result, SortsBean.class);
        if (sortsBean.getCode() == 200) {
            dataBeansList = sortsBean.getDatalist();
            SpeachSearchRecyclerview.setAdapter(new SearchRvAdapter(SpeachSearchActivity.this, dataBeansList, new SearchRvAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    toshowDialog(dataBeansList, position);
                }
            }));
        } else {
            Toast.makeText(SpeachSearchActivity.this, "没有搜索到或暂未收入......", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示dialog的方法
     *
     * @param mStrs    数据源列表
     * @param position 列表下标
     */
    public void toshowDialog(List<SortsBean.DatalistBean> mStrs, int position) {
        CustomDialog dialog = new CustomDialog(SpeachSearchActivity.this, R.style.custom_dialog);
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

    /**
     * 返回键触发的事件
     * 点击返回键时，跳转到指定的页面
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SpeachSearchActivity.this,SpeachActivity.class));
        finish();
    }
}
