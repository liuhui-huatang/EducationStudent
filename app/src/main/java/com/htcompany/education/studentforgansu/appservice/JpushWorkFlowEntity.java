package com.htcompany.education.studentforgansu.appservice;

import java.io.Serializable;

/**
 * Created by weiruibin on 2017/6/29.
 */

public class JpushWorkFlowEntity implements Serializable{
    private String flow_id;//":34,
    private String type;//":1,
    private String id;//":909,
    private String content;//":"fdsagdsg"}
    private String protype;//1待办事项，2业务跟踪详情

    public String getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }
}
