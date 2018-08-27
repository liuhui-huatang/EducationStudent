package com.htcompany.education.studentforgansu.mainpart.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;

import java.util.HashMap;
import java.util.Map;


/**
 * 登录界面
 * Created by wrb on 2016/12/30.
 */
public class MyWebActivity extends BaseActivity implements View.OnClickListener {
    WebView myweb;
    private TextView title_tv;
    private String url;
    private SharedPrefUtil sharedPrefUtil = null;

    public static void  startIntent(Activity activity,String url,String title){
        Intent intent=new Intent( activity,MyWebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initViews();
        initDatas();
    }


    public void initViews() {
        title_tv = (TextView) this.findViewById(R.id.title);
        title_tv.setText("详情页面");
        this.findViewById(R.id.reback_btn).setOnClickListener(this);
        myweb= (WebView) findViewById(R.id.webview);
    }

    public void initDatas() {
        sharedPrefUtil = new SharedPrefUtil(this,"login") ;
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        title_tv.setText(title);
        url = intent.getStringExtra("url");
        Map<String, String > map = new HashMap<String, String>() ;
        map.put( "token" , sharedPrefUtil.getString("token","") ) ;
        url +="?unkey="+sharedPrefUtil.getString("unkey","")+"&token="+sharedPrefUtil.getString("token","") ;
        //map.put("unkey",sharedPrefUtil.getString("unkey",""));
        WebSettings webSettings = myweb.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //设置自适应屏幕，两者合用

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片


        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        myweb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        myweb.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }
        });
        myweb.loadUrl(url,map);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }


}
