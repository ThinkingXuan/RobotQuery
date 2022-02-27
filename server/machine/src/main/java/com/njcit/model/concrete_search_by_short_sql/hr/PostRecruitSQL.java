package com.njcit.model.concrete_search_by_short_sql.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class PostRecruitSQL {
    private String postId;
    private String postName;
    private String postNumber;
    private String postRealNumber;
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getPostName() {
        return postName;
    }
    public void setPostName(String postName) {
        this.postName = postName;
    }
    public String getPostNumber() {
        return postNumber;
    }
    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }
    public String getPostRealNumber() {
        return postRealNumber;
    }
    public void setPostRealNumber(String postRealNumber) {
        this.postRealNumber = postRealNumber;
    }
    @Override
    public String toString() {
        return "PostRecruitSQL{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", postNumber='" + postNumber + '\'' +
                ", postRealNumber='" + postRealNumber + '\'' +
                '}';
    }
}
