package com.njcit.model;

import org.springframework.stereotype.Component;

/**
 * Created by mirko on 2017/3/29.
 */
@Component("message")
public class Message {

    private String messageId;

    private String userId;

    private String time;

    private String content;

    private String isRobot;

    private String type;

    public String getIsRobot() {
        return isRobot;
    }

    public void setIsRobot(String isRobot) {
        this.isRobot = isRobot;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", userId='" + userId + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", isRobot='" + isRobot + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
