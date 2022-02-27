package com.njcit.dao.privilegeInformation;
import java.util.List;
/**
 * Created by mirko on 2017/4/27.
 */
public interface GetTemplateTagByPrivilegeIdMapper {

    List<String> getTemplateTagIdByPrivilegeId(String privilegeId);

    String getTemplateTagNameByTemplateTagId(String templateId);



}
