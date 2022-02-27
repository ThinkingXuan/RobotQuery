package com.njcit.dao.privilegeInformation.Get;

import com.njcit.model.TemplateTag;
import com.njcit.model.user.UserSalaryInformation;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class TemplateTagModel {

    private String message;

    private String code;

    private List<TemplateTag> object;

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

    public List<TemplateTag> getObject() {
        return object;
    }

    public void setObject(List<TemplateTag> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "TemplateTagModel{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }
}
