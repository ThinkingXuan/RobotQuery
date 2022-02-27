package com.njcit.robotquery.bean.tuling;

import java.util.List;

/**
 * Created by youxuan on 2017/4/1 0001.
 * 菜谱类
 */

public class Recipes {

    /**
     * code : 308000
     * text : 亲，已帮您找到菜谱信息
     * list : [{"name":"鱼香肉丝","icon":"http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg","info":"猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐","detailurl":"http://m.xiachufang.com/recipe/264781/"}]
     */

    private int code;
    private String text;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 鱼香肉丝
         * icon : http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg
         * info : 猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐
         * detailurl : http://m.xiachufang.com/recipe/264781/
         */

        private String name;
        private String icon;
        private String info;
        private String detailurl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getDetailurl() {
            return detailurl;
        }

        public void setDetailurl(String detailurl) {
            this.detailurl = detailurl;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "name='" + name + '\'' +
                    ", icon='" + icon + '\'' +
                    ", info='" + info + '\'' +
                    ", detailurl='" + detailurl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "code=" + code +
                ", text='" + text + '\'' +
                ", list=" + list +
                '}';
    }
}
