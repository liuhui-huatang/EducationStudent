package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 课程实体类
 * Created by wrb on 2017/2/13.
 */
public class KeChengEntity implements Serializable{
            private String label;//": "高等数学",
            private String value;//": "5"

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
