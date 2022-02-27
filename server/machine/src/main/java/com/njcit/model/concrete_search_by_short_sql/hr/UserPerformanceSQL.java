package com.njcit.model.concrete_search_by_short_sql.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserPerformanceSQL {
    private String userId;
    private String userAttitude;
    private String userResponsibility;
    private String userWorkAbility;
    private String userTrainingAbility;
    private String userTotal;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAttitude() {
        return userAttitude;
    }

    public void setUserAttitude(String userAttitude) {
        this.userAttitude = userAttitude;
    }

    public String getUserResponsibility() {
        return userResponsibility;
    }

    public void setUserResponsibility(String userResponsibility) {
        this.userResponsibility = userResponsibility;
    }

    public String getUserWorkAbility() {
        return userWorkAbility;
    }

    public void setUserWorkAbility(String userWorkAbility) {
        this.userWorkAbility = userWorkAbility;
    }

    public String getUserTrainingAbility() {
        return userTrainingAbility;
    }

    public void setUserTrainingAbility(String userTrainingAbility) {
        this.userTrainingAbility = userTrainingAbility;
    }

    public String getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(String userTotal) {
        this.userTotal = userTotal;
    }

    @Override
    public String toString() {
        return "UserPerformanceSQL{" +
                "userId='" + userId + '\'' +
                ", userAttitude='" + userAttitude + '\'' +
                ", userResponsibility='" + userResponsibility + '\'' +
                ", userWorkAbility='" + userWorkAbility + '\'' +
                ", userTrainingAbility='" + userTrainingAbility + '\'' +
                ", userTotal='" + userTotal + '\'' +
                '}';
    }
}
