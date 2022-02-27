package com.njcit.service.impl;

import com.njcit.dao.SQL.hr.UserTrainingSQLMapper;
import com.njcit.model.JsonModel.hr.UserTrainingModel;
import com.njcit.model.concrete_search_by_short_sql.hr.UserTrainingSQL;
import com.njcit.model.hr.UserTraining;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.UserTrainingService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("userTrainingService")
public class UserTrainingServiceImpl implements UserTrainingService {
    @Autowired
    UserTrainingSQLMapper userTrainingSQLMapper;
    @Override
    public String getUserTraining(TemplateModelBean templateModelBean) {
        UserTrainingSQL userTrainingSQL = new UserTrainingSQL();
        Map map = templateModelBean.getmMap();
        String sql1 = (String) map.get("train_id");
        String sql2 = (String) map.get("train_name");
        String sql3 = (String) map.get("train_target");
        String sql4 = (String) map.get("train_trainer");
        String sql5 = (String) map.get("train_date");

        if (!(sql1==null||sql1.equals(""))){
            userTrainingSQL.setTrainId(sql1);
        }
        if (!(sql2==null||sql2.equals(""))){
            userTrainingSQL.setTrainName(sql2);
        } if (!(sql3==null||sql3.equals(""))){
            userTrainingSQL.setTrainTarget(sql3);
        } if (!(sql4==null||sql4.equals(""))){
            userTrainingSQL.setTrainTrainer(sql4);
        }
        if (!(sql5==null||sql5.equals(""))){
            userTrainingSQL.setTrainDate(sql5);
        }
       List<UserTraining> list = userTrainingSQLMapper.getUserTraining(userTrainingSQL);
        UserTrainingModel userTrainingModel = new UserTrainingModel();
        userTrainingModel.setCode("1");
        userTrainingModel.setMessage("获取成功");
        userTrainingModel.setObject(list);


        System.out.println(GsonUtil.ObjectToJson(userTrainingModel,userTrainingModel.getClass()));


        return GsonUtil.ObjectToJson(userTrainingModel,userTrainingModel.getClass());
    }
}
