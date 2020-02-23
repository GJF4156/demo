package com.example.demo.myView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.demo.IconFont.FontIconView;
import com.example.demo.R;

public class CustomDialog extends Dialog {

    private TextView tv_name, tv_sort_name, tv_description, tv_changjian, changjian_content, tv_delivery, delivery_content;
    private FontIconView tv_icon;
    private CardView cardView;

    private String name, sortname, description, changjian, changjiancontent, delivery, deliverycontent;

    private int icon, cardVieDgColor,iconColor,sortNameColor,ChangJianColor,DeliveryColor,NameColor;

    public CustomDialog setNameColor(int nameColor) {
        NameColor = nameColor;
        return this;
    }

    public CustomDialog setIconColor(int iconColor) {
        this.iconColor = iconColor;
        return this;
    }

    public CustomDialog setSortNameColor(int sortNameColor) {
        this.sortNameColor = sortNameColor;
        return this;
    }

    public CustomDialog setChangJianColor(int changJianColor) {
        ChangJianColor = changJianColor;
        return this;
    }

    public CustomDialog setDeliveryColor(int deliveryColor) {
        DeliveryColor = deliveryColor;
        return this;
    }

    public CustomDialog setCardVieDgColor(int cardVieDgColor) {
        this.cardVieDgColor = cardVieDgColor;
        return this;
    }

    public CustomDialog setName(String name) {
        this.name = name;
        return this;
    }

    public CustomDialog setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public CustomDialog setSortname(String sortname) {
        this.sortname = sortname;
        return this;
    }

    public CustomDialog setDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomDialog setChangjian(String changjian) {
        this.changjian = changjian;
        return this;
    }

    public CustomDialog setChangjiancontent(String changjiancontent) {
        this.changjiancontent = changjiancontent;
        return this;
    }

    public CustomDialog setDelivery(String delivery) {
        this.delivery = delivery;
        return this;
    }

    public CustomDialog setDeliverycontent(String deliverycontent) {
        this.deliverycontent = deliverycontent;
        return this;
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        // 设置宽度
        WindowManager manager = getWindow().getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        Point size = new Point();
        display.getSize(size);
        // 宽度为当前屏幕的90%
        params.width = (int) (size.x * 0.9);
        getWindow().setAttributes(params);

        tv_name = findViewById(R.id.tv_name);
        tv_sort_name = findViewById(R.id.tv_sort_name);
        tv_description = findViewById(R.id.tv_description);
        tv_changjian = findViewById(R.id.tv_changjian);
        changjian_content = findViewById(R.id.changjian_content);
        tv_delivery = findViewById(R.id.tv_delivery);
        delivery_content = findViewById(R.id.delivery_content);
        tv_icon = findViewById(R.id.tv_icon);
        cardView = findViewById(R.id.dialog_layout);
        tv_icon.setText(icon);
        cardView.setCardBackgroundColor(cardVieDgColor);
//        tv_name.setTextColor(NameColor);
        tv_icon.setTextColor(iconColor);
        tv_sort_name.setTextColor(sortNameColor);
        tv_changjian.setTextColor(ChangJianColor);
        tv_delivery.setTextColor(DeliveryColor);

        if (!TextUtils.isEmpty(name)) {
            tv_name.setText(name);
        }
        if (!TextUtils.isEmpty(sortname)) {
            tv_sort_name.setText(sortname);
        }
        if (!TextUtils.isEmpty(description)) {
            tv_description.setText(description);
        }
        if (!TextUtils.isEmpty(changjian)) {
            tv_changjian.setText(changjian);
        }
        if (!TextUtils.isEmpty(changjiancontent)) {
            changjian_content.setText(changjiancontent);
        }
        if (!TextUtils.isEmpty(delivery)) {
            tv_delivery.setText(delivery);
        }
        if (!TextUtils.isEmpty(deliverycontent)) {
            delivery_content.setText(deliverycontent);
        }


    }


}