package com.njcit.model;

import org.springframework.stereotype.Component;

/**
 * Created by mirko on 2017/3/14.
 */
@Component("user")
/*
    用户注册的一些信息
 */
public class User {

    private String userId;//id
    private String userNumber;//手机
    private String userUsername;//用户名
    private String userPassword;//密码
    private String roleId;//用户角色
    private String userEmail;//用户邮箱

    public String getUserNumber() {
        return userNumber;
    }
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
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
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", userUsername='" + userUsername + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", roleId='" + roleId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
