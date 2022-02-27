package com.njcit.service.impl;

import com.njcit.dao.sale.SalesVolumeMapper;
import com.njcit.model.JsonModel.chartbean.Line.LineModel;
import com.njcit.model.sale.SalesVolume;
import com.njcit.service.SalesVolumeService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by mirko on 2017/4/13.
 */
@Service("salesVolumeService")
public class SalesVolumeServiceImpl implements SalesVolumeService {
    @Autowired
    SalesVolumeMapper salesVolumeMapper;
    @Override
    public String getSalesVolume(String startTime,String ennTime) {
        List<SalesVolume> list = salesVolumeMapper.getAllSalesVolume(startTime,ennTime);
        System.out.println(list.size());
        LineModel lineModel = new LineModel();
        lineModel.setCode("1");
        lineModel.setMessage("获取成功");
        List<LineModel.EntryListBean> list1 = new ArrayList<LineModel.EntryListBean>();
        System.out.println("进入循环");

            for(SalesVolume salesVolume:list) {
                LineModel.EntryListBean objectBean = new LineModel.EntryListBean();
                objectBean.setX(salesVolume.getSalesVolumeTime());
                objectBean.setY(salesVolume.getSalesVolumePureProfit());
                list1.add(objectBean);
            }

        lineModel.setEntryList(list1);
        String response =
            GsonUtil.ObjectToJson(lineModel,lineModel.getClass());
        return response;
    }






}
