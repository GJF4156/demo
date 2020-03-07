package com.example.demo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.adapter.ShoppingCartAdapter;
import com.example.demo.beans.ShoppingCartBean;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends Fragment implements View.OnClickListener, ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {
    private TextView tvTitle, btHeaderRight;
    private DbManager db;
    private static final String TAG = "ShoppingCartActivity";
    //全选
    private CheckBox ckAll;
    //总额
    private TextView tvShowPrice;
    //结算
    private TextView tvSettlement;
    ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        initDb();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        try {
            shoppingCartBeanList = db.findAll(ShoppingCartBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        list_shopping_cart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);


    }

    private void initView(View view) {
        tvTitle = getActivity().findViewById(R.id.tv_title);
        btHeaderRight = getActivity().findViewById(R.id.bt_header_right);
        ckAll = view.findViewById(R.id.ck_all);
        tvShowPrice = view.findViewById(R.id.tv_show_price);
        tvSettlement = view.findViewById(R.id.tv_settlement);
        list_shopping_cart = view.findViewById(R.id.list_shopping_cart);

        tvTitle.setText("购物车");
        btHeaderRight.setVisibility(view.VISIBLE);
        ckAll.setOnClickListener(this);
        btHeaderRight.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
    }

    private void initDb() {
        //本地数据的初始化
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("shoppingCar") //设置数据库名
                .setDbVersion(1) //设置数据库版本
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                        //开启WAL, 对写入加速提升巨大(作者原话)
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //数据库升级操作
                    }
                });
        db = x.getDb(daoConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.bt_header_right:
                flag = !flag;
                if (flag) {
                    btHeaderRight.setText("完成");
                    shoppingCartAdapter.isShow(false);
                } else {
                    btHeaderRight.setText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
                break;
            case R.id.tv_settlement: //结算
                lementOnder();
                break;
            default:
                break;
        }
    }

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        List<ShoppingCartBean> list = new ArrayList<>();
        //选中的需要提交的商品清单
        for (ShoppingCartBean bean : shoppingCartBeanList) {
            boolean choosed = bean.isChoosed();
            if (choosed) {
                list.add(bean);
//                String shoppingName = bean.getShoppingName();
//                int count = bean.getCount();
//                double price = bean.getPrice();
//                int size = bean.getDressSize();
//                int id = bean.getId();
            }
        }
        if (list.size() > 0) {
            PayFragment payFragment = new PayFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("shoppingCartBeanList", (Serializable) list);
            payFragment.setArguments(bundle);
            //跳转到支付界面
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .hide(this)
                    .add(R.id.content_frameLayout, payFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        } else {
            Toast.makeText(getActivity(), "你还没有选择商品哟！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tvShowPrice.setText("合计:" + totalPrice);
        tvSettlement.setText("结算(" + totalCount + ")");
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删减
     * *
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        try {
            db.delete(shoppingCartBeanList.get(position));
        } catch (DbException e) {
            e.printStackTrace();
        }
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }
}
