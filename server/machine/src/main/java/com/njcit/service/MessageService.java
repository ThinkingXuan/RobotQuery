package com.njcit.service;

import com.njcit.model.Message;

import java.io.IOException;
import java.util.List;

/**
 * Created by mirko on 2017/3/29.
 */
public interface MessageService {

    //用户请求发送消息
    String sendMessage(Message message) throws IOException;



    //用户请求查看历史消息
    String getHistoryMessage(String userUsername,int offset,int size);
}
