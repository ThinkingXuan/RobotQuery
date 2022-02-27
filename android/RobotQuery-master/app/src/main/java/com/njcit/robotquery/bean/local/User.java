package com.njcit.robotquery.bean.local;

/**
 * Created by youxuan on 2017/3/30 0030.
 */

public class User {

    private String userId;
    private String email;
    private String username;
    private String password;
    private String rule;

    public User() {
    }

    public User(String userId,String email, String username, String password, String rule) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rule = rule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
