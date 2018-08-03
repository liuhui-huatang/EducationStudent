package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 助学金类型
 * Created by wrb on 2017/2/22.
 */
public class FinancialTypeEntity implements Serializable{
            private String id;//": 31,
            private String unkey;//": "d85pf5ea-582bb5751bb22",
            private String name;//": "国家助学金",
            private String code;//": "21",
            private String sort;//": 2,
            private String is_system;//": "2",
            private String pid;//": 2,
            private String notes;//": "2",
            private String _level;//: 1,
            private String _html;//": "",
            private String _name;//": "国家助学金"

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getIs_system() {
        return is_system;
    }

    public void setIs_system(String is_system) {
        this.is_system = is_system;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String get_level() {
        return _level;
    }

    public void set_level(String _level) {
        this._level = _level;
    }

    public String get_html() {
        return _html;
    }

    public void set_html(String _html) {
        this._html = _html;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
