package com.njcit.model.user;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserSalaryInformation {

    private String userId;
    private int userSalary;
    private int userWelfare;
    private String userGivenTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(int userSalary) {
        this.userSalary = userSalary;
    }

    public int getUserWelfare() {
        return userWelfare;
    }

    public void setUserWelfare(int userWelfare) {
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
                ", userSalary=" + userSalary +
                ", userWelfare=" + userWelfare +
                ", userGivenTime='" + userGivenTime + '\'' +
                '}';
    }
}
