package com.njcit.service.impl;
import com.njcit.dao.MessageDao;
import com.njcit.model.JsonModel.BackMessage;
import com.njcit.model.JsonModel.HistoryMessage;
import com.njcit.model.Message;
import com.njcit.reboot.GetAnswer;
import com.njcit.reboot.xiaomi.XIAOMIAnswer;
import com.njcit.service.MessageService;
import com.njcit.status.Constant;
import com.njcit.utils.GsonUtil;
import com.youText.AckToAIML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mirko on 2017/3/29.
 */
@Service("messageService")
public class MessageServcieImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    //存储聊天记录并且返回机器人的答案
    //message需要userUsername和content

    public String sendMessage(Message message) throws IOException {

        //存储用户问题
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setTime(format.format(new Date()));
        message.setIsRobot("0");
        message.setType(Constant.TEXT);
        messageDao.saveMessage(message);

        Message message1 = new Message();
        GetAnswer getAnswer = new GetAnswer();
        Map<String,String> map=getAnswer.getAnswer(message.getUserId(),message.getContent());
        BackMessage backMessage = new BackMessage();
        List<BackMessage.ObjectBean> objectBean = new ArrayList<BackMessage.ObjectBean>();
        BackMessage.ObjectBean objectBean1 = new BackMessage.ObjectBean();
        if(map.containsKey(Constant.TEXT)){
            message1.setType(Constant.TEXT);
            message1.setContent(map.get(Constant.TEXT));
            message1.setTime(format.format(new Date()));
            message1.setIsRobot("1");
            message1.setUserId(message.getUserId());
            messageDao.saveMessage(message1);
            backMessage.setCode("1");
            backMessage.setMessage("操作成功");
            objectBean1.setIsRobot("1");
            objectBean1.setTime(format.format(new Date()));
            objectBean1.setType(Constant.TEXT);
            objectBean1.setContent(map.get(Constant.TEXT));
            objectBean.add(objectBean1);
            backMessage.setObject(objectBean);
            return GsonUtil.ObjectToJson(backMessage,backMessage.getClass());
        }else {
            message1.setType(Constant.JSON);
            message1.setContent(map.get(Constant.JSON));
            message1.setTime(format.format(new Date()));
            message1.setIsRobot("1");
            message1.setUserId(message.getUserId());
            messageDao.saveMessage(message1);
            backMessage.setCode("1");
            backMessage.setMessage("操作成功");
            objectBean1.setIsRobot("1");
            objectBean1.setTime(format.format(new Date()));
            objectBean1.setType(Constant.JSON);
            objectBean1.setContent(map.get(Constant.JSON));
            objectBean.add(objectBean1);
            backMessage.setObject(objectBean);
            return GsonUtil.ObjectToJson(backMessage,backMessage.getClass());
        }


    }

    public String getHistoryMessage(String userId,int offset,int size) {

        List<Message> list = messageDao.getHistoryMessage(userId,offset,size);
        HistoryMessage historyMessage = new HistoryMessage();
        List<HistoryMessage.ObjectBean> objectBeans = new ArrayList<HistoryMessage.ObjectBean>();
        Iterator<Message> iterator =list.iterator();
        while (iterator.hasNext()){
            Message message = iterator.next();
            HistoryMessage.ObjectBean objectBean = new HistoryMessage.ObjectBean();
            objectBean.setIsRobot(message.getIsRobot());
            objectBean.setContent(message.getContent());
            objectBean.setTime(message.getTime().substring(0,message.getTime().length()-2));
            objectBean.setType(message.getType());
            objectBeans.add(objectBean);
        }
        historyMessage.setCode("1");
        historyMessage.setMessage("获取成功");
        historyMessage.setObject(objectBeans);
        return  GsonUtil.ObjectToJson(historyMessage,historyMessage.getClass());
    }
}
