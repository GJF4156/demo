package com.example.demo.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.demo.R;
import com.example.demo.base.BaseFragment;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * 显示web网页的fragment
 */
public class WebFragment extends BaseFragment {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        initView(view);
        Bundle bundle = this.getArguments();//得到从Activity传来的数据
        String url = bundle.getString("url");
        showWebView(url);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showWebView(String url) {
        //设置正常加载css js等样式文件
        webView.getSettings().setJavaScriptEnabled(true);
        //设置要加载的网页地址
        webView.loadUrl(url);
        //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
    }

    private void initView(View view) {
        webView=view.findViewById(R.id.webView);
    }

}
