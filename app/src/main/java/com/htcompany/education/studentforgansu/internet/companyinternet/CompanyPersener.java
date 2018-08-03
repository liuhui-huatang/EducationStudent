package com.htcompany.education.studentforgansu.internet.companyinternet;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.CompanyJYJLEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.SXRecoderEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.YBMJobEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 企业信息解析类
 * Created by wrb on 2017/3/23.
 */
public class CompanyPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public CompanyPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析实习记录列表数据
     * @param rebackString
     * @return
     */
    public List<SXRecoderEntity> parseCompany_SXJLData(String rebackString){
        List<SXRecoderEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                if(!"".equals(jsonObject.getJSONArray("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(), SXRecoderEntity.class);
                }
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析就业记录列表数据
     * @param rebackString
     * @return
     */
    public List<CompanyJYJLEntity> parseCompany_JYJLData(String rebackString){
        List<CompanyJYJLEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                if(!"".equals(jsonObject.getJSONArray("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(), CompanyJYJLEntity.class);
                }
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
    /**
     * 解析已报名岗位列表数据
     * @param rebackString
     * @return
     */
    public List<YBMJobEntity> parseCompany_YBMJobData(String rebackString){
        List<YBMJobEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                if(!"".equals(jsonObject.getJSONArray("data").toString())) {
                    datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(), YBMJobEntity.class);
                }
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }

    /**
     * 解析岗位报名数据
     * @param rebacstr
     * @return
     */
    public boolean parserGWBM(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
