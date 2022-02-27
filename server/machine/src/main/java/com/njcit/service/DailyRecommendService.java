package com.njcit.service;

import com.njcit.model.article.DailyRecommend;

import java.util.List;

/**
 * Created by mirko on 2017/4/14.
 */
public interface DailyRecommendService {

    void insert();

    String getRecommend(String userId,int page,int size);

    void insertSelective(DailyRecommend record);

    String getRecommendByUserId(String userId,int page,int size);

    String getConcernedRecommendByUserId(String userId,int page,int size);


}
