package com.njcit.robotquery.bean.server;

import java.util.List;

/**
 * Created by youxuan on 2017/4/5 0005.
 */

public class LoginBean {


    /**
     * message : 登陆成功
     * code : 1
     * object : [{"userId":"ce4887c9199a11e7aeb0fcd847d76266"},{"userId":"尤旋"},{"ruleName":"老板"},{"roleId","234"}]
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

    public static class ObjectBean {
        /**
         * userId : ce4887c9199a11e7aeb0fcd847d76266
         * userName : 尤旋
         * roleId : 老板
         */

        private String userId;
        private String userUsername;
        private String roleId;

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

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }
    }
}
