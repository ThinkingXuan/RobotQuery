package com.njcit.service.impl;

import com.njcit.dao.SQL.hr.UserAttendanceSQLMapper;
import com.njcit.model.JsonModel.hr.UserAttendanceModel;
import com.njcit.model.concrete_search_by_short_sql.hr.UserAttendanceSQL;
import com.njcit.model.hr.UserAttendance;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.UserAttendanceService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("userAttendanceService")
public class UserAttendanceServiceImpl implements UserAttendanceService {

    @Autowired
    UserAttendanceSQLMapper userAttendanceSQLMapper;

    @Override
    public String getUserAttendanceService(TemplateModelBean templateModelBean) {

        UserAttendanceSQL userAttendanceSQL = new UserAttendanceSQL();

        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("attendance_date");
        String sql2 = (String)map.get("attendance_all");
        String sql3 = (String)map.get("attendance_real");

        if (!(sql1==null||sql1.equals(""))){
           userAttendanceSQL.setAttendanceDate(sql1);
        }
        if (!(sql2==null||sql2.equals(""))){
           userAttendanceSQL.setAttendanceAll(sql2);
        }
        if (!(sql3==null||sql3.equals(""))){
           userAttendanceSQL.setAttendanceReal(sql3);
        }
            List<UserAttendance> list = userAttendanceSQLMapper.getUserAttendance(userAttendanceSQL);

            UserAttendanceModel userAttendanceModel = new UserAttendanceModel();
            userAttendanceModel.setMessage("获取成功");
            userAttendanceModel.setCode("1");
            userAttendanceModel.setObject(list);
        return GsonUtil.ObjectToJson(userAttendanceModel,userAttendanceModel.getClass());
    }
}
