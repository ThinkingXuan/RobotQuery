package com.njcit.service.impl;
import com.njcit.dao.user.TemplateModelMapper;
import com.njcit.model.JsonModel.CommonJsonModel;
import com.njcit.model.JsonModel.chartbean.user.TemplateModelModel;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.model.user.UserSearchCondition;
import com.njcit.service.TemplateModelService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirko on 2017/4/14.
 */
@Service("templateModelService")
public class TemplateModelServiceImpl implements TemplateModelService {

    @Autowired
    TemplateModelMapper templateModelMapper;


    @Override
    public String getTemplateModelByUserIdAndTemplateId(String userId, String templateId) {

        List<UserSearchCondition> list = templateModelMapper.getConcernByUserIdAndTemplateId(userId,templateId);
        if (list.size()<=0){
            TemplateModelModel templateModelModel = new TemplateModelModel();
            templateModelModel.setMessage("没有该模板");
            templateModelModel.setCode("0");
            return GsonUtil.ObjectToJson(templateModelModel, templateModelModel.getClass());

        }else {


            UserSearchCondition userSearchCondition = list.get(0);
            String content = userSearchCondition.getContent();
            TemplateModelBean templateModelBean = (TemplateModelBean) GsonUtil.JsonToObject(content,TemplateModelBean.class);

            TemplateModelModel templateModelModel = new TemplateModelModel();
            templateModelModel.setMessage("获取成功");
            templateModelModel.setCode("1");
            List<TemplateModelModel.ObjectBean> list1 = new ArrayList<TemplateModelModel.ObjectBean>();
            TemplateModelModel.ObjectBean objectBean = new TemplateModelModel.ObjectBean();
            objectBean.setTemplateModelBean(templateModelBean);
            list1.add(objectBean);
            templateModelModel.setObject(list1);

            return GsonUtil.ObjectToJson(templateModelModel, templateModelModel.getClass());

        }
    }

    @Override
    public String deleteTemplateModel(String userId, String templateId) {

        templateModelMapper.deleteConcern(userId,templateId);
        CommonJsonModel commonJsonModel = new CommonJsonModel();
        commonJsonModel.setMessage("成功删除模板");
        commonJsonModel.setCode("1");
        return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
    }

    @Override
    public String insertNewTemplateModel(TemplateModelBean templateModelBean) {


        UserSearchCondition userSearchCondition = new UserSearchCondition();
        userSearchCondition.setUserId(templateModelBean.getUserId());
        userSearchCondition.setRole(templateModelBean.getRole());
        userSearchCondition.setTime(templateModelBean.getTime());
        userSearchCondition.setTemplateId(templateModelBean.getTemplateId());
        userSearchCondition.setType(templateModelBean.getType());
        userSearchCondition.setContent(GsonUtil.ObjectToJson(templateModelBean, templateModelBean.getClass()));
        templateModelMapper.insertNewConcern(userSearchCondition);
        CommonJsonModel commonJsonModel = new CommonJsonModel();
        commonJsonModel.setMessage("成功插入模板");
        commonJsonModel.setCode("1");
        return GsonUtil.ObjectToJson(commonJsonModel,commonJsonModel.getClass());
    }
}
