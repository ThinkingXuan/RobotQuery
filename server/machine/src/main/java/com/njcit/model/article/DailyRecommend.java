package com.njcit.model.article;

public class DailyRecommend {
    private String dailyRecommendId;
    private String dailyRecommendTime;
    private String dailyRecommendContent;
    private String dailyRecommendPicture;
    private String dailyRecommendTitle;
    private String templateId;
    private String dailyRecommendLineChart;
    private String dailyRecommendCircleChart;

    public String getDailyRecommendId() {
        return dailyRecommendId;
    }

    public void setDailyRecommendId(String dailyRecommendId) {
        this.dailyRecommendId = dailyRecommendId;
    }

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

    public String getDailyRecommendLineChart() {
        return dailyRecommendLineChart;
    }

    public void setDailyRecommendLineChart(String dailyRecommendLineChart) {
        this.dailyRecommendLineChart = dailyRecommendLineChart;
    }

    public String getDailyRecommendCircleChart() {
        return dailyRecommendCircleChart;
    }

    public void setDailyRecommendCircleChart(String dailyRecommendCircleChart) {
        this.dailyRecommendCircleChart = dailyRecommendCircleChart;
    }

    @Override
    public String toString() {
        return "DailyRecommend{" +
                "dailyRecommendId='" + dailyRecommendId + '\'' +
                ", dailyRecommendTime='" + dailyRecommendTime + '\'' +
                ", dailyRecommendContent='" + dailyRecommendContent + '\'' +
                ", dailyRecommendPicture='" + dailyRecommendPicture + '\'' +
                ", dailyRecommendTitle='" + dailyRecommendTitle + '\'' +
                ", templateId='" + templateId + '\'' +
                ", dailyRecommendLineChart='" + dailyRecommendLineChart + '\'' +
                ", dailyRecommendCircleChart='" + dailyRecommendCircleChart + '\'' +
                '}';
    }
}