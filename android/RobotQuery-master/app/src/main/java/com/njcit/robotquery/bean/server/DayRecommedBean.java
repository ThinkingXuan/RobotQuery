package com.njcit.robotquery.bean.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/14 0014.
 */

public class DayRecommedBean implements Serializable {


    /**
     * message : 获取成功
     * code : 1
     * object : [
     * {"dailyRecommendTime":"2017-04-14 14:29:07",
     * "dailyRecommendContent":"最新销量出炉啦~~",
     * "dailyRecommendPicture":"http://oodmh8sl5.bkt.clouddn.com/5397ae6a15eab.jpg",
     * "dailyRecommendTitle":"2017-04-14销量出炉啦~~~",
     * "dailyRecommendType":"employee"}
     * <p>
     * ]
     */

    private String message;
    private String code;
    @SerializedName("object")
    private List<DataBean> ResultList;

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

    public List<DataBean> getResultList() {
        return ResultList;
    }

    public void setResultList(List<DataBean> ResultList) {
        this.ResultList = ResultList;
    }

    public static class DataBean {
        /**
         * dailyRecommendTime : 2017-04-14 14:29:07
         * dailyRecommendContent : 最新销量出炉啦~~
         * dailyRecommendPicture : http://oodmh8sl5.bkt.clouddn.com/5397ae6a15eab.jpg
         * dailyRecommendTitle : 2017-04-14销量出炉啦~~~
         * templateId : employee
         */

        private String dailyRecommendTime;
        private String dailyRecommendContent;
        private String dailyRecommendPicture;
        private String dailyRecommendTitle;
        private String templateId;

        public String getDailyRecommendTime() {
            return dailyRecommendTime;
        }

        public void setDailyRecommendTime(String dailyRecommendTime) {
            this.dailyRecommendTime = dailyRecommendTime;
        }

        public String getDailyRecommendContent() {
            return dailyRecommendContent;
        }

        public void setDailyRecommendContent(String dailyRecommendContent) {
            this.dailyRecommendContent = dailyRecommendContent;
        }

        public String getDailyRecommendPicture() {
            return dailyRecommendPicture;
        }

        public void setDailyRecommendPicture(String dailyRecommendPicture) {
            this.dailyRecommendPicture = dailyRecommendPicture;
        }

        public String getDailyRecommendTitle() {
            return dailyRecommendTitle;
        }

        public void setDailyRecommendTitle(String dailyRecommendTitle) {
            this.dailyRecommendTitle = dailyRecommendTitle;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }
    }
}
