package com.njcit.model.user;

import java.util.Map;
/*
        用户查询
 */

public class TemplateModelBean {
    private String time;
    private String userId;
    private String templateId;
    private String role;             //模板权限
    private String type;            //数据展示内容
    private Map<String, String> mMap; // 查询条件
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public Map<String, String> getmMap() {
        return mMap;
    }

    public void setmMap(Map<String, String> mMap) {
        this.mMap = mMap;
    }

    @Override
    public String toString() {
        return "TemplateModelBean{" +
                "time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", mMap=" + mMap +
                '}';
    }
}
