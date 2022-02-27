package com.njcit.service.impl;

import com.njcit.dao.GetUserIdByUsernameMapper;
import com.njcit.dao.SQL.hr.UserPerformanceSQLMapper;
import com.njcit.model.JsonModel.hr.UserPerformanceModel;
import com.njcit.model.concrete_search_by_short_sql.hr.UserPerformanceSQL;
import com.njcit.model.hr.UserPerformance;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.UserPerformanceService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("userPerformanceService")
public class UserPerformanceServiceImpl implements UserPerformanceService {

    @Autowired
    UserPerformanceSQLMapper userPerformanceSQLMapper;

    @Autowired
    GetUserIdByUsernameMapper getUserIdByUsernameMapper ;



    @Override
    public String getUserPerformance(TemplateModelBean templateModelBean) {


        UserPerformanceSQL userPerformanceSQL = new UserPerformanceSQL();

        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("user_id");
        String sql2 = (String) map.get("user_attitude");
        String sql3 = (String)map.get("user_responsibility");
        String sql4 = (String)map.get("user_work_ability");
        String sql5 = (String)map.get("user_training_ability");
        String sql6 = (String)map.get("user_total");


        if (!(sql1==null||sql1.equals(""))){
            System.out.println(sql1);
            String userid = "user_id = "+"'"+getUserIdByUsernameMapper.getUseridByUsername(sql1)+"'";
            System.out.println(userid);
           userPerformanceSQL.setUserId(userid);
        }

        if (!(sql2==null||sql2.equals(""))){
            userPerformanceSQL.setUserAttitude(sql2);
        }

        if (!(sql3==null||sql3.equals(""))){
            userPerformanceSQL.setUserResponsibility(sql3);
        }

        if (!(sql4==null||sql4.equals(""))){
            userPerformanceSQL.setUserWorkAbility(sql4);
        }

        if (!(sql5==null||sql5.equals(""))){
            userPerformanceSQL.setUserTrainingAbility(sql5);
        }

        if (!(sql6==null||sql6.equals(""))){
            userPerformanceSQL.setUserTotal(sql6);
        }

        List<UserPerformance> list = userPerformanceSQLMapper.getUserPerformance(userPerformanceSQL);
        UserPerformanceModel userPerformanceModel = new UserPerformanceModel();
        userPerformanceModel.setCode("1");
        userPerformanceModel.setMessage("获取成功");
        userPerformanceModel.setObject(list);
        System.out.println(GsonUtil.ObjectToJson(userPerformanceModel,userPerformanceModel.getClass()));

        return GsonUtil.ObjectToJson(userPerformanceModel,userPerformanceModel.getClass());
    }
}
