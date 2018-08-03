package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 助学金实体类
 * Created by wrb on 2017/2/22.
 */
public class StudentFinancialEntity implements Serializable{
                  private String id;//": 5,
                  private String stuid;//": "hf0fkmi5_59376f37bc34e",
                  private String reason;//": "申请理由名称",
                  private String zhuangtai;//0 已录入，1已提交，2审核未通过，3审核通过
                  private String nian;//": "2017",
                  private String yue;//": "6",
                  private String total;//": "8990.00",
                  private String miaoshu;//": "描述",
                  private String status;//": "已提交"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getNian() {
        return nian;
    }

    public void setNian(String nian) {
        this.nian = nian;
    }

    public String getYue() {
        return yue;
    }

    public void setYue(String yue) {
        this.yue = yue;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
