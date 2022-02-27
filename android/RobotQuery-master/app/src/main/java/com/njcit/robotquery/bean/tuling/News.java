package com.njcit.robotquery.bean.tuling;

import java.util.List;

/**
 * Created by youxuan on 2017/4/1 0001.
 * 新闻类
 */

public class News {

    /**
     * code : 302000
     * text : 亲，已帮您找到相关新闻
     * list : [{"article":"工信部:今年将大幅提网速降手机流量费","source":"网易新闻","icon":"","detailurl":"http://news.163.com/15/0416/03/AN9SORGH0001124J.html"},{"article":"北京最强沙尘暴午后袭沪 当地叫停广场舞","source":"网易新闻","icon":"","detailurl":"http://news.163.com/15/0416/14/ANB2VKVC00011229.html"},{"article":"公安部:小客车驾照年内试点自学直考","source":"网易新闻","icon":"","detailurl":"http://news.163.com/15/0416/01/AN9MM7CK00014AED.html"}]
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
         * article : 工信部:今年将大幅提网速降手机流量费
         * source : 网易新闻
         * icon :
         * detailurl : http://news.163.com/15/0416/03/AN9SORGH0001124J.html
         */

        private String article;
        private String source;
        private String icon;
        private String detailurl;

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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
                    "article='" + article + '\'' +
                    ", source='" + source + '\'' +
                    ", icon='" + icon + '\'' +
                    ", detailurl='" + detailurl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "code=" + code +
                ", text='" + text + '\'' +
                ", list=" + list +
                '}';
    }
}
