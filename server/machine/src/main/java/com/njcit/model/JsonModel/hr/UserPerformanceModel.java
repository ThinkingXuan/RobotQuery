package com.njcit.model.JsonModel.hr;
import com.njcit.model.hr.UserPerformance;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class UserPerformanceModel {
    private String message;

    private String code;

    private List<UserPerformance> object;

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

    public List<UserPerformance> getObject() {
        return object;
    }

    public void setObject(List<UserPerformance> object) {
        this.object = object;
    }
}
