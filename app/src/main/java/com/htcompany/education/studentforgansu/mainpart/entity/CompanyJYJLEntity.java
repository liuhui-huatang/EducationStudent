package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 就业记录实体
 * Created by wrb on 2017/3/23.
 */
public class CompanyJYJLEntity implements Serializable{
            private String name;//": "2015~2016学年第二学期",
            private String el_name;//": "企业名称",
            private String ep_name;//": "123",
            private String er_wages;//": "8888",
            private String ep_address;//": "123",
            private String er_sign_time;//": "2017-03-23"
            private String zt;//: 0
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEl_name() {
        return el_name;
    }

    public void setEl_name(String el_name) {
        this.el_name = el_name;
    }

    public String getEp_name() {
        return ep_name;
    }

    public void setEp_name(String ep_name) {
        this.ep_name = ep_name;
    }

    public String getEr_wages() {
        return er_wages;
    }

    public void setEr_wages(String er_wages) {
        this.er_wages = er_wages;
    }

    public String getEp_address() {
        return ep_address;
    }

    public void setEp_address(String ep_address) {
        this.ep_address = ep_address;
    }

    public String getEr_sign_time() {
        return er_sign_time;
    }

    public void setEr_sign_time(String er_sign_time) {
        this.er_sign_time = er_sign_time;
    }
}
