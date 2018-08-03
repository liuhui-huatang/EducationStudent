package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MainLeftMenuChildEntity implements Serializable{
    private String cname;//名称
    private int cdid; //图片id
    private int postionId;//第几项
    private String viewflag;//界面标示

    public String getViewflag() {
        return viewflag;
    }

    public void setViewflag(String viewflag) {
        this.viewflag = viewflag;
    }

    public int getPostionId() {
        return postionId;
    }

    public void setPostionId(int postionId) {
        this.postionId = postionId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCdid() {
        return cdid;
    }

    public void setCdid(int cdid) {
        this.cdid = cdid;
    }
}
