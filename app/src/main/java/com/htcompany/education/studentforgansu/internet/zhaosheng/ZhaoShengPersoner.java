package com.htcompany.education.studentforgansu.internet.zhaosheng;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.htcompany.education.studentforgansu.commonpart.tools.JsonUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.RecruitStudentZYEntity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */
public class ZhaoShengPersoner {
    private Context context;
    private JSONObject jsonObject;
    private JSONArray firstJsonarray;
    private JSONArray twoJsonarray;
    private String status = "";
    private SharedPrefUtil sharedPrefUtil;
    private Handler myHandler;
    private Message mesg=null;
    public ZhaoShengPersoner(Context context,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
    }

    /**
     * 解析课表数据
     * @param rebackString
     * @return
     */
    public void parseClassTableData(String rebackString){
        List<ZSSHEntity> mzdatas=null;
        List<ZSSHEntity> xzdatas=null;
        List<ZSSHEntity> xslbdatas=null;
        List<ZSSHEntity> jdfsbdatas=null;
        List<ZSSHEntity> zydatas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                JSONObject jsonObject2 = jsonObject.getJSONObject("data");
                //所有周次
                //本周所有课程
                mzdatas = JsonUtils.getListObject(jsonObject2.getJSONArray("nation").toString(),ZSSHEntity.class);
                xzdatas = JsonUtils.getListObject(jsonObject2.getJSONArray("educational").toString(),ZSSHEntity.class);
                jdfsbdatas = JsonUtils.getListObject(jsonObject2.getJSONArray("jiudu_method").toString(),ZSSHEntity.class);
                xslbdatas = JsonUtils.getListObject(jsonObject2.getJSONArray("stu_type").toString(),ZSSHEntity.class);
                zydatas = JsonUtils.getListObject(jsonObject2.getJSONArray("major").toString(),ZSSHEntity.class);
                Message message = new Message();
                message.what=201;
                Bundle bundle = new Bundle();
                bundle.putSerializable("mzdatas",(Serializable) mzdatas);
                bundle.putSerializable("xzdatas",(Serializable) xzdatas);
                bundle.putSerializable("jdfsbdatas",(Serializable) jdfsbdatas);
                bundle.putSerializable("xslbdatas",(Serializable) xslbdatas);
                bundle.putSerializable("zydatas",(Serializable) zydatas);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }else{
            }
        } catch (JSONException e) {
            e.printStackTrace();
            myHandler.sendEmptyMessage(300);
        }
    }
    /**
     * 解析登录数据
     * @param rebacstr
     * @return
     */
    public boolean parserADDzs(String rebacstr){
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
     * 解析登录数据
     * @param rebacstr
     * @return
     */
    public void parserPDYQM(String rebacstr){
        try {
            jsonObject = new JSONObject(rebacstr);
            status  =jsonObject.getString("code");
            if("0".equals(status)){
                mesg = new Message();
                mesg.what=201;
                mesg.obj = jsonObject.getString("msg");
            }else{
                mesg = new Message();
                mesg.what=202;
                mesg.obj = jsonObject.get("msg");
            }
            myHandler.sendMessage(mesg);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 解析专业列表数据
     * @param rebackString
     * @return
     */
    public List<RecruitStudentZYEntity> parseZYLISTData(String rebackString){
        List<RecruitStudentZYEntity> datas=null;
        try {
            jsonObject = new JSONObject(rebackString);
            status = jsonObject.getString("code");
            if("0".equals(status)){
                datas = JsonUtils.getListObject(jsonObject.getJSONArray("data").toString(),RecruitStudentZYEntity.class);
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
