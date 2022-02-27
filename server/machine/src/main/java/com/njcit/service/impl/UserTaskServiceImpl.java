package com.njcit.service.impl;

import com.njcit.dao.SQL.hr.UserTrainingSQLMapper;
import com.njcit.dao.SQL.user.UserTaskSQLMapper;
import com.njcit.model.JsonModel.user.UserTaskModel;
import com.njcit.model.concrete_search_by_short_sql.user.UserTaskSQL;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.model.user.UserTask;
import com.njcit.service.UserTaskService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("userTaskService")
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    UserTaskSQLMapper userTaskSQLMapper;

    @Override
    public String getUserTask(TemplateModelBean templateModelBean) {

        UserTaskSQL userTaskSQL = new UserTaskSQL();
        Map map = templateModelBean.getmMap();
        String  sql1 = (String) map.get("user_id");
        String sql2 = (String) map.get("task_id");
        String sql3 = (String) map.get("task_content");
        String sql4 = (String) map.get("task_expiry_date");
        if (!(sql1==null||sql1.equals(""))){
           userTaskSQL.setUserId(sql1);
        }
        if (!(sql2==null||sql2.equals(""))){
            userTaskSQL.setTaskId(sql2);
        }
        if (!(sql3==null||sql3.equals(""))){
            userTaskSQL.setTaskContent(sql3);
        }
        if (!(sql4==null||sql4.equals(""))){
            userTaskSQL.setTaskContent(sql4);
        }
       List<UserTask> list = userTaskSQLMapper.getUserTask(userTaskSQL);
        UserTaskModel userTaskModel = new UserTaskModel();
        userTaskModel.setCode("1");
        userTaskModel.setMessage("获取成功");
        userTaskModel.setObject(list);


        GsonUtil.ObjectToJson(userTaskModel,userTaskModel.getClass());

        return  GsonUtil.ObjectToJson(userTaskModel,userTaskModel.getClass());
    }
}
