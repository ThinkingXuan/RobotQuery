package com.njcit.service;

/**
 * Created by mirko on 2017/4/27.
 */
public interface UserConcernService {


    String userConcern(String userId,String templateId);
    String cancelConcern(String userId,String templateId);

}
