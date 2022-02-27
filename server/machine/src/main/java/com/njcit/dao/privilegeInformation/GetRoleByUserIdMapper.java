package com.njcit.dao.privilegeInformation;

/**
 * Created by mirko on 2017/4/27.
 */
public interface GetRoleByUserIdMapper {

    String getRoleIdByUserId(String userId);

    String getRoleNameByRoleId(String roleId);

}
