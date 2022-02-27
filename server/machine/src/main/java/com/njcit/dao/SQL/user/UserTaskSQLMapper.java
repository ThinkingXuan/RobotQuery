package com.njcit.dao.SQL.user;

import com.njcit.model.concrete_search_by_short_sql.user.UserTaskSQL;
import com.njcit.model.user.UserTask;

import java.util.List;

/**
 * Created by mirko on 2017/4/25.
 */
public interface UserTaskSQLMapper {

    List<UserTask> getUserTask(UserTaskSQL userTaskSQL);
}
