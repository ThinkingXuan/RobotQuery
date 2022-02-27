package com.njcit.model.JsonModel.user;

import com.njcit.model.user.UserTask;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class UserTaskModel {
    private String message;

    private String code;

    private List<UserTask> object;

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

    public List<UserTask> getObject() {
        return object;
    }

    public void setObject(List<UserTask> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "UserTaskModel{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }
}


