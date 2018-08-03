package com.htcompany.education.studentforgansu.internet.myevaluate;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.GraduatedCommentEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.LeaveApplyEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MyLeaveTypeEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.PunishmentEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.RewardsEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermsRemarkEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 我的评价数据解析
 * Created by wrb on 2017/2/8.
 */
public class MyEvaluatePersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MyEvaluatePersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析学生奖励数据
     * @param rebackString
     * @return
     */
    public List<RewardsEntity> parseStudnetJLData(String rebackString){
        List<RewardsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),RewardsEntity.class);
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
     * 解析学生奖励数据
     * @param rebackString
     * @return
     */
    public List<PunishmentEntity> parseStudnetCFData(String rebackString){
        List<PunishmentEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),PunishmentEntity.class);
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
     * 解析学生请假列表数据
     * @param rebackString
     * @return
     */
    public List<LeaveApplyEntity> parseStudnetQJData(String rebackString){
        List<LeaveApplyEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),LeaveApplyEntity.class);
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
     * 解析请假申请提交结果数据
     * @param rebacstr
     * @return
     */
    public boolean parseStudnet_QJResultData(String rebacstr){
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
     * 解析我请假类型列表数据
     * @param rebackString
     * @return
     */
    public List<MyLeaveTypeEntity> parseMyLeaveTypes_ListData(String rebackString){
        List<MyLeaveTypeEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),MyLeaveTypeEntity.class);
                return datas;
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datas;
    }
    /**
     * 解析学生毕业评语数据
     * @param rebackString
     * @return
     */
    public GraduatedCommentEntity parseStudnetBYPYData(String rebackString){
        GraduatedCommentEntity entity=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                firstJsonarray = jsonObject.getJSONArray("data");
                if(firstJsonarray!=null&&firstJsonarray.length()>0) {
                    entity = JsonUtils.getObject(firstJsonarray.get(0).toString(), GraduatedCommentEntity.class);
                }
            }else{
                return entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
        return entity;
    }
    /**
     * 解析学期评语列表数据
     * @param rebackString
     * @return
     */
    public List<TermsRemarkEntity> parseStudnetTermPYData(String rebackString){
        List<TermsRemarkEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TermsRemarkEntity.class);
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
