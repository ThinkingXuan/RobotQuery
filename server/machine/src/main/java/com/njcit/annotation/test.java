package com.njcit.annotation;

/**
 * Created by mirko on 2017/4/24.
 */
public class test {


    /**
     * mMap : {"user_gender":"user_gender='男'"}
     * role : 老板
     * templateId : employee
     * time : 2017-04-24 20:16:00
     * type : text
     * userId : ce4887c9199a11e7aeb0fcd847d76266
     */

    private MMapBean mMap;
    private String role;
    private String templateId;
    private String time;
    private String type;
    private String userId;

    public MMapBean getMMap() {
        return mMap;
    }

    public void setMMap(MMapBean mMap) {
        this.mMap = mMap;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class MMapBean {
        /**
         * user_gender : user_gender='男'
         */

        private String user_gender;

        public String getUser_gender() {
            return user_gender;
        }

        public void setUser_gender(String user_gender) {
            this.user_gender = user_gender;
        }
    }
}
