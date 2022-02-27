package com.njcit.dao.SQL.hr;

import com.njcit.model.concrete_search_by_short_sql.hr.UserTrainingSQL;
import com.njcit.model.hr.UserTraining;

import java.util.List;

/**
 * Created by mirko on 2017/4/25.
 */
public interface UserTrainingSQLMapper {

    List<UserTraining> getUserTraining(UserTrainingSQL userTrainingSQL);
}
