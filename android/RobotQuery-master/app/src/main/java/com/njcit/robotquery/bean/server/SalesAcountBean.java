package com.njcit.robotquery.bean.server;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class SalesAcountBean {


    /**
     * code : 1
     * message : 获取成功
     * entryList : [{"x":"2017-04-17 11:44:00.0","y":"25"},{"x":"2017-05-17 11:44:00.0","y":"26"},{"x":"2017-06-17 11:44:00.0","y":"28"},{"x":"2017-07-17 11:44:00.0","y":"30"}]
     */

    private String code;
    private String message;
    private List<EntryListBean> entryList;

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

    public List<EntryListBean> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryListBean> entryList) {
        this.entryList = entryList;
    }

    public static class EntryListBean {
        /**
         * x : 2017-04-17 11:44:00.0
         * y : 25
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
    }
}
