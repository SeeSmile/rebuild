package com.a360ads.eshare.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.utils.Elog;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * 说明：基础网页显示
 * Created by FuPei
 * on 2016/1/20 at 15:00
 */
public class EwebActivity extends BaseActivity {

    public static String EXTRA_TITLE = "title";
    public static String EXTRA_URL = "url";
    private String url;
    private String title;
    private WebView wv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Elog.i("进入WebActivity");
        initView();
        initData();
        initWebView();
        loadUrl();
    }

    private void initView() {
        setContentView(R.layout.activity_baseweb);
        setTitleStyle(STYLE_BOTH_TB);
        setOnbackclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wv_main = (WebView) findViewById(R.id.wbv_main);
    }

    private void initData() {
        title = getIntent().getStringExtra(EXTRA_TITLE);
        url = getIntent().getStringExtra(EXTRA_URL);
        if(TextUtils.isEmpty(url)) {
            toast("加载的网页无效");
            finish();
        }
        setTitle(title);
        Elog.i("url = " + url + ", title = " + title);
    }

    private void initWebView() {
        WebSettings webSetting=wv_main.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    private void loadUrl() {
        wv_main.loadUrl(url);
    }
}
