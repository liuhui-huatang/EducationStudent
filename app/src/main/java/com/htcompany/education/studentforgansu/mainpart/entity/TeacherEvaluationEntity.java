package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 教师评教实体类
 * Created by wrb on 2017/2/22.
 */
public class TeacherEvaluationEntity implements Serializable{
           private String id;//": 4,
            private String title;//": "123123",
            private String ban;//": "m8vq7hgm-58293b0d2ff76,3lieu92o-5816af5ce4037,lkmsvovc-583bd126851be,8vq7hgm-58293b0d2ff76",
            private String xueqi;//": "pu8crm8m-5812c34aa2a2a",
            private String atime;//": "2016-12-05",
            private String btime;//": "2017-01-28",
            private String status;//": 2,
            private String shuoming;//": "11121212",
            private String xueqi_name;//": "2015~2016学年第二学期",
            private String status_t;//": "已发布",
            private String is_ping;//": 3,
            private String caozuo;//": "详情"
            private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getXueqi() {
        return xueqi;
    }

    public void setXueqi(String xueqi) {
        this.xueqi = xueqi;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShuoming() {
        return shuoming;
    }

    public void setShuoming(String shuoming) {
        this.shuoming = shuoming;
    }

    public String getXueqi_name() {
        return xueqi_name;
    }

    public void setXueqi_name(String xueqi_name) {
        this.xueqi_name = xueqi_name;
    }

    public String getStatus_t() {
        return status_t;
    }

    public void setStatus_t(String status_t) {
        this.status_t = status_t;
    }

    public String getIs_ping() {
        return is_ping;
    }

    public void setIs_ping(String is_ping) {
        this.is_ping = is_ping;
    }

    public String getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(String caozuo) {
        this.caozuo = caozuo;
    }
}
