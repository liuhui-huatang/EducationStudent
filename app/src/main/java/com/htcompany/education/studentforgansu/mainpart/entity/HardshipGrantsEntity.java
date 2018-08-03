package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 临时困难补助
 * Created by wrb on 2017/2/22.
 */
public class HardshipGrantsEntity implements Serializable{
           private String id;//": 8,
            private String s_id;//": "q8chlbtu-585a11d299092",
            private String school_date;//": "2016~2017学年第一学期",
            private String subsidy_date;//": "2016-12-14",
            private String reason;//": "家中贫困",
            private String amount;//": "800.00",
            private String file;//": "00",
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

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getSubsidy_date() {
        return subsidy_date;
    }

    public void setSubsidy_date(String subsidy_date) {
        this.subsidy_date = subsidy_date;
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
