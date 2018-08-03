package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 班级公告实体类
 * Created by wrb on 2016/12/6.
 */
public class ClassAnnouncementEntity implements Serializable{
            private String id;//": 19,
            private String title;//": "班级公告2",
            private String teacher_id;//": "attgq7ee-583b9a599e11c",
            private String type;//": 2,
            private String zuzhi;//": "lkmsvovc-583bd126851be",
            private String update_time;//": 1484755200,
            private String content;//": "119",
            private String status;//": 1
            private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZuzhi() {
        return zuzhi;
    }

    public void setZuzhi(String zuzhi) {
        this.zuzhi = zuzhi;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
