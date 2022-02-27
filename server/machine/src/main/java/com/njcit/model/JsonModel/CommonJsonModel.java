package com.njcit.model.JsonModel;

import java.util.List;

/**
 * Created by mirko on 2017/3/30.
 * 公共数据返回模型 object一般为空
 */

public class CommonJsonModel {

    private String message;
    private String code;

    private List<?> object;

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

    public List<?> getObject() {
        return object;
    }

    public void setObject(List<?> object) {
        this.object = object;
    }
}
