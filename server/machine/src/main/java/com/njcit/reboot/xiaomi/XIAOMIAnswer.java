package com.njcit.reboot.xiaomi;

import com.youText.AckToAIML;

/**
 * Created by mirko on 2017/3/31.
 */
public class XIAOMIAnswer {

    public static String getAnswer(String question){

        //获取AIML机器人
        AckToAIML bot = new AckToAIML();
        //发出问题,获得结果
        String response = bot.response(question);
        //取消单字之间的空格
        response = response.replaceAll(" ","");
        //返回结果
        return response;
    }

}
