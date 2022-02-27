package com.njcit.robotquery.bean.server;

import com.njcit.robotquery.bean.local.TemplateBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/5 0005.
 * 模板组：group,
 * 一个创建人，多个模板
 */

public class TemplateGroup implements Serializable{

    private String useId;           //这个模板的归属人id
    private String username;
    private String time;            //创立的时间
    private List<TemplateBean> mTemplateBeen;

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<TemplateBean> getTemplateBeen() {
        return mTemplateBeen;
    }

    public void setTemplateBeen(List<TemplateBean> templateBeen) {
        mTemplateBeen = templateBeen;
    }


}
