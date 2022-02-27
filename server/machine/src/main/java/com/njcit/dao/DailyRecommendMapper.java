package com.njcit.dao;

import com.njcit.model.article.DailyRecommend;

import java.util.List;

public interface DailyRecommendMapper {

    int insert(DailyRecommend record);

    List<DailyRecommend> getRecommend(int page,int size);

    int insertSelective(DailyRecommend record);

    List<DailyRecommend> getRecommendByTemplateId(String templateId,int page,int size);


}