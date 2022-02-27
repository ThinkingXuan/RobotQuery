package com.njcit.model.JsonModel.chartbean.user;

import com.njcit.model.user.TemplateModelBean;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class TemplateModelModel {
    private String message;

    private String code;

    private List<TemplateModelModel.ObjectBean> object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TemplateModelModel.ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<TemplateModelModel.ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {

    private TemplateModelBean templateModelBean;

        public TemplateModelBean getTemplateModelBean() {
            return templateModelBean;
        }

        public void setTemplateModelBean(TemplateModelBean templateModelBean) {
            this.templateModelBean = templateModelBean;
        }

        @Override
        public String toString() {
            return "ObjectBean{" +
                    "templateModelBean=" + templateModelBean +
                    '}';
        }
    }
}
