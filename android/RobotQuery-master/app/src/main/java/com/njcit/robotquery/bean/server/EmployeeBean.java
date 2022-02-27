package com.njcit.robotquery.bean.server;

import com.njcit.robotquery.bean.local.TemplateBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by youxuan on 2017/4/24 0024.
 */

public class EmployeeBean implements Serializable {
    private String message;
    private String code;
    private List<EmployeeData> object;

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

    public List<EmployeeData> getObject() {
        return object;
    }

    public void setObject(List<EmployeeData> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }

    public static class EmployeeData implements Serializable{
        private String userId;
        private String userUsername;
        private String userPosition;
        private Date userBirthday;
        private int userSalary;
        private String userWorkDate;
        private String userSchool;
        private String userDepartment;
        private String userGender;
        private String userHometown;
        private String userEducation;
        private int userAge;


        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserUsername() {
            return userUsername;
        }

        public void setUserUsername(String userUsername) {
            this.userUsername = userUsername;
        }

        public String getUserPosition() {
            return userPosition;
        }

        public void setUserPosition(String userPosition) {
            this.userPosition = userPosition;
        }

        public Date getUserBirthday() {
            return userBirthday;
        }

        public void setUserBirthday(Date userBirthday) {
            this.userBirthday = userBirthday;
        }

        public int getUserSalary() {
            return userSalary;
        }

        public void setUserSalary(int userSalary) {
            this.userSalary = userSalary;
        }

        public String getUserWorkDate() {
            return userWorkDate;
        }

        public void setUserWorkDate(String userWorkDate) {
            this.userWorkDate = userWorkDate;
        }

        public String getUserSchool() {
            return userSchool;
        }

        public void setUserSchool(String userSchool) {
            this.userSchool = userSchool;
        }

        public String getUserDepartment() {
            return userDepartment;
        }

        public void setUserDepartment(String userDepartment) {
            this.userDepartment = userDepartment;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserHometown() {
            return userHometown;
        }

        public void setUserHometown(String userHometown) {
            this.userHometown = userHometown;
        }

        public String getUserEducation() {
            return userEducation;
        }

        public void setUserEducation(String userEducation) {
            this.userEducation = userEducation;
        }

        public int getUserAge() {
            return userAge;
        }

        public void setUserAge(int userAge) {
            this.userAge = userAge;
        }

        @Override
        public String toString() {
            return "EmployeeData{" +
                    "userId='" + userId + '\'' +
                    ", userUsername='" + userUsername + '\'' +
                    ", userPosition='" + userPosition + '\'' +
                    ", userBirthday=" + userBirthday +
                    ", userSalary=" + userSalary +
                    ", userWorkDate='" + userWorkDate + '\'' +
                    ", userSchool='" + userSchool + '\'' +
                    ", userDepartment='" + userDepartment + '\'' +
                    ", userGender='" + userGender + '\'' +
                    ", userHometown='" + userHometown + '\'' +
                    ", userEducation='" + userEducation + '\'' +
                    ", userAge=" + userAge +
                    '}';
        }
    }



}
