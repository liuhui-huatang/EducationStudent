package com.htcompany.education.studentforgansu.internet.mysubsidize;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

/**
 * 我的资助网络请求类
 * Created by wrb on 2017/2/21.
 */
public class MySubsidizeInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public MySubsidizeInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取奖学金列表数据
     */
    public void getSubsidize_JXJLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_JXJ_LIST),200,params,myHandler);
    }
    /**
     * 获取助学金列表数据
     */
    public void getSubsidize_ZXJLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_ZXJ_LIST),200,params,myHandler);
    }
    /**
     * 获取助学金类型列表数据
     */
    public void getSubsidize_ZXJTypeLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("unkey","ml35fkfp-582bb3ddca065");
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_ZXJTYPE_LIST),201,params,myHandler);
    }
    /**
     * 提交助学金申请数据
     */
    public void uploadSubsidize_ZXJDetialDatas(String reason,String total,
                                               String nian,String yue,
                                               String miaoshu){
        params = new RequestParams();
        params.addBodyParameter("reason", reason);
        params.addBodyParameter("total", total);
        params.addBodyParameter("nian", nian);
        params.addBodyParameter("yue", yue);
        params.addBodyParameter("miaoshu", miaoshu);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_ZXJ_ADD),200,params,myHandler);
    }
    /**
     * 获取临时困难补助列表数据
     */
    public void getSubsidize_LSKNBZLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_LSKNBZ_LIST),200,params,myHandler);
    }

    /**
     * 提交临时困难补助申请数据
     * @param school_date 学期
     * @param subsidy_date 时间
     * @param amount 金额
     * @param reason 原因
     * @param remark 备注
     */
    public void uploadSubsidize_LSKNBZDetialDatas(String school_date,String subsidy_date,String amount,String reason,String remark){
        params = new RequestParams();
        params.addBodyParameter("school_date", school_date);
        params.addBodyParameter("subsidy_date", subsidy_date);
        params.addBodyParameter("amount", amount);
        params.addBodyParameter("reason", reason);
        params.addBodyParameter("remark", remark);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_LSKNBZ_ADD),200,params,myHandler);
    }
    /**
     * 获取免学费列表数据
     */
    public void getSubsidize_MXFLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_MXF_LIST),200,params,myHandler);
    }
    /**
     * 获取助学贷款列表数据
     */
    public void getSubsidize_ZXDKLISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_ZXDK_LIST),200,params,myHandler);
    }
    /**
     * 提交助学贷款给申请数据
     */
    public void uploadSubsidize_ZXDKDetialDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYSUBSIDIZE_ZXDK_ADD),200,params,myHandler);
    }


    /**
     * 获取奖学金列表数据
     */
    public void getSubsidize_Teacherpj_Datas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.Teacher_evaluation_list),200,params,myHandler);
    }
}
