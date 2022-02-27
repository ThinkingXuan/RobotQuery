package com.njcit.model.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class PostRecruit {
    private String postId;
    private String postName;
    private int postNumber;
    private int postRealNumber;

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

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public int getPostRealNumber() {
        return postRealNumber;
    }

    public void setPostRealNumber(int postRealNumber) {
        this.postRealNumber = postRealNumber;
    }

    @Override
    public String toString() {
        return "PostRecruit{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", postNumber=" + postNumber +
                ", postRealNumber=" + postRealNumber +
                '}';
    }
}
