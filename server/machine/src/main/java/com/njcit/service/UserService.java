package com.njcit.service;

import com.njcit.model.User;

/**
 * Created by mirko on 2017/3/14.
 */
/*
    用户一系列操作业务层代码
*/
public interface UserService {

    //保存用户
    String saveUser(User user);
    //用户登录
    String userLogin(User user);


}
