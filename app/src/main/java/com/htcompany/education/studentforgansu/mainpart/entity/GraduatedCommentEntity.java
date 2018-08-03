package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 毕业评语
 * Created by wrb on 2017/3/1.
 */
public class GraduatedCommentEntity implements Serializable{
    private String student;
    private String pinyu;
    private String teacher;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getPinyu() {
        return pinyu;
    }

    public void setPinyu(String pinyu) {
        this.pinyu = pinyu;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
