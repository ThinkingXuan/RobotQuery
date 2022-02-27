package com.njcit.controller;
import com.njcit.annotation.PrivilegeInfo;
import com.njcit.dao.privilegeInformation.GetRoleByUserIdMapper;
import com.njcit.dao.user.UserDao;
import com.njcit.model.JsonModel.CommonJsonModel;
import com.njcit.model.JsonModel.chartbean.user.LoginBackUserId;
import com.njcit.model.User;
import com.njcit.service.UserService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {
    @Autowired
    @Qualifier("userService")
    UserService userService;
    @Autowired
    GetRoleByUserIdMapper getRoleByUserIdMapper;
    @Autowired
    UserDao userDao;

    @PrivilegeInfo("save")
    @RequestMapping(value = "/userRegister.action", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(User user) {
        System.out.println("----------------------------------------------------------------");
            System.out.println("参数信息:"+user.toString());
                String status =userService.saveUser(user);
            if("success".equals(status)){
                CommonJsonModel commonJsonModel = new CommonJsonModel();
                commonJsonModel.setCode("1");
                commonJsonModel.setMessage("注册成功");
                commonJsonModel.setObject(null);
                System.out.println("UserController@saveUser:返回参数:"+GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass()));
                System.out.println("----------------------------------------------------------------");
                return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
            }else {
                    CommonJsonModel commonJsonModel = new CommonJsonModel();
                    commonJsonModel.setCode("0");
                    commonJsonModel.setMessage(status);
                    commonJsonModel.setObject(null);
                System.out.println("UserController@saveUser:返回参数:"+GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass()));
                System.out.println("----------------------------------------------------------------");
                return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
            }
    }
    @PrivilegeInfo("login")
    @RequestMapping(value = "/userLogin.action", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(User user) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("UserController@userLogin:user参数:"+user.toString());
        String status = userService.userLogin(user);
            if("success".equals(status)){
                LoginBackUserId loginBackUserId = new LoginBackUserId();
                List<LoginBackUserId.ObjectBean> list = new ArrayList<LoginBackUserId.ObjectBean>();
                loginBackUserId.setCode("1");
                loginBackUserId.setMessage(status);

                LoginBackUserId.ObjectBean objectBean = new LoginBackUserId.ObjectBean();
                objectBean.setUserId(userDao.getUserByUserEmail(user.getUserEmail()).getUserId());

                System.out.println(userDao.getUserByUserEmail(user.getUserEmail()).getRoleId());



                objectBean.setRoleId(getRoleByUserIdMapper.getRoleNameByRoleId(userDao.getUserByUserEmail(user.getUserEmail()).getRoleId()));
                objectBean.setUserUsername(userDao.getUserByUserEmail(user.getUserEmail()).getUserUsername());
                list.add(objectBean);
                loginBackUserId.setObject(list);

                System.out.println("UserController@userLogin:返回参数:"+GsonUtil.ObjectToJson(loginBackUserId,loginBackUserId.getClass()));
                System.out.println("----------------------------------------------------------------");
                return GsonUtil.ObjectToJson(loginBackUserId,loginBackUserId.getClass());
            }
            else {
                LoginBackUserId loginBackUserId = new LoginBackUserId();
                loginBackUserId.setCode("0");
                loginBackUserId.setMessage(status);
                loginBackUserId.setObject(null);

                System.out.println("UserController@userLogin:返回参数:"+GsonUtil.ObjectToJson(loginBackUserId,loginBackUserId.getClass()));
                System.out.println("----------------------------------------------------------------");
                return GsonUtil.ObjectToJson(loginBackUserId,loginBackUserId.getClass());
            }

    }
}

