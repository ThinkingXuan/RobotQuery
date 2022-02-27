package com.njcit.model.user;

/**
 * Created by mirko on 2017/4/27.
 */
public class UserConcern {
    private String templateId;
    private String userId;

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

    @Override
    public String toString() {
        return "UserConcernService{" +
                "templateId='" + templateId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
