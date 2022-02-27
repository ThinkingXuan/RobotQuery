package com.njcit.reboot.base;
import java.io.IOException;
import java.util.Map;
/**
 * Created by mirko on 2017/3/22.
 */
/*
        机器人总结口
 */
public interface IBaseDeal extends IBase {
        //输入用户标志 和 问题 返回答案类型和答案
        Map<String,String>  getAnswer(String userUsername, String question) throws IOException;

}
