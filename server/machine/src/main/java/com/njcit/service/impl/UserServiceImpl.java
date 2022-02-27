package com.njcit.service.impl;

import com.njcit.dao.user.UserDao;
import com.njcit.model.User;
import com.njcit.service.UserService;
import com.njcit.status.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mirko on 2017/3/14.
 */
/*
用户操作业务层实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao ;

    //保存用户
    @Transactional
    public String saveUser(User user) {
        System.out.println("正在检测用户:"+user.getUserUsername()+"是否存在");
        System.out.println("正在检测用户:"+user.getUserUsername()+"邮箱:"+user.getUserEmail()+"是否存在");
        User user1 =userDao.getUserByUsername(user.getUserUsername());
        User user2 = userDao.getUserByUserEmail(user.getUserEmail());
        if(user2!=null){
            System.out.println("用户邮箱:"+user.getUserEmail()+"已存在");
            return ResultStatus.EXIST_Email;

        }else if(user1!=null){
            System.out.println("用户名:"+user.getUserUsername()+"已存在");
            return ResultStatus.EXIST_USERNAME;
        }else{
            System.out.println("UserDao:"+"执行saveUser");
            userDao.saveUser(user);
            System.out.println("用户"+user.getUserUsername()+"注册成功");
            return ResultStatus.SUCCESS;
        }
    }

    public String userLogin(User user) {
            System.out.println("UserService:"+"执行UserLogin");
            User user1 = userDao.userLogin(user);
            User user2 = userDao.getUserByUserEmail(user.getUserEmail());
        if(user2==null){
            System.out.println("不存在该用户");
            return ResultStatus.USERNAME_EORROR;
        }else if(user1==null){
            System.out.println("密码错误");
            return ResultStatus.PASSWORD_ERROR;
        }else {
            System.out.println("验证成功");
            return ResultStatus.SUCCESS;
        }
    }

}
