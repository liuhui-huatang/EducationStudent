package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 奖学金实体类
 * Created by wrb on 2017/2/21.
 */
public class ScholarshipEntity implements Serializable{
            private String id;//": 8,
            private String s_id;//": "q8chlbtu-585a11d299092",
            private String title;//": "2017届",
            private String code;//": "0001",
            private String level;//": "一等奖学金",
            private String type;//": "国家奖助类",
            private String amount;//": "10000.00",
            private String school_date;//": "2016~2017学年第一学期",
            private String price_date;//": "20170120",
            private String zizhu;//": "",
            private String source;//": "省级财政",
            private String file;//": "",
            private String remark;//": "",
            private String status;//": "已提交"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getPrice_date() {
        return price_date;
    }

    public void setPrice_date(String price_date) {
        this.price_date = price_date;
    }

    public String getZizhu() {
        return zizhu;
    }

    public void setZizhu(String zizhu) {
        this.zizhu = zizhu;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
