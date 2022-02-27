package com.njcit.robotquery.bean.server;

import java.util.Map;

/**
 * Created by youxuan on 2017/4/5 0005.
 * 用户模板单个
 */

public class Model {

    private String content;
    private String time;
    private String role;             //模板权限
    private String type;            //数据展示内容

    private Map<String, String> mMap; // 查询条件

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getMap() {
        return mMap;
    }

    public void setMap(Map<String, String> map) {
        mMap = map;
    }

}


