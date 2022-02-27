package com.njcit.robotquery.http.api;

import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.server.ChatMessage;
import com.njcit.robotquery.bean.server.LoginBean;
import com.njcit.robotquery.bean.server.TemplateGroup;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by youxuan on 2017/3/25 0025.
 * RetrofitClient 处理消息的发送和接收
 */

public interface chatApi {


    /**
     * 注册
     * @param userPassword
     * @param userEmail
     * @param userEmail
     * @param userRole
     * @return0
     */
    @FormUrlEncoded
    @POST("/user/userRegister.action")
    Observable<BaseBean> register_user(@Field("userUsername") String userUsername,
                                       @Field("userEmail") String userEmail,
                                       @Field("userPassword") String userPassword,
                                       @Field("userRole")String userRole);


    /**
     * 登录
     * @param userEmail
     * @param userPassword
     * @return
     */
    @FormUrlEncoded
    @POST("/user/userLogin.action")
    Observable<LoginBean> login_user(@Field("userEmail") String userEmail,
                                     @Field("userPassword") String userPassword);

    /**
     * 发送和获取聊天消息 POST
     * @param content 消息内容
     * @param userId
     * @return  Observable类型
     */
    @FormUrlEncoded
    @POST("/message/sendToRobot.action")
    Observable<ChatMessage> getChatMessage(@Field("userId") String userId,
                                           @Field("content") String content);

    /**
     * 加载聊天历史 GET
     * @param page 页数
     * @param per_page  每页数量
     * @return Observable
     */
    @FormUrlEncoded
    @POST("/message/getHistoryMessages.action")
    Observable<ChatMessage> getChatHistory(@Field("userId")String userId,
                                           @Field("page") int page,
                                           @Field("size") int per_page);

    /**
     * 获取用户模板
     * @param userId
     * @return
     */
    Observable<TemplateGroup> getUserModel(@Field("userId")String userId);

}
