package com.htcompany.education.studentforgansu.internet.maininternet;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 主页请求类
 * Created by wrb on 2017/2/20.
 */
public class MainInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MainInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取学期信息数据
     */
    public void getTermMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.ALL_TERME),200,params,myHandler);
    }
    /**
     * 获取课表数据
     */
    public void getToadayClassTableDatas(){
        params = new RequestParams();
        params.addBodyParameter("weekt","1");
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_CLASSTABLE),201,params,myHandler);
    }
}
