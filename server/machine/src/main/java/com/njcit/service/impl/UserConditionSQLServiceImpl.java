package com.njcit.service.impl;

import com.njcit.dao.SQL.user.UserConditionSQLMapper;
import com.njcit.model.JsonModel.chartbean.user.UserConditionModel;
import com.njcit.model.concrete_search_by_short_sql.UserConditionSQL;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.model.user.UserCondition;
import com.njcit.service.UserConditionSQLService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/19.
 */
@Service("userConditionSQLService")
public class UserConditionSQLServiceImpl implements UserConditionSQLService {

    @Autowired
    private UserConditionSQLMapper userConditionSQLMapper;

    @Override
    public String getUserConditioin(TemplateModelBean templateModelBean) {


        UserConditionSQL userConditionSQL = new UserConditionSQL();
        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("user_id");
        String sql2 = (String) map.get("user_username");
        String sql3 = (String) map.get("user_position");
        String sql4 = (String) map.get("user_birthday");
        String sql5 = (String) map.get("user_salary");
        String sql6 = (String) map.get("user_work_date");
        String sql7 = (String) map.get("user_school");
        String sql8 = (String) map.get("user_department");
        String sql9 = (String) map.get("user_gender");
        String sql10 = (String) map.get("user_hometown");
        String sql11 = (String) map.get("user_education");
        String sql12 = (String) map.get("user_age");

        if (!(sql1==null||sql1.equals(""))){
            userConditionSQL.setUserId(sql1);
        }
        if (!(sql2==null||sql2.equals(""))){
            userConditionSQL.setUserUsername(sql2);
        }
        if (!(sql3==null||sql3.equals(""))){
            userConditionSQL.setUserPosition(sql3);
        }
        if (!(sql4==null||sql4.equals(""))){
            userConditionSQL.setUserBirthday(sql4);
        }
        if (!(sql5==null||sql5.equals(""))){
            userConditionSQL.setUserSalary(sql5);
        }
        if (!(sql6==null||sql6.equals(""))){
            userConditionSQL.setUserWorkDate(sql6);
        }
        if (!(sql7==null||sql7.equals(""))){
            userConditionSQL.setUserSchool(sql7);
        }
        if (!(sql8==null||sql8.equals(""))){
            userConditionSQL.setUserDepartment(sql8);
        }
        if (!(sql9==null||sql9.equals(""))) {
            userConditionSQL.setUserGender(sql9);
        }
        if (!(sql10==null||sql10.equals(""))){
            userConditionSQL.setUserHometown(sql10);
        }
        if (!(sql11==null||sql11.equals(""))){
            userConditionSQL.setUserEducation(sql11);
        }
        if (!(sql12==null||sql12.equals(""))){
            userConditionSQL.setUserAge(sql12);
        }

        System.out.println(userConditionSQL.toString());
            List<UserCondition> list =userConditionSQLMapper.getUserConditioin(userConditionSQL);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(list.get(0).getUserBirthday()));


        UserConditionModel userConditionModel = new UserConditionModel();
            userConditionModel.setCode("1");
            userConditionModel.setMessage("查询成功");
            userConditionModel.setObject(list);
        return  GsonUtil.ObjectToJson(userConditionModel,userConditionModel.getClass());
    }
}
