package com.njcit.reboot.tulin.bean.question;
import com.njcit.status.Constant;
/*
        图灵发送接口json数据格式
 */
public class BaseBean {
    //APIKEY
    private String key = Constant.APIKEY;
    //用户问题
    private String info;
    //用户标识符 每一个用户分配一个
    private String userid;
//    private String loc;

//    public String getLoc() {
//        return loc;
//    }
//
//    public void setLoc(String loc) {
//        this.loc = loc;
//    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public BaseBean(String info, String userid) {
        this.info = info;
        this.userid = userid;
    }

    public BaseBean() {
    }
}
