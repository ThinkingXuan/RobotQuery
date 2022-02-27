package com.njcit.robotquery.bean.server;

import java.util.List;

/**
 * Created by youxuan on 2017/4/27 0027.
 * 模板的Tag标签
 */

public class TemplateTagBean {
    private String message;
    private String code;

    private List<TagData> object;

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

    public List<TagData> getObject() {
        return object;
    }

    public void setObject(List<TagData> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "TemplateTagBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }

    public static class TagData{
        private String templateId;
        private String templateName;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getTagName() {
            return templateName;
        }

        public void setTagName(String templateName) {
            this.templateName = templateName;
        }

        @Override
        public String toString() {
            return "TagData{" +
                    "templateId='" + templateId + '\'' +
                    ", tagName='" + templateName + '\'' +
                    '}';
        }
    }
}
