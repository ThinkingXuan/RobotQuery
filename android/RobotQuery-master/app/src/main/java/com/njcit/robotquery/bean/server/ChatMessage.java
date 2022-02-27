package com.njcit.robotquery.bean.server;


import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/3/25 0025.
 * 接收服务端的消息
 */

public class ChatMessage implements Serializable {

    /**
     * message : 操作成功
     * code : 0
     * object : [{"content":"哈哈","type":"text","time":"2017-3-25 14:59:15","isrobot":"1"}]
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
         * content : 哈哈
         * type : text
         * time : 2017-3-25 14:59:15
         * isRobot : 1
         */

        private String content;
        private String type;
        private String time;
        private String isRobot;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getIsRobot() {
            return isRobot;
        }

        public void setIsRobot(String isRobot) {
            this.isRobot = isRobot;
        }
    }
}
