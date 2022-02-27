package com.njcit.service.impl;

import com.njcit.dao.SQL.hr.PostRecruitSQLMapper;
import com.njcit.model.JsonModel.hr.PostRecruitModel;
import com.njcit.model.concrete_search_by_short_sql.hr.PostRecruitSQL;
import com.njcit.model.hr.PostRecruit;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.PostRecruitSQLService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/4/25.
 */
@Service("postRecruitSQLService")
public class PostRecruitSQLServiceImpl implements PostRecruitSQLService {

    @Autowired
    PostRecruitSQLMapper postRecruitSQLMapper;

    @Override
    public String getPostRecruit(TemplateModelBean templateModelBean) {
        PostRecruitSQL postRecruitSQL = new PostRecruitSQL();
        Map map = templateModelBean.getmMap();
        String sql1 =  (String) map.get("post_id");
        String sql2 = (String)map.get("post_name");
        String sql3 = (String) map.get("post_number");
        String  sql4 = (String) map.get("post_real_number");

        if (!(sql1==null||sql1.equals(""))){
            postRecruitSQL.setPostId(sql1);
        }

        if (!(sql2==null||sql2.equals(""))){
            postRecruitSQL.setPostName(sql2);
        }
        if (!(sql3==null||sql3.equals(""))){
            postRecruitSQL.setPostNumber(sql3);
        }
        if (!(sql4==null||sql4.equals(""))){
            postRecruitSQL.setPostRealNumber(sql4);
        }
        System.out.println(postRecruitSQL.toString());

        List<PostRecruit> list = postRecruitSQLMapper.getPostRecruit(postRecruitSQL);
        System.out.println(list.size());
        PostRecruitModel postRecruitModel = new PostRecruitModel();
        postRecruitModel.setCode("1");
         postRecruitModel.setObject(list);

        return GsonUtil.ObjectToJson(postRecruitModel,postRecruitModel.getClass());
    }
}
