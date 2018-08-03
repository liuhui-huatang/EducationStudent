package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 请假实体类
 * Created by wrb on 2017/1/3.
 */
public class LeaveApplyEntity implements Serializable{
                    private String id;//": 5,
                    private String unkey;//": "tu4p7457_5951b5150ab34",
                    private String s_id;//": "hf0fkmi5_59376f37bc34e",
                    private String type;//": "事假",
                    private String reason;//": "回来了",
                    private String start_time;//": "2017-06-27",
                    private String end_time;//": "2017-06-27",
                    private String remark;//": null,
                    private String status;//": "已录入",
                    private String xjstatus;//": 2,
                    private String xjtime;//": null,
                    private String data_1;//": 0,
                    private String xzb;//": "影视2017级一班"

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_1() {
        return data_1;
    }

    public void setData_1(String data_1) {
        this.data_1 = data_1;
    }

    public String getXzb() {
        return xzb;
    }

    public void setXzb(String xzb) {
        this.xzb = xzb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnkey() {
        return unkey;
    }

    public void setUnkey(String unkey) {
        this.unkey = unkey;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getXjstatus() {
        return xjstatus;
    }

    public void setXjstatus(String xjstatus) {
        this.xjstatus = xjstatus;
    }

    public String getXjtime() {
        return xjtime;
    }

    public void setXjtime(String xjtime) {
        this.xjtime = xjtime;
    }
}
