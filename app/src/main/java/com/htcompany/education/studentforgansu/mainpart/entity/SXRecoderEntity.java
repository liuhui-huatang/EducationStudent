package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 实习记录实体
 * Created by wrb on 2017/3/24.
 */
public class SXRecoderEntity implements Serializable{
           private String name;//": "2015~2016学年第二学期",
            private String el_name;//": null,
            private String er_work_start_date;//": "2017-03-24",
            private String ep_name;//": null,
            private String er_wages;//": "123",
            private String er_employment_place;//": "123",
            private String zt;//": 1

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

    public String getEr_work_start_date() {
        return er_work_start_date;
    }

    public void setEr_work_start_date(String er_work_start_date) {
        this.er_work_start_date = er_work_start_date;
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

    public String getEr_employment_place() {
        return er_employment_place;
    }

    public void setEr_employment_place(String er_employment_place) {
        this.er_employment_place = er_employment_place;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
