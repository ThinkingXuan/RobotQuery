package com.njcit.dao.SQL.user;

import com.njcit.model.concrete_search_by_short_sql.UserConditionSQL;
import com.njcit.model.user.UserCondition;

import java.util.List;

/**
 * Created by mirko on 2017/4/19.
 */
public interface UserConditionSQLMapper {

    List<UserCondition> getUserConditioin(UserConditionSQL userConditionSQL);

}
