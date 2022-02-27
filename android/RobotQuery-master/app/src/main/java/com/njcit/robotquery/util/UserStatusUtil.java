package com.njcit.robotquery.util;

import android.content.Context;

import com.njcit.robotquery.bean.local.User;
import com.njcit.robotquery.constant.Constants;

/**
 * Created by youxuan on 2017/3/30 0030.
 * 操作用户的登录状态
 */

public class UserStatusUtil {

    //注册时保存用户数据
    public static void saveUserInfo(Context mContext, User user){

        if (null != user){
            SharedPreUtil.setString(mContext, Constants.USERID,user.getUserId());
            SharedPreUtil.setString(mContext, Constants.EMAIL,user.getEmail());
            SharedPreUtil.setString(mContext, Constants.USERNAME,user.getUsername());
            SharedPreUtil.setString(mContext, Constants.PASSWORD,user.getPassword());
            SharedPreUtil.setString(mContext, Constants.RULE,user.getRule());
        }else {
            throw new RuntimeException("用户信息为空");
        }
    }

    //保存指定的用户数据
    public static void saveUserInfo(Context mContext,String key,String value){

        if (null != key || null !=value){
            SharedPreUtil.setString(mContext, key,value);
        }else {
            throw new RuntimeException("指定的用户key或者value为空");

        }
    }
    //移除所有保存的数据
    public static void deleteUserInfo(Context mContext,User user){
        if (null != user){
            SharedPreUtil.removeString(mContext,Constants.EMAIL);
            SharedPreUtil.removeString(mContext,Constants.USERNAME);
            SharedPreUtil.removeString(mContext,Constants.PASSWORD);
            SharedPreUtil.removeString(mContext,Constants.RULE);

        }else {
            throw new RuntimeException("用户信息为空");
        }
    }

    //移除指定的用户数据

    public static void deleteUserInfo(Context mContext,String key){
        if (null != key){
            SharedPreUtil.removeString(mContext,key);
        }else {
            throw new RuntimeException("指定的用户key为空");
        }
    }

    //设置用户登录状态为已登录
    public static void setLoginStatus_Yes(Context mContext){
        SharedPreUtil.setString(mContext,Constants.LOGINSTATE,"1");
    }

    //设置用户登录状态为未登录
    public static void setLoginStatus_No(Context mContext){
        SharedPreUtil.setString(mContext,Constants.LOGINSTATE,"0");
    }

    //获取指定的所有的用户信息
    public static User getUserInfo(Context mContext){
        User user = new User();
        user.setEmail(SharedPreUtil.getString(mContext,Constants.EMAIL,""));
        user.setPassword(SharedPreUtil.getString(mContext,Constants.PASSWORD,""));
        user.setUsername(SharedPreUtil.getString(mContext,Constants.USERNAME,""));
        user.setRule(SharedPreUtil.getString(mContext,Constants.RULE,""));

        return user;
    }

    //获取指定的用用户信息
    public static String getUserInfo(Context mContext,String key){

        if (null !=key){
            return SharedPreUtil.getString(mContext,key,"");
        }else {
            throw new RuntimeException("指定的用户key为空");
        }
    }

}
