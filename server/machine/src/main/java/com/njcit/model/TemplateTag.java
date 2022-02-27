package com.njcit.model;

/**
 * Created by mirko on 2017/4/27.
 */
public class TemplateTag {
    private String templateId;
    private String templateName;

    public String getTemplateId() {
        return templateId;
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    @Override
    public String toString() {
        return "TemplateTag{" +
                "templateId='" + templateId + '\'' +
                ", templateName='" + templateName + '\'' +
                '}';
    }


}
