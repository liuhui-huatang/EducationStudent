package com.htcompany.education.studentforgansu.internet.zhaosheng;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 招生网络请求类
 * Created by wrb on 2017/1/22.
 */
public class ZhaoShengInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public ZhaoShengInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取下拉数据
     */
    public void getZSxlDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.ZS_STUDENT_SJY),200,params,myHandler);
    }
    /**
     * 提交报名数据数据
     */
    public void addZSxlDatas(String name,String sex,String sfcard,String mz,String phone,String zy,String xz,
                             String xslb,String jdfs,String byyx,String time,String code){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("name", name);
        params.addBodyParameter("sex", sex);
        params.addBodyParameter("shengfen", sfcard);
        params.addBodyParameter("mingzu", mz);
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("zhuanye", zy);
        params.addBodyParameter("xuezhi", xz);
        params.addBodyParameter("leibie", xslb);
        params.addBodyParameter("jiudu", jdfs);
        params.addBodyParameter("biye_school", byyx);
        params.addBodyParameter("biye_time", time);
        params.addBodyParameter("code", code);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.ZS_STUDENT_ADDSJY),202,params,myHandler);
    }
    /**
     * 判断邀请码
     */
    public void getPanDuanYQMDatas(String code){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("code", code);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.ZS_STUDENT_PDYQM),200,params,myHandler);
    }
    /**
     * 获取德育新闻列表数据
     */
    public void get_DYXWDatas(String issy){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("issy", "1");
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.ZS_STUDENT_DYXW),208,params,myHandler);
    }
    /**
     * 获取专业介绍列表数据
     */
    public void get_zyDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_ZHUANYES),222,params,myHandler);
    }
}
