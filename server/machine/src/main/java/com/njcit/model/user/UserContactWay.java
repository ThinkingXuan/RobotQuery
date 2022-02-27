package com.njcit.model.user;

public class UserContactWay {
    private String userId;

    private String userPhone;

    private String userQq;

    private String userWechat;

    private String userEmail;

    private String userTelephone;

    public UserContactWay(String userId, String userPhone, String userQq, String userWechat, String userEmail, String userTelephone) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userQq = userQq;
        this.userWechat = userWechat;
        this.userEmail = userEmail;
        this.userTelephone = userTelephone;
    }

    public UserContactWay() {
        super();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public String getUserWechat() {
        return userWechat;
    }

    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat == null ? null : userWechat.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    @Override
    public String toString() {
        return "UserContactWayModel{" +
                "userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userQq='" + userQq + '\'' +
                ", userWechat='" + userWechat + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                '}';
    }
}