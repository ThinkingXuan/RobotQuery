package com.njcit.model.hr;

import java.util.Date;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserAttendance {
    private Date attendanceDate;
    private int attendanceAll;
    private int attendanceReal;

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getAttendanceAll() {
        return attendanceAll;
    }

    public void setAttendanceAll(int attendanceAll) {
        this.attendanceAll = attendanceAll;
    }

    public int getAttendanceReal() {
        return attendanceReal;
    }

    public void setAttendanceReal(int attendanceReal) {
        this.attendanceReal = attendanceReal;
    }

    @Override
    public String toString() {
        return "UserAttendance{" +
                "attendanceDate=" + attendanceDate +
                ", attendanceAll=" + attendanceAll +
                ", attendanceReal=" + attendanceReal +
                '}';
    }
}
