package com.njcit.status;

/**
 * Created by mirko on 2017/3/17.
 */
public class ResultStatus {
    //status
    //成功
    public final static String SUCCESS ="success";
    //失败
    public final static String FAILURE ="failure";

    /*
        用户注册
     */
    //用户名重复
    public final static String EXIST_USERNAME = "用户名重复";
    //邮箱重复
    public final static String EXIST_Email = "邮箱已存在";

    /*
        用户登录
     */

    //用户名错误
    public final static String USERNAME_EORROR = "用户名或邮箱错误";
    //用户密码错误
    public final static String PASSWORD_ERROR  =  "密码错误";

    /*
        用户操作
     */
    public final static String NO_ACCESS ="抱歉，您没有权限";

}
