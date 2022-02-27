package com.njcit.model.concrete_search_by_short_sql.user;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserTaskSQL {

    private String userId;
    private String taskId;
    private String taskContent;
    private String taskExpiryDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskExpiryDate() {
        return taskExpiryDate;
    }

    public void setTaskExpiryDate(String taskExpiryDate) {
        this.taskExpiryDate = taskExpiryDate;
    }

    @Override
    public String toString() {
        return "UserTaskSQL{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", taskExpiryDate='" + taskExpiryDate + '\'' +
                '}';
    }
}
