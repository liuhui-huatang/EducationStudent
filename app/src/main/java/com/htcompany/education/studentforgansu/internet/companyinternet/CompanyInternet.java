package com.htcompany.education.studentforgansu.internet.companyinternet;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 企业信息网络模块
 * Created by wrb on 2017/3/23.
 */
public class CompanyInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public CompanyInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取企业招聘信息数据
     */
    public void getCompany_ZPGWMsg(String name,String page){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("ser_ep_name",name);
        params.addBodyParameter("page",page);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMPANY_QYZP_LIST),200,params,myHandler);
    }
    /**
     * 获取实习记录信息数据
     */
    public void getCompany_SXJLMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMPANY_SXJL_LIST),200,params,myHandler);
    }
    /**
     * 获取就业记录信息数据
     */
    public void getCompany_JYXXMsg(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMPANY_JYXX_LIST),200,params,myHandler);
    }
    /**
     * 获取已报名岗位信息数据
     */
    public void getCompany_YBMGWMsg(String name,String page){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("ser_ep_name",name);
        params.addBodyParameter("page",page);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMPANY_YBMGW_LIST),200,params,myHandler);
    }
    /**
     * 获取报名岗位信息数据
     */
    public void uploadCompany_BMGWMsg(String gid){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("gwid",gid);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.COMPANY_GWBM_ADD),200,params,myHandler);
    }
}
