package com.njcit.dao.SQL.hr;

import com.njcit.model.concrete_search_by_short_sql.hr.UserAttendanceSQL;
import com.njcit.model.hr.UserAttendance;

import java.util.List;

/**
 * Created by mirko on 2017/4/25.
 */
public interface UserAttendanceSQLMapper {

    List<UserAttendance> getUserAttendance(UserAttendanceSQL userAttendanceSQL);

}
