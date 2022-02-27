package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/25 0025.
 * 绩效考核Bean
 */

public class CheckBean implements Serializable {

    private String message;
    private String code;

    private List<CheckData> object;

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

    public List<CheckData> getObject() {
        return object;
    }

    public void setObject(List<CheckData> object) {
        this.object = object;
    }


    @Override
    public String toString() {
        return "CheckBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }

    public static class CheckData implements Serializable{

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
            return "CheckData{" +
                    "userId='" + userId + '\'' +
                    ", userAttitude=" + userAttitude +
                    ", userResponsibility=" + userResponsibility +
                    ", userWorkAbility=" + userWorkAbility +
                    ", userTrainingAbility=" + userTrainingAbility +
                    ", userTotal=" + userTotal +
                    '}';
        }
    }


}
