package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 已报名岗位实体
 * Created by wrb on 2017/3/23.
 */
public class YBMJobEntity implements Serializable{
             private String ep_name;//": "佛挡杀佛",
            private String el_name;//": "企业名称",
            private String ep_address;//": "321",
            private String ep_education;//": 123,
            private String ep_num;//": "3",
            private String ep_start_time;//": "2017-02-02",
            private String ep_end_time;//": "2018-03-31",
            private String ep_monthly_pay;//": 132
            private String el_zone;//详细地址
            private String ep_content;//描述
            private String student_id;//": "jbqcf1bp-5859fc1a82fec"
            private String ep_id;//": 1,
            private  String bm;//报名状态，0未报名，1已报名

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getEp_id() {
        return ep_id;
    }

    public void setEp_id(String ep_id) {
        this.ep_id = ep_id;
    }

    public String getEl_zone() {
        return el_zone;
    }

    public void setEl_zone(String el_zone) {
        this.el_zone = el_zone;
    }

    public String getEp_content() {
        return ep_content;
    }

    public void setEp_content(String ep_content) {
        this.ep_content = ep_content;
    }

    public String getEp_name() {
        return ep_name;
    }

    public void setEp_name(String ep_name) {
        this.ep_name = ep_name;
    }

    public String getEl_name() {
        return el_name;
    }

    public void setEl_name(String el_name) {
        this.el_name = el_name;
    }

    public String getEp_address() {
        return ep_address;
    }

    public void setEp_address(String ep_address) {
        this.ep_address = ep_address;
    }

    public String getEp_education() {
        return ep_education;
    }

    public void setEp_education(String ep_education) {
        this.ep_education = ep_education;
    }

    public String getEp_num() {
        return ep_num;
    }

    public void setEp_num(String ep_num) {
        this.ep_num = ep_num;
    }

    public String getEp_start_time() {
        return ep_start_time;
    }

    public void setEp_start_time(String ep_start_time) {
        this.ep_start_time = ep_start_time;
    }

    public String getEp_end_time() {
        return ep_end_time;
    }

    public void setEp_end_time(String ep_end_time) {
        this.ep_end_time = ep_end_time;
    }

    public String getEp_monthly_pay() {
        return ep_monthly_pay;
    }

    public void setEp_monthly_pay(String ep_monthly_pay) {
        this.ep_monthly_pay = ep_monthly_pay;
    }
}
