package com.njcit.model.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserPerformance {


    private String userId;
    private int userAttitude;
    private int userResponsibility;
    private int userWorkAbility;
    private int userTrainingAbility;
    private int userTotal;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public int getUserAttitude() {
        return userAttitude;
    }
    public void setUserAttitude(int userAttitude) {
        this.userAttitude = userAttitude;
    }
    public int getUserResponsibility() {
        return userResponsibility;
    }
    public void setUserResponsibility(int userResponsibility) {
        this.userResponsibility = userResponsibility;
    }
    public int getUserWorkAbility() {
        return userWorkAbility;
    }
    public void setUserWorkAbility(int userWorkAbility) {
        this.userWorkAbility = userWorkAbility;
    }
    public int getUserTrainingAbility() {
        return userTrainingAbility;
    }
    public void setUserTrainingAbility(int userTrainingAbility) {
        this.userTrainingAbility = userTrainingAbility;
    }
    public int getUserTotal() {
        return userTotal;
    }
    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    @Override
    public String toString() {
        return "UserPerformanceModel{" +
                "userId='" + userId + '\'' +
                ", userAttitude=" + userAttitude +
                ", userResponsibility=" + userResponsibility +
                ", userWorkAbility=" + userWorkAbility +
                ", userTrainingAbility=" + userTrainingAbility +
                ", userTotal=" + userTotal +
                '}';
    }
}
