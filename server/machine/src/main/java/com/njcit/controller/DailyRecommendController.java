package com.njcit.controller;
import com.njcit.dao.privilegeInformation.Get.GetTemplateTag;
import com.njcit.service.DailyRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/Daily", produces = "text/html;charset=UTF-8")
public class DailyRecommendController {

    @Autowired
    @Qualifier("dailyRecommendService")
    DailyRecommendService dailyRecommendService;



    @RequestMapping(value = "/getDailyRecommend.action", method = RequestMethod.POST)
    @ResponseBody
    public String getSalesVolume(String userId, int page, int size) {

        return dailyRecommendService.getRecommend(userId, ((page - 1) * size), size);

    }

    @RequestMapping(value = "/setDailyRecommend.action", method = RequestMethod.POST)
    @ResponseBody
    public void setSalesVolume() {
        dailyRecommendService.insert();
    }


    @RequestMapping(value = "/getRecommendByUserId.action", method = RequestMethod.POST)
    @ResponseBody
    public String getRecommendBUserId(String userId, int page, int size) {

        return dailyRecommendService.getRecommendByUserId(userId,((page - 1) * size), size);

    }

    @RequestMapping(value = "/getConcernRecommendByUserId.action", method = RequestMethod.POST)
    @ResponseBody
    public String getConcernRecommendBUserId(String userId, int page, int size) {
        System.out.println(userId+page+size);
        return dailyRecommendService.getConcernedRecommendByUserId(userId,((page-1)*size), size);

    }
}


