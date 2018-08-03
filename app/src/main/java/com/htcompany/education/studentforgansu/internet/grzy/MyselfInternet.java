package com.htcompany.education.studentforgansu.internet.grzy;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 个人主页
 * Created by wrb on 2017/2/7.
 */
public class MyselfInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MyselfInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取个人信息数据
     */
    public void getMySelfMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.GRZY_JBXX),200,params,myHandler);
    }
    /**
     * 获取成绩信息数据
     */
    public void getMySelfExamMsg(String term){
        params = new RequestParams();
        params.addBodyParameter("term",term);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.GRZY_WDCJ),200,params,myHandler);
    }
    /**
     * 获取我的选修课数据
     */
    public void getMySelectClassMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.GRZY_WDXXK),200,params,myHandler);
    }
    /**
     * 获取体检信息数据
     */
    public void getMySelfBodyCheckMsg(String term){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("term", term);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.GRZY_TJXX),200,params,myHandler);
    }
    /**
     * 获取体检信息数据
     */
    public void getMySelfBodyTZMsg(String term){
        params = new RequestParams();
        params.addBodyParameter("term", term);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.GRZY_TZXX),200,params,myHandler);
    }
}
