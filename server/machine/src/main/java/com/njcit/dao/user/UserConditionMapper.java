package com.njcit.dao.user;
import com.njcit.model.user.UserCondition;

public interface UserConditionMapper {

    UserCondition selectByPrimaryKey(String userId);
}