package com.njcit.robotquery.moudle.chat.robotcontrol;

import android.view.MotionEvent;
import android.view.View;

import com.njcit.robotquery.constant.Constants;

/**
 * Created by youxuan on 2017/5/18 0018.
 * 用户发送消息的处理引擎
 */

public class RobotControl {


    public Object MesageDeal(String content, String type) {

        if (content != null && !content.equals("") && type != null && !type.equals("")) {
            return MessageDispatch(content,type);
        }
        return null;
    }


    /**
     * 消息分发
     *
     * @param type 消息类型:
     *             普通消息 NormalMessage
     *             模板消息 TemplateMessage
     *             上下文消息 ContextMessage
     * @return
     */

    public String MessageDispatch(String content,String type) {

        switch (type) {
            case Constants.MESSAGE_NORMAL:


                break;
            case Constants.MESSAGE_TEMPLATE:

                break;
            case Constants.MESSAGE_CONTEXT:
                break;

        }
        return null;
    }

    public Object NormalMessageDeal(String content){
        return null;
    }
    public Object TemplateMessageDeal(String content){
        return null;
    }
    public Object ContextMessageDeal(String content){
        return null;
    }



}
