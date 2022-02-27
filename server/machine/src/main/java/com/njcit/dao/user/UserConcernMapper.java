package com.njcit.dao.user;

import com.njcit.model.user.UserConcern;

import java.util.List;

/**
 * Created by mirko on 2017/4/27.
 */
public interface UserConcernMapper {

    void insertNewConcern(String userId,String templateId);

    void CancelConcern(String userId,String templateId);

    List<String> getConcern(String userId);

}
