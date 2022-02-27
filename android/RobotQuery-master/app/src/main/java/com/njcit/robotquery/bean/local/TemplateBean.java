package com.njcit.robotquery.bean.local;


import java.io.Serializable;
import java.util.Map;

/**
 * Created by youxuan on 2017/4/5 0005.
 * 模板标签的Bean
 */

public class TemplateBean implements Serializable{

    private String time;
    private String userId;
    private String templateId;
    private String role;             //模板权限
    private String type;            //数据展示内容

    private Map<String, String> mMap; // 查询条件

    public TemplateBean() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getMap() {
        return mMap;
    }

    public void setMap(Map<String, String> map) {
        mMap = map;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "TemplateBean{" +
                "time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", mMap=" + mMap +
                '}';
    }
}
