package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/25 0025.
 */

public class PostBean implements Serializable{
    private String message;
    private String code;

    private List<Postdata> object;

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

    public List<Postdata> getObject() {
        return object;
    }

    public void setObject(List<Postdata> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "PostBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }

    public static class Postdata implements Serializable{

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
            return "Postdata{" +
                    "postId='" + postId + '\'' +
                    ", postName='" + postName + '\'' +
                    ", postNumber=" + postNumber +
                    ", postRealNumber=" + postRealNumber +
                    '}';
        }
    }
}
