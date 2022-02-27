package com.njcit.dao.SQL.hr;

import com.njcit.model.concrete_search_by_short_sql.hr.PostRecruitSQL;
import com.njcit.model.hr.PostRecruit;

import java.util.List;

/**
 * Created by mirko on 2017/4/24.
 */
public interface PostRecruitSQLMapper {

    List<PostRecruit> getPostRecruit(PostRecruitSQL postRecruitSQL);

}
