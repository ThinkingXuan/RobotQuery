package com.njcit.model.JsonModel.chartbean.Line;

import java.util.List;

/**
 * Created by mirko on 2017/4/13.
 */
public class LineModel {
    /**
     * code : 1
     * message : 成功
     * entryList : [{"x":1,"y":3},{"x":2,"y":4},{"x":3,"y":5},{"x":4,"y":6},{"x":5,"y":7}]
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
            return "EntryListBean{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
