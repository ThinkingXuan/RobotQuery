package com.njcit.robotquery.util;

import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;

/**
 * Created by youxuan on 2017/4/12 0012.
 * 封装发送消息的工具类
 *
 */

public class SendMessageUtil {

    /**
     *
     * @param type  0用户 1机器人   2多视图   3图片
     * @param content   附内容
     * @param mainContext  主内容
     * @return
     */
    public static MessagesListFixtures.Message getMessage(int type,String content,String mainContext){

        if (mainContext==null){
            mainContext = "";
        }
        if (content==null){
            content = "";
        }

        MessagesListFixtures.Message message = new MessagesListFixtures.Message();
        message.setIsRobot(type);
        message.setText(content);
        message.setMainText(mainContext);

        return message;

    }


    public static MessagesListFixtures.Message getMessage(int type,String content,Object obj,String objType){


        if (content==null){
            content = "";
        }

        MessagesListFixtures.Message message = new MessagesListFixtures.Message();
        message.setIsRobot(type);
        message.setText(content);
        message.setObj(obj);
        message.setObjType(objType);

        return message;

    }

    public static MessagesListFixtures.Message getMessage(int type,String content,String mainContext,Object obj,String objType){

        if (mainContext==null){
            mainContext = "";
        }
        if (content==null){
            content = "";
        }

        MessagesListFixtures.Message message = new MessagesListFixtures.Message();
        message.setIsRobot(type);
        message.setText(content);
        message.setMainText(mainContext);
        message.setObj(obj);
        message.setObjType(objType);

        return message;

    }
}
