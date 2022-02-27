package com.njcit.service;

import java.util.List;

/**
 * Created by mirko on 2017/4/27.
 */
public interface UserRoleAndPrivilege {

    List<String> getPrivileges(String userId);
}
