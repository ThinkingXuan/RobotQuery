package com.njcit.controller;
import com.njcit.annotation.PrivilegeInfo;
import com.njcit.dao.MessageDao;
import com.njcit.model.JsonModel.BackMessage;
import com.njcit.model.JsonModel.HistoryMessage;
import com.njcit.model.Message;

import com.njcit.service.MessageService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mirko on 2017/3/29.
 */
@Controller
@RequestMapping(value = "/message", produces = "text/html;charset=UTF-8")
public class MessageController {

    @Autowired
    @Qualifier("messageService")
    MessageService messageService;
    @Autowired
    MessageDao messageDao;
    @RequestMapping(value = "/sendToRobot.action",method = RequestMethod.POST)
    @ResponseBody
    @PrivilegeInfo("save")
    public String sendToReboot(Message message) throws IOException {
        System.out.println("----------------------------------------------------------------");
        System.out.println("MessageController@sendToReboot:message参数:"+message.toString());
        String response =messageService.sendMessage(message);
        System.out.println("MessageController@sendToReboot:返回结果"+response);
        System.out.println("----------------------------------------------------------------");

        return response;

    }
    @RequestMapping(value = "/getHistoryMessages.action",method = RequestMethod.POST)
    @ResponseBody
    public String getHistoryMessage(String userId,int page,int size){
        System.out.println("----------------------------------------------------------------");
        System.out.println("MessageController@sendToReboot:请求参数");
        System.out.println("userId:"+userId);
        System.out.println("page:"+page);
        System.out.println("size:"+size);
            //System.out.println("总记录条数"+messageDao.getHistoryMessageSize(userUsername));
            String response = messageService.getHistoryMessage(userId,((page-1)*size),(size));
        System.out.println("MessageController@sendToReboot:返回结果"+response);
        System.out.println("----------------------------------------------------------------");
        return response;
    }

}
