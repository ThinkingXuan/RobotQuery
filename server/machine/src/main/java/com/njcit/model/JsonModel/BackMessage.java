package com.njcit.model.JsonModel;

import java.util.List;

/**
 * Created by mirko on 2017/3/30.
 * 聊天数据的返回 包括机器人的answer和查询历史聊天记录
 */
public class BackMessage {
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
        private String content;
        private String isRobot;
        private String type;
        private String time;
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIsRobot() {
            return isRobot;
        }

        public void setIsRobot(String isRobot) {
            this.isRobot = isRobot;
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
    }
}
