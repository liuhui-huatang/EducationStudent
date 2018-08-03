package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * Created by wrb on 2016/10/20.
 * 学期
 */
public class TermEntity implements Serializable{
    private String name;//": "2015~2016学年第一学期",

    public String getOnecode() {
        return onecode;
    }

    public void setOnecode(String onecode) {
        this.onecode = onecode;
    }

    private String onecode;//": "csvi8fpl-5812c34406cb7"

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
