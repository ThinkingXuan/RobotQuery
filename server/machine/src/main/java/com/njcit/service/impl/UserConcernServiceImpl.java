package com.njcit.service.impl;

import com.njcit.dao.user.UserConcernMapper;
import com.njcit.model.JsonModel.CommonJsonModel;
import com.njcit.service.UserConcernService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mirko on 2017/4/27.
 */
@Service("userConcernService")
public class UserConcernServiceImpl implements UserConcernService {
    @Autowired
    UserConcernMapper userConcernMapper;


    @Override
    public String userConcern(String userId, String templateId) {

        userConcernMapper.insertNewConcern(userId,templateId);
        CommonJsonModel commonJsonModel = new CommonJsonModel();
        commonJsonModel.setCode("1");
        commonJsonModel.setMessage("关注成功");
        return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
    }

    @Override
    public String cancelConcern(String userId, String templateId) {

        userConcernMapper.CancelConcern(userId,templateId);
        CommonJsonModel commonJsonModel = new CommonJsonModel();
        commonJsonModel.setCode("1");
        commonJsonModel.setMessage("取消成功");
        return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
    }
}
