package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 我的成绩实体类
 * Created by wrb on 2016/12/6.
 */
public class MyExamEntity implements Serializable{
          private String total;//总分
          private String paiming;//排名
         private String currentTerm;//当前学期
    private List<MyExamClassEntity> chengji;//成绩数据


    public String getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(String currentTerm) {
        this.currentTerm = currentTerm;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
    }

    public List<MyExamClassEntity> getChengji() {
        return chengji;
    }

    public void setChengji(List<MyExamClassEntity> chengji) {
        this.chengji = chengji;
    }
}
