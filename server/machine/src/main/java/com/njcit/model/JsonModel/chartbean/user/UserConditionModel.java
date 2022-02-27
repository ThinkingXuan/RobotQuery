package com.njcit.model.JsonModel.chartbean.user;

import com.njcit.model.user.UserCondition;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class UserConditionModel {
    private String message;
    private String code;
    private List<UserCondition> object;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<UserCondition> getObject() {
        return object;
    }
    public void setObject(List<UserCondition> object) {
        this.object = object;
    }
}
