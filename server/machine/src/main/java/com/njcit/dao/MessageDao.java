package com.njcit.dao;

import com.njcit.model.Message;

import java.util.List;

/**
 * Created by mirko on 2017/3/29.
 */
public interface MessageDao {


    //保存用户和服务器发送的消息
    void saveMessage(Message message);

    //用户请求查看历史消息
    List<Message> getHistoryMessage(String userUsername,int offset,int size);

    int getHistoryMessageSize(String userUsername);
}
