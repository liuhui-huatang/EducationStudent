package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 学期评语实体类
 * Created by wrb on 2016/12/6.
 */
public class TermsRemarkEntity implements Serializable{
    private String term;
    private String content;
    private String time;
    private String laoshi;
    private String year;
    private String month;
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLaoshi() {
        return laoshi;
    }

    public void setLaoshi(String laoshi) {
        this.laoshi = laoshi;
    }
}
