package com.njcit.service;

import com.njcit.model.user.TemplateModelBean;


/**
 * Created by mirko on 2017/4/10.
 */

public interface TemplateModelService {

    String getTemplateModelByUserIdAndTemplateId(String userId, String templateId);

    String deleteTemplateModel(String userId,String templateId);

    String insertNewTemplateModel(TemplateModelBean templateModelBean);



}
