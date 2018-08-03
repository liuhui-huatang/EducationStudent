package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 设置界面
 * Created by wrb on 2017/3/11.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView setting_exit_tv;
    private SharedPrefUtil sharedPrefUtil=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.setting_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(SettingActivity.this, "login");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        setting_exit_tv= (TextView)this.findViewById(R.id.setting_exit_tv);
    }
    public void initViewValues(){
        title.setText("设置");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        setting_exit_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
               this.finish();
                break;
            case R.id.setting_exit_tv:
                //退出程序，退出换新，并注册激光别名为空
                setAlias("");
                exit();
                break;
        }
    }
    //退出环信
    public void exit(){
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                sharedPrefUtil.putString("islogin","0");
                sharedPrefUtil.putString("token","");
                sharedPrefUtil.putString("hxuser","");
                sharedPrefUtil.putString("hxpassword","");
                sharedPrefUtil.putString("sname","");
                sharedPrefUtil.putString("avatar","");
                sharedPrefUtil.putString("photo","");
                sharedPrefUtil.putString("number","");
                sharedPrefUtil.putString("loginpwd","");
                sharedPrefUtil.commit();
                MyApp.getInstance().exit();
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub

            }
        });
    }
    //=========================================注册激光推送别名=================================================
    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias(String alias) {
        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    sharedPrefUtil.putString("jgbgsucess","1");
                    sharedPrefUtil.commit();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
            }
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(SettingActivity.this,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
}
