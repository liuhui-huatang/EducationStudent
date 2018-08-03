package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 惩处实体类
 * Created by wrb on 2017/1/3.
 */
public class PunishmentEntity implements Serializable{
             private String p_description;//": "考试作弊",
            private String c_reason;//": "111",
            private String ch_date;//": "2017年01月25日"

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public String getC_reason() {
        return c_reason;
    }

    public void setC_reason(String c_reason) {
        this.c_reason = c_reason;
    }

    public String getCh_date() {
        return ch_date;
    }

    public void setCh_date(String ch_date) {
        this.ch_date = ch_date;
    }
}
