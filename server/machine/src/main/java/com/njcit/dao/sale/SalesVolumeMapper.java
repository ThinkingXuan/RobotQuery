package com.njcit.dao.sale;


import com.njcit.model.sale.SalesVolume;

import java.util.List;

/**
 * Created by mirko on 2017/3/29.
 */
public interface SalesVolumeMapper {

    List<SalesVolume> getAllSalesVolume(String startTime,String endTime);

}
