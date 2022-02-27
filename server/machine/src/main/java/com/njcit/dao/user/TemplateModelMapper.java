package com.njcit.dao.user;

import com.njcit.model.user.UserSearchCondition;

import java.util.List;

/**
 * Created by mirko on 2017/4/14.
 */
public interface TemplateModelMapper {

    List<UserSearchCondition> getConcernByUserIdAndTemplateId(String userId, String templateId);

    void insertNewConcern(UserSearchCondition userSearchCondition);

    void deleteConcern(String userId,String templateId);



}
