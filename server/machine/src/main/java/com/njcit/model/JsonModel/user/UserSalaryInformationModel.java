package com.njcit.model.JsonModel.user;

import com.njcit.model.user.UserSalaryInformation;
import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class UserSalaryInformationModel {

    private String message;

    private String code;


    private List<UserSalaryInformation> object;
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
    public List<UserSalaryInformation> getObject() {
        return object;
    }
    public void setObject(List<UserSalaryInformation> object) {
        this.object = object;
    }


}