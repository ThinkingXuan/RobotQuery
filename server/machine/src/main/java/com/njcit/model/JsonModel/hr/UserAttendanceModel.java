package com.njcit.model.JsonModel.hr;
import com.njcit.model.hr.UserAttendance;
import java.util.List;
/**
 * Created by mirko on 2017/4/10.
 */
public class UserAttendanceModel {
    private String message;

    private String code;

    private List<UserAttendance> object;

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

    public List<UserAttendance> getObject() {
        return object;
    }

    public void setObject(List<UserAttendance> object) {
        this.object = object;
    }
}
