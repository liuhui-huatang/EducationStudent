package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MainLeftMenuParentEntity implements Serializable{
    private String pname;//名称
    private int pdid; //图片id
    private int postionId;//第几项

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public int getPostionId() {
        return postionId;
    }

    public void setPostionId(int postionId) {
        this.postionId = postionId;
    }
}
