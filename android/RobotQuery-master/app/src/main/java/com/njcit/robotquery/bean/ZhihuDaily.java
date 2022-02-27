package com.njcit.robotquery.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by youxuan on 2016/12/19.
 */

public class ZhihuDaily {


    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private List<ZhjhuDailyItem> mZhihuDailyItems;
    @SerializedName("stories")
    private List<ZhjhuDailyItem> stories;

    public List<ZhjhuDailyItem> getZhihuDailyItems() {
        return mZhihuDailyItems;
    }

    public void setZhihuDailyItems(List<ZhjhuDailyItem> zhihuDailyItems) {
        mZhihuDailyItems = zhihuDailyItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhjhuDailyItem> getStories() {
        return stories;
    }

    public void setStories(List<ZhjhuDailyItem> stories) {
        this.stories = stories;
    }
}