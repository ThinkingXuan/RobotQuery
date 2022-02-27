package com.njcit.dao.privilegeInformation;

import java.util.List;

/**
 * Created by mirko on 2017/4/27.
 */
public interface GetPrivilegeByRoleIdMapper {

    List<String> getPrivilegeIdByRoleId(String roleId);

    String getPrivilegeNameByPrivilegeId(String privilegeId);


}
