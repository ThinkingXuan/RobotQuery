package com.njcit.model.concrete_search_by_short_sql;

import java.util.Date;

/**
 * Created by mirko on 2017/4/19.
 */
public class UserConditionSQL {

    private String userId;
    private String userUsername;
    private String userPosition;
    private String userBirthday;
    private String userSalary;
    private String userWorkDate;
    private String userSchool;
    private String userDepartment;
    private String userGender;
    private String userHometown;
    private String userEducation;
    private String userAge;

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

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

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(String userSalary) {
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

    @Override
    public String toString() {
        return "UserConditionSQL{" +
                "userId='" + userId + '\'' +
                ", userUsername='" + userUsername + '\'' +
                ", userPosition='" + userPosition + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userSalary='" + userSalary + '\'' +
                ", userWorkDate='" + userWorkDate + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userDepartment='" + userDepartment + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userHometown='" + userHometown + '\'' +
                ", userEducation='" + userEducation + '\'' +
                ", userAge='" + userAge + '\'' +
                '}';
    }
}
