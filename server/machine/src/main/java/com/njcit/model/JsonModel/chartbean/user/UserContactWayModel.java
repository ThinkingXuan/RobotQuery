package com.njcit.model.JsonModel.chartbean.user;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class UserContactWayModel {
    private String message;

    private String code;

    private List<UserContactWayModel.ObjectBean> object;
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
    public List<UserContactWayModel.ObjectBean> getObject() {
        return object;
    }
    public void setObject(List<UserContactWayModel.ObjectBean> object) {
        this.object = object;
    }
    public static class ObjectBean {
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
    }
}
