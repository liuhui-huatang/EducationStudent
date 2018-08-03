package com.htcompany.education.studentforgansu.internet.grzy;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.BodyCheckEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.BodyPhysiqueEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MyElectiveEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MyExamEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MySelfMsgEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 个人主页
 * Created by wrb on 2017/2/7.
 */
public class MySelfPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MySelfPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析个人信息数据
     * @param rebackString
     * @return
     */
    public MySelfMsgEntity parseMyselfMsgData(String rebackString){
        MySelfMsgEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //个人信息
                msgEntity = JsonUtils.getObject(jsonObject.getJSONObject("data").toString(),MySelfMsgEntity.class);
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
     * 解析体检信息数据
     * @param rebackString
     * @return
     */
    public BodyCheckEntity parseMyselfBodyCheckData(String rebackString){
        BodyCheckEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                if(!"null".equals(jsonObject.getJSONObject("data").toString())&&jsonObject.getJSONObject("data").toString()!=null){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                //本周所有课程
                    msgEntity = JsonUtils.getObject(jsonObject2.toString(),BodyCheckEntity.class);
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
     * 解析体质信息数据
     * @param rebackString
     * @return
     */
    public BodyPhysiqueEntity parseMyselfBodyTZData(String rebackString){
        BodyPhysiqueEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                //本周所有课程
                if(jsonObject2!=null) {
                    msgEntity = JsonUtils.getObject(jsonObject2.toString(), BodyPhysiqueEntity.class);
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
     * 解析成绩信息数据
     * @param rebackString
     * @return
     */
    public MyExamEntity parseMyExamMsgData(String rebackString){
        MyExamEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //个人信息
                msgEntity = JsonUtils.getObject(jsonObject.getJSONObject("data").toString(),MyExamEntity.class);
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
     * 解析我的选修课数据
     * @param rebackString
     * @return
     */
    public MyElectiveEntity parseMyXXKData(String rebackString){
        MyElectiveEntity msgEntity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                //本周所有课程
                if(jsonObject2!=null) {
                    msgEntity = JsonUtils.getObject(jsonObject2.toString(), MyElectiveEntity.class);
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
}
