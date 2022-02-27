package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/3/25 0025.
 * 登录注册Bean
 */

public class BaseBean implements Serializable {


    /**
     * message : 登陆成功
     * code : 1
     * object : [{"userId":"ce4887c9199a11e7aeb0fcd847d76266"}]
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
         */

        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
