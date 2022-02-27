package com.njcit.model.JsonModel.chartbean.article;
import com.njcit.model.article.DailyRecommend;

import java.util.List;
/**
 * Created by mirko on 2017/4/10.
 */
public class DailyRecommendModel {
    private String message;
    private String code;
    private List<DailyRecommend> object;
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

    public List<DailyRecommend> getObject() {
        return object;
    }

    public void setObject(List<DailyRecommend> object) {
        this.object = object;
    }
}

