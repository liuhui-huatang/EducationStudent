package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 成绩分数
 * Created by wrb on 2017/3/8.
 */
public class MyExamClassEntity implements Serializable{
    private String score;//": 64,
    private String cource;//": "高等数学"

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }
}
