package com.htcompany.education.studentforgansu.recruitstudent.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.RecruitStudentZYEntity;

/**
 * 专业介绍详情
 * Created by weiruibin on 2017/6/29.
 */

public class ZyJsDetailsActivity extends BaseActivity{
    private TextView title;
    private RelativeLayout reback_btn;
    private WebView zyjsdetial_wb;
    RecruitStudentZYEntity entity;
    private TextView zydetails_name_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyjsdetails_activity);
        initDatas();
        initViews();
    }
    public void initDatas(){
        entity=(RecruitStudentZYEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        zydetails_name_tv=(TextView)this.findViewById(R.id.zydetails_name_tv);
        zyjsdetial_wb=(WebView)this.findViewById(R.id.zyjsdetial_wb);
        title.setText("专业详情");
        reback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(entity!=null){
            zydetails_name_tv.setText(entity.getName());
            init();
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = zyjsdetial_wb.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(false);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);//
        webSettings.setLoadWithOverviewMode(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
//        webSettings.setDefaultFontSize(60);
        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
        zyjsdetial_wb.setWebViewClient(mWebViewClientBase);
        zyjsdetial_wb.setWebChromeClient(mWebChromeClientBase);
        zyjsdetial_wb.loadDataWithBaseURL("", entity.getContent(), "text/html", "utf-8", null);
        this.onResume();
    }
    private ZyJsDetailsActivity.WebViewClientBase mWebViewClientBase = new ZyJsDetailsActivity.WebViewClientBase();
    private class WebViewClientBase extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url,
                                           boolean isReload) {
            // TODO Auto-generated method stub
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }

    private ZyJsDetailsActivity.WebChromeClientBase mWebChromeClientBase = new ZyJsDetailsActivity.WebChromeClientBase();

    private class WebChromeClientBase extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

            Log.e("eee",consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url,
                                           boolean precomposed) {
            // TODO Auto-generated method stub
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog,
                                      boolean isUserGesture, Message resultMsg) {
            // TODO Auto-generated method stub
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
    }
}
