package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.MainActivity;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.chatpart.DemoHelper;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.login.LoginInternet;
import com.htcompany.education.studentforgansu.internet.login.LoginPersoner;
import com.htcompany.education.studentforgansu.recruitstudent.RecruitStudentActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录界面
 * Created by wrb on 2016/12/30.
 * 1.登录服务器账号
 * 2.登录成功后根据返回环信账号登录环信
 * 3.根据返回jpuid注册激光别名
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private CardView login_card;
    private EditText login_uname_edt,login_pwd_edt;
    //网络请求类
    private LoginInternet loginInternet;
    private LoginPersoner loginPersoner;
    private WaitDialog waitDialog=null;
    private TextView login_yk_tv;
    private SharedPrefUtil sharedPrefUtil=null;
    private String login_flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initDatas();
        initViews();
        initOnclicEvents();
    }
    public void initDatas(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(tintManager!=null){
                tintManager.setTintDrawable(null);
                tintManager.setTintColor(getResources().getColor(R.color.login_tv));
            }
        }
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        //账号在其他设备登录时
        login_flag=getIntent().getStringExtra("login_flag");
        if("chat_flag".equals(login_flag)){
            ToastUtil.showToast("您的账号在其他设备登录!",this);
            setAlias("");
            //退出环信
//            exit();
        }
        loginInternet = new LoginInternet(this,myHandler);
        loginPersoner = new LoginPersoner(this);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        login_card = (CardView)this.findViewById(R.id.login_card);
        login_uname_edt=(EditText)this.findViewById(R.id.login_uname_edt);
        login_pwd_edt=(EditText)this.findViewById(R.id.login_pwd_edt);
        login_yk_tv= (TextView)this.findViewById(R.id.login_yk_tv);
    }
    public void initOnclicEvents(){
        login_card.setOnClickListener(this);
        login_yk_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_yk_tv:
                startActivity(new Intent(LoginActivity.this, RecruitStudentActivity.class));
                break;
            case R.id.login_card:
                if("".equals(login_uname_edt.getText().toString())){
                    ToastUtil.showToast("请输入账号",LoginActivity.this);
                }else if("".equals(login_pwd_edt.getText().toString())){
                    ToastUtil.showToast("请输入密码",LoginActivity.this);
                }else{
                    if(BaseUtils.isNetworkAvailable(LoginActivity.this)){
                        //登录
                        waitDialog.show();
                        loginInternet.login(login_uname_edt.getText().toString(),login_pwd_edt.getText().toString());
                    }else{
                        ToastUtil.showToast("请链接网络", LoginActivity.this);
                    }
                }
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接网络失败",LoginActivity.this);
                    break;
                case 200:
                    //登录返回数据
                    if(loginPersoner.parserLogin((String)msg.obj)){
                        //登录环信
                        System.out.print("登录成功 ");
                        if(waitDialog!=null){
                            waitDialog.dismiss();
                        }
                        sharedPrefUtil.putString("loginname",login_uname_edt.getText().toString().trim());
                        sharedPrefUtil.putString("loginpwd",login_pwd_edt.getText().toString().trim());
                        sharedPrefUtil.putString("islogin","1");

                        sharedPrefUtil.commit();
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                         setAlias(sharedPrefUtil.getString("jpuid",""));

                    }else{
                        ToastUtil.showToast("请输入正确账号密码",LoginActivity.this);
                        if(waitDialog!=null){
                            waitDialog.dismiss();
                        }
                    }
                    break;
            }
        }
    };
    public void setLogin(String username,String pwd){
        EMClient.getInstance().login(username,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {

                ToastUtil.showToast("登录成功",LoginActivity.this);
                //保存账号密码
                DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(sharedPrefUtil.getString("username",""));
                DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(InterfaceManager.siteURLIP+sharedPrefUtil.getString("photo",""));//设置换新头像
                finish();

            }
            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

                ToastUtil.showToast("登录失败"+code,LoginActivity.this);

                System.out.print("登录失败"+code);
            }
        });
    }

    //退出环信
    public void exit(){
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                sharedPrefUtil.putString("islogin","0");
                sharedPrefUtil.putString("token","");
                sharedPrefUtil.putString("sname","");
                sharedPrefUtil.putString("avatar","");
                sharedPrefUtil.putString("photo","");
                sharedPrefUtil.putString("number","");
                sharedPrefUtil.putString("loginpwd","");
                sharedPrefUtil.commit();
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
                    JPushInterface.setAliasAndTags(LoginActivity.this,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
}
