package com.njcit.dao.user;
import com.njcit.model.User;
/**
 * Created by mirko on 2017/3/14.
 */
/*
     对用户的存储等一系列操作
 */

public interface UserDao {

        //根据用户名获取用户
        User getUserByUsername(String username);
        //根据邮箱获取用户
        User getUserByUserEmail(String userEmail);
        //用户注册
        void saveUser(User user);
       //用户登录
        User userLogin(User user);

        String getUserIdByUsername(String username);

}
