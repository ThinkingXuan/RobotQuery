package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by youxuan on 2017/4/25 0025.
 */

public class AttendanceBean implements Serializable{

    private String message;
    private String code;

    private List<AttendanceData> object;

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

    public List<AttendanceData> getObject() {
        return object;
    }

    public void setObject(List<AttendanceData> object) {
        this.object = object;
    }


    @Override
    public String toString() {
        return "AttendanceBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }

    public static class AttendanceData implements Serializable{
        private Date attendanceDate;
        private int attendanceAll;
        private int attendanceReal;


        public Date getAttendance_date() {
            return attendanceDate;
        }

        public void setAttendance_date(Date attendanceDate) {
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
            return "AttendanceData{" +
                    "attendance_date=" + attendanceDate +
                    ", attendanceAll=" + attendanceAll +
                    ", attendanceReal=" + attendanceReal +
                    '}';
        }
    }
}
