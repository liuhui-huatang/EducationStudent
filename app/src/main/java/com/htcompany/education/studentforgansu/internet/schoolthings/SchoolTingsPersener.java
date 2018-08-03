package com.htcompany.education.studentforgansu.internet.schoolthings;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.AssetRepairEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.KeChengEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.SkillsGameDetailEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.SkillsGameEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TeachingInteractionEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
public class SchoolTingsPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public SchoolTingsPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析教学互动列表数据
     * @param rebackString
     * @return
     */
    public List<TeachingInteractionEntity> parseStudnetJXHDData(String rebackString){
        List<TeachingInteractionEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TeachingInteractionEntity.class);
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
     * 解析课程跟教师数据列表数据
     * @param rebackString
     * @return
     */
    public List<KeChengEntity> parseClassesData(String rebackString){
        List<KeChengEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),KeChengEntity.class);
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
     * 解析登录数据
     * @param rebacstr
     * @return
     */
    public boolean parserJXHDQuestion(String rebacstr){
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
     * 解析教学互动是否解决数据
     * @param rebacstr
     * @return
     */
    public boolean parserJXHDQuestionIFANSWER(String rebacstr){
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
     * 解析德育新闻列表数据
     * @param rebackString
     * @return
     */
    public List<DYNewsEntity> parseDYnewsData(String rebackString){
        List<DYNewsEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),DYNewsEntity.class);
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
     * 解析技能大赛数据列表数据
     * @param rebackString
     * @return
     */
    public List<SkillsGameEntity> parseShillGameListData(String rebackString){
        List<SkillsGameEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),SkillsGameEntity.class);
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
     * 解析技能大赛数据列表数据
     * @param rebackString
     * @return
     */
    public SkillsGameDetailEntity parseShillGameDetailData(String rebackString){
        SkillsGameDetailEntity datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
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
     * 解析资产报修数据列表数据
     * @param rebackString
     * @return
     */
    public List<AssetRepairEntity> parseAssetRepairListData(String rebackString){
        List<AssetRepairEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONObject("data").getJSONArray("data").toString(),AssetRepairEntity.class);
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
     * 解析添加资产报修
     * @param rebacstr
     * @return
     */
    public boolean parserAddAssetReapir(String rebacstr){
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
