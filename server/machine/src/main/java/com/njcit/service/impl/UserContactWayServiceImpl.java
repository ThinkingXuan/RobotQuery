package com.njcit.service.impl;

import com.njcit.dao.user.UserContactWayMapper;
import com.njcit.dao.user.UserDao;
import com.njcit.model.JsonModel.chartbean.user.UserContactWayModel;
import com.njcit.model.user.UserContactWay;
import com.njcit.service.UserContactWayService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
@Service("userContactWayService")
public class UserContactWayServiceImpl implements UserContactWayService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserContactWayMapper userContactWayMapper;

    @Override
    public String getUserContactWayByUsername(String username){






        String ID = userDao.getUserIdByUsername(username);
        UserContactWay userContactWay = userContactWayMapper.selectByPrimaryKey(ID);
        if(userContactWay==null){
            UserContactWayModel userContactWayModel = new UserContactWayModel();
            userContactWayModel.setMessage("不存在该用户,请重新输入或者回复'1'退出查询");
            userContactWayModel.setCode("0");




            String response  =  GsonUtil.ObjectToJson(userContactWayModel,userContactWayModel.getClass());
            return  response;

        }else {
            UserContactWayModel userContactWayModel = new UserContactWayModel();
            UserContactWayModel.ObjectBean objectBean = new UserContactWayModel.ObjectBean();
            objectBean.setUserId(userContactWay.getUserId());
            objectBean.setUserEmail(userContactWay.getUserEmail());
            objectBean.setUserPhone(userContactWay.getUserPhone());
            objectBean.setUserQQ(userContactWay.getUserQq());
            objectBean.setUserWechat(userContactWay.getUserWechat());
            objectBean.setUserTelephone(userContactWay.getUserTelephone());
            List<UserContactWayModel.ObjectBean> list = new ArrayList<UserContactWayModel.ObjectBean>();
            list.add(objectBean);
            userContactWayModel.setMessage("获取成功");
            userContactWayModel.setCode("1");
            userContactWayModel.setObject(list);
            String response = GsonUtil.ObjectToJson(userContactWayModel, userContactWayModel.getClass());



            return response;
        }
    }
}
