package com.htcompany.education.studentforgansu.internet.mysubsidize;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.FinancialTypeEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.HardshipGrantsEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.ScholarshipEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentFinancialEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentLoansEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TeacherEvaluationEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TuitionWaiverEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 我的资助数据解析类
 * Created by wrb on 2017/2/21.
 */
public class MySubsidizePersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MySubsidizePersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析奖学金列表数据
     * @param rebackString
     * @return
     */
    public List<ScholarshipEntity> parseJXJ_LISTData(String rebackString){
        List<ScholarshipEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ScholarshipEntity.class);
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
     * 解析助学金列表数据
     * @param rebackString
     * @return
     */
    public List<StudentFinancialEntity> parseZXJ_LISTData(String rebackString){
        List<StudentFinancialEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),StudentFinancialEntity.class);
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
     * 解析助学金类型列表数据
     * @param rebackString
     * @return
     */
    public List<FinancialTypeEntity> parseZXJ_TYPELISTData(String rebackString){
        List<FinancialTypeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),FinancialTypeEntity.class);
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
     * 解析助学金添加返回结果数据
     * @param rebacstr
     * @return
     */
    public boolean parseZXJ_AddData(String rebacstr){
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
    /**
     * 解析免学费列表数据
     * @param rebackString
     * @return
     */
    public List<TuitionWaiverEntity> parseMXF_LISTData(String rebackString){
        List<TuitionWaiverEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TuitionWaiverEntity.class);
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
     * 解析临时困难补助列表数据
     * @param rebackString
     * @return
     */
    public List<HardshipGrantsEntity> parseLSKNBZ_LISTData(String rebackString){
        List<HardshipGrantsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),HardshipGrantsEntity.class);
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
     * 解析助学贷款列表数据
     * @param rebackString
     * @return
     */
    public List<StudentLoansEntity> parseZXDK_LISTData(String rebackString){
        List<StudentLoansEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),StudentLoansEntity.class);
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
     * 解析助学金列表数据
     * @param rebackString
     * @return
     */
    public List<TeacherEvaluationEntity> parseTeacherPj_LISTData(String rebackString){
        List<TeacherEvaluationEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TeacherEvaluationEntity.class);
            }else{
                return datas;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return datas;
    }
}
