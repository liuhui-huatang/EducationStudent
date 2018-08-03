package com.htcompany.education.studentforgansu.internet.login;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录解析类
 * Created by wrb on 2017/1/20.
 */
public class LoginPersoner {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    public LoginPersoner(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析登录数据
     * @param rebacstr
     * @return
     */
    public boolean parserLogin(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                String token = jsonObject.getJSONObject("data").getString("token");
                sharedPrefUtil.putString("token",token);
                sharedPrefUtil.putString("hxuser",jsonObject.getJSONObject("data").getString("hxuser"));
                sharedPrefUtil.putString("hxpassword",jsonObject.getJSONObject("data").getString("hxpassword"));
                sharedPrefUtil.putString("sname",jsonObject.getJSONObject("data").getString("true_name"));
                sharedPrefUtil.putString("avatar",jsonObject.getJSONObject("data").getString("avatar"));
                sharedPrefUtil.putString("photo",jsonObject.getJSONObject("data").getString("photo"));
                sharedPrefUtil.putString("number",jsonObject.getJSONObject("data").getString("number"));
                sharedPrefUtil.putString("jxb",jsonObject.getJSONObject("data").getString("jxb"));
                sharedPrefUtil.putString("xzb",jsonObject.getJSONObject("data").getString("xzb"));
                sharedPrefUtil.putString("jpuid",jsonObject.getJSONObject("data").getString("jpuid"));
                sharedPrefUtil.commit();
                //注册激光别名
//                setAlias(jsonObject.getJSONObject("data").getString("jpuid"));
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
                    JPushInterface.setAliasAndTags(context,
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
            }
        }
    };
}
