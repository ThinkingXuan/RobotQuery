package com.njcit.service.impl;

import com.njcit.dao.SQL.user.UserSalaryInformationSQLMapper;
import com.njcit.model.JsonModel.user.UserSalaryInformationModel;
import com.njcit.model.concrete_search_by_short_sql.user.UserSalaryInformationSQL;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.model.user.UserSalaryInformation;
import com.njcit.service.UserSalaryInformationService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("userSalaryInformationService")
public class UserSalaryInformationServiceImpl implements UserSalaryInformationService {


    @Autowired
    UserSalaryInformationSQLMapper userSalaryInformationSQLMapper;

    @Override
    public String getUserSalaryInformation(TemplateModelBean templateModelBean) {

        UserSalaryInformationSQL userSalaryInformationSQL = new UserSalaryInformationSQL();

        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("user_id");
        String sql2 = (String) map.get("user_salary");
        String sql3 = (String) map.get("user_welfare");
        String sql4 = (String) map.get("user_given_time");
        List<UserSalaryInformation> list =  userSalaryInformationSQLMapper.getUserSalaryInformation(userSalaryInformationSQL);

        UserSalaryInformationModel userSalaryInformationModel = new UserSalaryInformationModel();

        userSalaryInformationModel.setCode("1");
        userSalaryInformationModel.setMessage("获取成功");
        userSalaryInformationModel.setObject(list);

        System.out.println(GsonUtil.ObjectToJson(userSalaryInformationModel,userSalaryInformationModel.getClass()));
        return GsonUtil.ObjectToJson(userSalaryInformationModel,userSalaryInformationModel.getClass());
    }
}
