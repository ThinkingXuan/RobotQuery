package com.njcit.model.user;

/**
 * Created by mirko on 2017/4/14.
 */
/*
用户保存的插叙条件
 */
public class UserSearchCondition {
    private String time;
    private String templateId;
    private String userId;
    private String role;
    private String type;
    private String content;

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserSearchCondition{" +
                "time='" + time + '\'' +
                ", templateId='" + templateId + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
