package com.htcompany.education.studentforgansu.internet.myclass;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassAnnouncementEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassDetailEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTableEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTxlEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 我的班级
 * Created by wrb on 2017/1/20.
 */
public class MyClassPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MyClassPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析课表数据
     * @param rebackString
     * @return
     */
    public  List<ClassTableEntity> parseClassTableData(String rebackString){
        List<ClassTableEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                //本周所有课程
                datas =JsonUtils.getListObject(jsonObject2.getJSONArray("cources").toString(),ClassTableEntity.class);
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
     * 解析通讯录数据
     * @param rebackString
     * @return
     */
    public  List<ClassTxlEntity> parseClassTxlData(String rebackString){
        List<ClassTxlEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                //本周所有课程
                datas =JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassTxlEntity.class);
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
     * 解析班级信息数据
     * @param rebackString
     * @return
     */
    public ClassDetailEntity parseMyClassMsgData(String rebackString){
        ClassDetailEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                firstJsonarray = jsonObject.getJSONArray("data");
                //所有周次
                //本周所有课程
                if(firstJsonarray!=null&&firstJsonarray.length()>0) {
                    msgEntity = JsonUtils.getObject(firstJsonarray.get(0).toString(), ClassDetailEntity.class);
                }
            }else{
                return msgEntity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return msgEntity;
    }
    /**
     * 解析班级公告数据
     * @param rebackString
     * @return
     */
    public  List<ClassAnnouncementEntity> parseClassGGData(String rebackString){
        List<ClassAnnouncementEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                //本周所有课程
                datas =JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),ClassAnnouncementEntity.class);
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
