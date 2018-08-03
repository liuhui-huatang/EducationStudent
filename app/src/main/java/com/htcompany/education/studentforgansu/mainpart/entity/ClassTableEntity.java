package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 课表实体类
 * Created by wrb on 2017/1/20.
 */
public class ClassTableEntity implements Serializable {
    private String name;//": "高等数学<br>(教学11班)",
            private String id;//": "2ki1tk9v-5881c7e31998f",
            private String time;//": " 11:00-11:40",
            private String iscource;//": "iscource",
            private String classname;//": "教学11班",
            private String roomname;//": "sdf ",
            private String courcename;//": "高等数学",
            private String teachername;//": "张三",
            private int week;//": 1,
            private int secid;//": 4
    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourcename() {
        return courcename;
    }

    public void setCourcename(String courcename) {
        this.courcename = courcename;
    }

    public String getIscource() {
        return iscource;
    }

    public void setIscource(String iscource) {
        this.iscource = iscource;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSecid() {
        return secid;
    }

    public void setSecid(int secid) {
        this.secid = secid;
    }
}
