package com.njcit.controller;
import com.njcit.dao.privilegeInformation.Get.GetTemplateTag;
import com.njcit.dao.privilegeInformation.Get.TemplateTagModel;
import com.njcit.model.TemplateTag;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
    獲得所有模板
 */
@Controller
@RequestMapping(value = "/system", produces = "text/html;charset=UTF-8")
public class TemplateTagController {

    @Autowired
    @Qualifier("getTemplateTag")
    GetTemplateTag getTemplateTag;


    @RequestMapping(value = "/getTemplateTag.action", method = RequestMethod.POST)
    @ResponseBody
    public String getUserContactWay(String userId) {
         List<TemplateTag> list =  getTemplateTag.getTemplateTags(userId);

         for(TemplateTag templateTag:list)
         {
             System.out.println(templateTag.toString());
         }
        TemplateTagModel templateTagModel = new TemplateTagModel();
         templateTagModel.setMessage("获取成功");
         templateTagModel.setCode("1");
         templateTagModel.setObject(list);
        System.out.println( GsonUtil.ObjectToJson(templateTagModel,templateTagModel.getClass()));
        return GsonUtil.ObjectToJson(templateTagModel,templateTagModel.getClass());

    }


}


