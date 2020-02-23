package com.example.demo.myView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自定义image,用于在4.x上实现仿5.0上分享元素的动画
 * Created by lh on 2016/11/4.
 */
@SuppressLint("AppCompatCustomView")
public class CustomImage extends ImageView {
    private int mResId;

    public CustomImage(Context context) {
        this(context, null, 0);
    }

    public CustomImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            String namespace = "http://schemas.android.com/apk/res/android";
            String attribute = "src";
            mResId = attrs.getAttributeResourceValue(namespace, attribute, 0);
        }
    }

    public int getImageId() {
        return mResId;
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mResId = resId;
    }
}