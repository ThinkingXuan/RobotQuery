package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/10 0010.
 */

public class Employee implements Serializable{


    /**
     * message : 获取成功
     * code : 1
     * object : [{"userId":"00","userPhone":"11","userQQ":"22","userWechat":"33","userEmail":"44","userTelephone":"55"}]
     */

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

    public static class ObjectBean implements Serializable {
        /**
         * userId : 00
         * userPhone : 11
         * userQQ : 22
         * userWechat : 33
         * userEmail : 44
         * userTelephone : 55
         */

        private String userId;
        private String userPhone;
        private String userQQ;
        private String userWechat;
        private String userEmail;
        private String userTelephone;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserQQ() {
            return userQQ;
        }

        public void setUserQQ(String userQQ) {
            this.userQQ = userQQ;
        }

        public String getUserWechat() {
            return userWechat;
        }

        public void setUserWechat(String userWechat) {
            this.userWechat = userWechat;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserTelephone() {
            return userTelephone;
        }

        public void setUserTelephone(String userTelephone) {
            this.userTelephone = userTelephone;
        }

        @Override
        public String toString() {
            return "ObjectBean{" +
                    "userId='" + userId + '\'' +
                    ", userPhone='" + userPhone + '\'' +
                    ", userQQ='" + userQQ + '\'' +
                    ", userWechat='" + userWechat + '\'' +
                    ", userEmail='" + userEmail + '\'' +
                    ", userTelephone='" + userTelephone + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }
}
