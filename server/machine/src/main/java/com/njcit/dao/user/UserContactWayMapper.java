package com.njcit.dao.user;

import com.njcit.model.user.UserContactWay;

public interface UserContactWayMapper {
    UserContactWay selectByPrimaryKey(String userId);
    int deleteByPrimaryKey(String userId);
    int insert(UserContactWay record);
    int insertSelective(UserContactWay record);
    int updateByPrimaryKeySelective(UserContactWay record);
    int updateByPrimaryKey(UserContactWay record);
}