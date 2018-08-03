package com.htcompany.education.studentforgansu.mainpart.entity;

import java.io.Serializable;

/**
 * 技能大赛详情
 * Created by wrb on 2017/2/27.
 */
public class SkillsGameDetailEntity implements Serializable{
           private String id;//": 1,
            private String m_id;//": "pcl1fc7o-5846a83ff1119",
            private String name;//": "贴标签比赛",
            private String match_type;//": "限时计数",
            private String match_jb;//": "联合举办",
            private String match_unit;//": "外联部",
            private String match_date;//": "2016-12-06",
            private String judges;//": "张三"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatch_type() {
        return match_type;
    }

    public void setMatch_type(String match_type) {
        this.match_type = match_type;
    }

    public String getMatch_jb() {
        return match_jb;
    }

    public void setMatch_jb(String match_jb) {
        this.match_jb = match_jb;
    }

    public String getMatch_unit() {
        return match_unit;
    }

    public void setMatch_unit(String match_unit) {
        this.match_unit = match_unit;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getJudges() {
        return judges;
    }

    public void setJudges(String judges) {
        this.judges = judges;
    }
}
