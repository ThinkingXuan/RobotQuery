package com.njcit.model.concrete_search_by_short_sql.user;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserSalaryInformationSQL {
    private String userId;
    private String userSalary;
    private String userWelfare;
    private String userGivenTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(String userSalary) {
        this.userSalary = userSalary;
    }

    public String getUserWelfare() {
        return userWelfare;
    }

    public void setUserWelfare(String userWelfare) {
        this.userWelfare = userWelfare;
    }

    public String getUserGivenTime() {
        return userGivenTime;
    }

    public void setUserGivenTime(String userGivenTime) {
        this.userGivenTime = userGivenTime;
    }

    @Override
    public String toString() {
        return "UserSalaryInformationSQL{" +
                "userId='" + userId + '\'' +
                ", userSalary='" + userSalary + '\'' +
                ", userWelfare='" + userWelfare + '\'' +
                ", userGivenTime='" + userGivenTime + '\'' +
                '}';
    }
}
