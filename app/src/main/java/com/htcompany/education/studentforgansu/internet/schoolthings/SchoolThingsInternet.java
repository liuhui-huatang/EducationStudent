package com.htcompany.education.studentforgansu.internet.schoolthings;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.Bimp;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.MyXutilsRequest;
import com.lidroid.xutils.http.RequestParams;

import java.io.File;

/**
 * 校内事宜网络请求类
 * Created by wrb on 2017/2/13.
 */
public class SchoolThingsInternet {
    private Context context;
    private Handler myHandler;
    private RequestParams params=null;
    private SharedPrefUtil sharedPrefUtil=null;
    public SchoolThingsInternet(Context context, Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 获取教学互动列表数据
     */
    public void getJSHD_LISTDatas(String page){
        params = new RequestParams();
        params.addBodyParameter("page", page);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_STUDENT_JXHDLIST),200,params,myHandler);
    }
    /**
     * 获取课程列表数据
     */
    public void getJSHD_CLASSDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_STUDENT_KECHENG),200,params,myHandler);
    }
    /**
     * 获取授课教师列表数据
     */
    public void getJSHD_ClassTeacherDatas(String unkey){
        params = new RequestParams();
        params.addBodyParameter("unkey",unkey);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_CLASS_TEACJERS),201,params,myHandler);
    }
    /**
     * 提交教学互动问题数据
     */
    public void uplodeJSHD_questionDatas(String kecheng_id,String teacher_id,String question){
        params = new RequestParams();
        params.addBodyParameter("kecheng_id",kecheng_id);
        params.addBodyParameter("teacher_id",teacher_id);
        params.addBodyParameter("question",question);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_JXHD_UPLODE),202,params,myHandler);
    }
    /**
     * 提交教学互动问题S是否解决数据
     */
    public void uplodeJSHD_questionISANSWERDatas(String id,String id_yes){
        params = new RequestParams();
        params.addBodyParameter("id",id);
        params.addBodyParameter("is_yes",id_yes);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.MYEVALUATE_JXHD_UPLODE_SFJJ),200,params,myHandler);
    }
    /**
     * 获取德育新闻列表数据
     */
    public void get_DYXWDatas(String issy,String pagenum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("issy", issy);
        params.addBodyParameter("page", pagenum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_DYNEWS),202,params,myHandler);
    }
    /**
     * 获取德育新闻列表数据
     */
    public void get_ALLDYXWDatas(String issy,String pagenum){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("issy", issy);
        params.addBodyParameter("page", pagenum);
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_DYNEWS),203,params,myHandler);
    }
    /**
     * 获取技能大赛列表数据
     */
    public void get_SKILLS_LISTDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_SHILL_LIST),200,params,myHandler);
    }
    /**
     * 获取技能大赛详情数据
     */
    public void get_SKILLS_DETAILSDatas(String m_id){
        params = new RequestParams();
        params.addBodyParameter("m_id",m_id);
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_SHILL_DETAILS),200,params,myHandler);
    }
    /**
     * 获取资产包修列表数据
     */
    public void getAsset_RepairListDatas(){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_ZCWH_LIST),200,params,myHandler);
    }
    /**
     * 添加资产包修列表数据
     */
    public void addAsset_RepairListDatas(String title,String place,String description){
        params = new RequestParams();
        params.addBodyParameter("token", sharedPrefUtil.getString("token",""));
        params.addBodyParameter("title",title);
        params.addBodyParameter("place",place);
        params.addBodyParameter("description",description);
        if(Bimp.tempSelectBitmap.size()>0){
            File file =new File(Bimp.tempSelectBitmap.get(0).imagePath);
            if(file.exists()){
                params.addBodyParameter("imgurl",new File(Bimp.tempSelectBitmap.get(0).imagePath));
            }
        }
        MyXutilsRequest.getMyXutlsRequest().sendRequestMethod(InterfaceManager.getInstance().getURL(InterfaceManager.SCHOOLTHINGS_ADDZCWH),200,params,myHandler);
    }
}
