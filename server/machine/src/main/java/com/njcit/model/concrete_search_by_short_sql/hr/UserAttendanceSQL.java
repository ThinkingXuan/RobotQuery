package com.njcit.model.concrete_search_by_short_sql.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserAttendanceSQL {
    private String attendanceDate;
    private String attendanceAll;
    private String attendanceReal;
    public String getAttendanceDate() {
        return attendanceDate;
    }
    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
    public String getAttendanceAll() {
        return attendanceAll;
    }
    public void setAttendanceAll(String attendanceAll) {
        this.attendanceAll = attendanceAll;
    }
    public String getAttendanceReal() {
        return attendanceReal;
    }
    public void setAttendanceReal(String attendanceReal) {
        this.attendanceReal = attendanceReal;
    }
    @Override
    public String toString() {
        return "UserAttendanceSQL{" +
                "attendanceDate='" + attendanceDate + '\'' +
                ", attendanceAll='" + attendanceAll + '\'' +
                ", attendanceReal='" + attendanceReal + '\'' +
                '}';
    }
}
