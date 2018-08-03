package com.htcompany.education.studentforgansu.internet.myclass;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 教师请求类
 * Created by wrb on 2017/1/20.
 */
public class MyClassInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MyClassInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 获取课表数据
     */
    public void getClassTableDatas(String week){
        params = new RequestParams();
        params.addBodyParameter("week",week);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_CLASSTABLE),200,params,myHandler);
    }
    /**
     * 获取通讯录数据
     */
    public void getClassTXLDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_STUDENT_TXL),200,params,myHandler);
    }
    /**
     * 获取班级信息数据
     */
    public void getClassMsgDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_CLASS_MSG),200,params,myHandler);
    }
    /**
     * 获取班级公告数据
     */
    public void getClassGGDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page", page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_CLASS_GG),200,params,myHandler);
    }
    /**
     * 获取学生公告数据
     */
    public void getStudentGGDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page", page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYCLASS_STUDENT_GG),200,params,myHandler);
    }
}
