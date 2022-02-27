package com.njcit.dao.SQL.hr;
import com.njcit.model.concrete_search_by_short_sql.hr.UserPerformanceSQL;
import com.njcit.model.hr.UserPerformance;

import java.util.List;

/**
 * Created by mirko on 2017/4/25.
 */
public interface UserPerformanceSQLMapper {


    List<UserPerformance> getUserPerformance(UserPerformanceSQL userPerformanceSQL);


}
