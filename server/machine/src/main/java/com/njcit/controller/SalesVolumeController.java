package com.njcit.controller;
import com.njcit.model.sale.SalesVolume;
import com.njcit.service.OrderService;
import com.njcit.service.SalesVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class SalesVolumeController {

    @Autowired
    @Qualifier("salesVolumeService")
    SalesVolumeService salesVolumeService;


    @RequestMapping(value = "/getSalesVolume.action", method = RequestMethod.POST)
    @ResponseBody
    public String getSalesVolume(String startTime,String endTime) {
        return salesVolumeService.getSalesVolume(startTime,endTime);
    }


}


