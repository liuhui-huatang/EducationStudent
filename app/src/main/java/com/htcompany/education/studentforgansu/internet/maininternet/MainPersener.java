package com.htcompany.education.studentforgansu.internet.maininternet;

import android.content.Context;
import android.os.Handler;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTableEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 主页解析类
 * Created by wrb on 2017/2/20.
 */
public class MainPersener {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    public MainPersener(Context context){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }
    /**
     * 解析课表数据
     * @param rebackString
     * @return
     */
    public List<ClassTableEntity> parseTodayClassTableData(String rebackString){
        List<ClassTableEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //今天
                datas = JsonUtils.getListObject(jsonObject2.getJSONArray("cources").toString(),ClassTableEntity.class);
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
     * 解析学期数据
     * @param rebackString
     * @return
     */
    public List<TermEntity> parseTermData(String rebackString){
        List<TermEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                //所有周次
                //本周所有课程
                datas =JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),TermEntity.class);
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
