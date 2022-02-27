package com.njcit.controller;

import com.njcit.service.UserConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by mirko on 2017/4/27.
 */
@Controller
@RequestMapping(value = "/system", produces = "text/html;charset=UTF-8")
public class UserConcernController {

    @Autowired
    @Qualifier("userConcernService")
    UserConcernService userConcernService;

    @RequestMapping(value = "/Concern.action", method = RequestMethod.POST)
    @ResponseBody
    public  String getUserConcern(String userId,String templateId) throws IOException {

       return userConcernService.userConcern(userId,templateId);


    }

    @RequestMapping(value = "/CancelConcern.action", method = RequestMethod.POST)
    @ResponseBody
    public  String getCancelConcern(String userId,String templateId) throws IOException {

            return userConcernService.cancelConcern(userId,templateId);

    }




}
