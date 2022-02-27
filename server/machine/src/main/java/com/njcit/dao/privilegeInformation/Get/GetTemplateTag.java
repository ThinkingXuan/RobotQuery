package com.njcit.dao.privilegeInformation.Get;

import com.njcit.dao.privilegeInformation.GetPrivilegeByRoleIdMapper;
import com.njcit.dao.privilegeInformation.GetRoleByUserIdMapper;
import com.njcit.dao.privilegeInformation.GetTemplateTagByPrivilegeIdMapper;
import com.njcit.model.TemplateTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirko on 2017/4/27.
 */
@Service("getTemplateTag")
public class GetTemplateTag {

    @Autowired
    GetRoleByUserIdMapper getRoleByUserIdMapper;
    @Autowired
    GetPrivilegeByRoleIdMapper getPrivilegeByRoleIdMapper;
    @Autowired
    GetTemplateTagByPrivilegeIdMapper getTemplateTagByPrivilegeIdMapper;

    public List<TemplateTag> getTemplateTags(String userId){

     String roleId = getRoleByUserIdMapper.getRoleIdByUserId(userId);
     List<String> privilege = getPrivilegeByRoleIdMapper.getPrivilegeIdByRoleId(roleId);
     List<String> templates = new ArrayList<String>();
     List<TemplateTag> templateTags = new ArrayList<TemplateTag>();
     for(String s :privilege){

         List<String> templateTag =getTemplateTagByPrivilegeIdMapper.getTemplateTagIdByPrivilegeId(s);

         for(String ss:templateTag){

             templates.add(ss);

         }
     }
     for (String sss:templates){

         TemplateTag templateTag = new TemplateTag();
         templateTag.setTemplateId(sss);
         templateTag.setTemplateName(
                 getTemplateTagByPrivilegeIdMapper.getTemplateTagNameByTemplateTagId(sss)
         );
         templateTags.add(templateTag);
     }
        return  templateTags;
    }

    public List<String> getTemplateTagIds(String userId) {

        String roleId = getRoleByUserIdMapper.getRoleIdByUserId(userId);

        List<String> privilege = getPrivilegeByRoleIdMapper.getPrivilegeIdByRoleId(roleId);

        List<String> templates = new ArrayList<String>();

        for (String s : privilege) {
            List<String> templateTag = getTemplateTagByPrivilegeIdMapper.getTemplateTagIdByPrivilegeId(s);
            for (String ss : templateTag) {
                templates.add(ss);
            }
        }
        return templates;
    }


}
