package com.njcit.dao.SQL.user;

import com.njcit.model.concrete_search_by_short_sql.user.UserSalaryInformationSQL;
import com.njcit.model.user.UserSalaryInformation;

import java.util.List;

/**
 * Created by mirko on 2017/4/25.
 */
public interface UserSalaryInformationSQLMapper {

    List<UserSalaryInformation> getUserSalaryInformation(UserSalaryInformationSQL userSalaryInformationSQL);
}
