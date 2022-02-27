package com.njcit.model.JsonModel.chartbean.user;

import java.util.List;

/**
 * Created by mirko on 2017/4/5.
 */
public class LoginBackUserId {


    private String message;

    private String code;

    private List<ObjectBean> object;

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

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        private String userId;
        private String userUsername;
        private String roleId;

        @Override
        public String toString() {
            return "ObjectBean{" +
                    "userId='" + userId + '\'' +
                    ", userUsername='" + userUsername + '\'' +
                    ", roleId='" + roleId + '\'' +
                    '}';
        }

        public String getUserUsername() {
            return userUsername;
        }

        public void setUserUsername(String userUsername) {
            this.userUsername = userUsername;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }


}
