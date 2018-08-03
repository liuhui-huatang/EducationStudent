package com.htcompany.education.studentforgansu.internet.myevaluate;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 我的评价网络请求
 * Created by wrb on 2017/2/8.
 */
public class MyEvaluateInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MyEvaluateInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取学生奖励数据
     */
    public void getStudentJLDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_STUDENTJL),200,params,myHandler);
    }
    /**
     * 获取学生惩罚数据
     */
    public void getStudentCFDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_STUDENTCF),200,params,myHandler);
    }
    /**
     * 获取学生请假类型列表数据数据
     */
    public void getStudentQJTYPES_LISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYQJ_WANT_TYPES),201,params,myHandler);
    }
    /**
     * 获取学生请假列表数据数据
     */
    public void getStudentQJ_LISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYQJ_LIST),200,params,myHandler);
    }
    /**
     * 提交学生请假申请数据
     */
    public void uploadStudentQJSQDatas(String type,String start_time,String end_time,String reason){
        params = new RequestParams();
        params.addBodyParameter("type", type);
        params.addBodyParameter("start_time", start_time);
        params.addBodyParameter("end_time", end_time);
        params.addBodyParameter("reason", reason);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYQJ_WANT),200,params,myHandler);
    }
    /**
     * 获取学生毕业评语数据
     */
    public void getStudentBYPYDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_BYPY),200,params,myHandler);
    }
    /**
     * 获取学生学期评语数据
     */
    public void getStudentTermPYDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_TERMPY),200,params,myHandler);
    }
}
