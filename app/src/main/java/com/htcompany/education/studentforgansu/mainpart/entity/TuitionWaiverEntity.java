package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 免学费实体类
 * Created by wrb on 2017/2/22.
 */
public class TuitionWaiverEntity implements Serializable{
            private String id;//": 10,
            private String s_id;//": "q8chlbtu-585a11d299092",
            private String school_date;//": "2016~2017学年第一学期",
            private String reason;//": "成绩优秀",
            private String amount;//": "1000.00",
            private String start_time;//": 0,
            private String end_time;//": "2016-12-26",
            private String source;//": "市级财政",
            private String file;//": "",
            private String remark;//": "222"

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

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
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
}
