package com.njcit.robotquery.bean.server.chartbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/13 0013.
 * 折线图Bean
 */

public class LineBean implements Serializable {


    /**
     * code : 1
     * message : 获取成功
     * entryList : [{"x":"1","y":"20"},{"x":"2","y":"30"},{"x":"3","y":"50"},{"x":"4","y":"60"}]
     *
     */

    private String code;
    private String message;
    private List<LinePoint> entryList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LinePoint> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<LinePoint> entryList) {
        this.entryList = entryList;
    }

    public static class LinePoint implements Serializable {

        /**
         * x : 1
         * y : 20
         */

        private String x;
        private String y;

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "LinePoint{" +
                    "x='" + x + '\'' +
                    ", y='" + y + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LineBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", entryList=" + entryList +
                '}';
    }
}
