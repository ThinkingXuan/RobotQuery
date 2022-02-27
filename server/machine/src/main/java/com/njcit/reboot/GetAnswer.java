package com.njcit.reboot;

import com.njcit.reboot.base.IBaseDeal;
import com.njcit.reboot.tulin.TULINAnswer;
import com.njcit.reboot.xiaomi.XIAOMIAnswer;
import com.njcit.status.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mirko on 2017/3/31.
 */
public class GetAnswer implements IBaseDeal{


    @Override
    public Map<String,String> getAnswer(String userUsername, String question) throws IOException {
        Map<String,String> map = new HashMap<String, String>();
        String response;
        System.out.println("正在请求小米");
        response = XIAOMIAnswer.getAnswer(question);
        if("#".equals(response)&& response == null){
            System.out.println("小米无答案 正在请求图灵");
            response = TULINAnswer.getAnswer(question,userUsername);
            map.put(Constant.JSON,response);
            return map;
        }else {

            map.put(Constant.TEXT,response);
            return map;
        }

    }

//    public static void main(String[] args) throws IOException {
//        String response = TULINAnswer.getAnswer("共产党","1");
//        System.out.println(response);
//    }
}
