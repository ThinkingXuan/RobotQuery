package com.njcit.controller;
import com.njcit.dao.user.UserDao;
import com.njcit.service.UserContactWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserContactWayController {
    @Autowired
    @Qualifier("userContactWayService")
    UserContactWayService userContactWayService;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/getContactWay.action", method = RequestMethod.POST)
    @ResponseBody
    public String getUserContactWay(String userUsername,String userId) {



        return userContactWayService.getUserContactWayByUsername(userUsername);




    }


}


