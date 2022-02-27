package com.njcit.service.impl;

import com.njcit.dao.DailyRecommendMapper;
import com.njcit.dao.privilegeInformation.Get.GetTemplateTag;
import com.njcit.dao.user.TemplateModelMapper;
import com.njcit.dao.user.UserConcernMapper;
import com.njcit.model.JsonModel.chartbean.article.DailyRecommendModel;
import com.njcit.model.article.DailyRecommend;
import com.njcit.service.DailyRecommendService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mirko on 2017/4/14.
 */
@Service("dailyRecommendService")
public class DailyRecommendServiceImpl implements DailyRecommendService {
    @Autowired
    DailyRecommendMapper dailyRecommendMapper;
    @Autowired
    TemplateModelMapper templateModelMapper;

    @Autowired
    UserConcernMapper userConcernMapper;

    @Autowired
    @Qualifier("getTemplateTag")
    GetTemplateTag getTemplateTag;


    @Override
    public void insert() {
        DailyRecommend dailyRecommend = new DailyRecommend();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

        dailyRecommend.setDailyRecommendTime(simpleDateFormat.format(new Date()));
        dailyRecommend.setDailyRecommendPicture("http://oodmh8sl5.bkt.clouddn.com/5397ae6a15eab.jpg");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("最新销量出炉啦~~~");
        dailyRecommend.setDailyRecommendContent(stringBuffer.substring(0,stringBuffer.length()-1));
        dailyRecommend.setDailyRecommendTitle(simpleDateFormat2.format(new Date())+"销量出炉啦~~~");
        dailyRecommend.setTemplateId("attendance");
        dailyRecommendMapper.insert(dailyRecommend);


    }

    @Override
    public String getRecommend(String userId,int page,int size) {
//        List<DailyRecommend> list = dailyRecommendMapper.getRecommend(page,size);
//        List<String> list2 = userConcernMapper.getDailyRecommendModelIdByUserId(userId);
//        Iterator iterator = list.iterator();
//        DailyRecommendModel dailyRecommendModel = new DailyRecommendModel();
//        List<DailyRecommendModel.ObjectBean> list1 = new ArrayList<DailyRecommendModel.ObjectBean>();
//        while (iterator.hasNext()){
//            DailyRecommendModel.ObjectBean objectBean = new DailyRecommendModel.ObjectBean();
//            DailyRecommend dailyRecommend = (DailyRecommend) iterator.next();
//            for (String s:list2){
//                if(dailyRecommend.getDailyRecommendModelId().equals(s)){
//                    objectBean.setDailyRecommendTitle(dailyRecommend.getDailyRecommendTitle());
//                    objectBean.setDailyRecommendTime(dailyRecommend.getDailyRecommendTime());
//                    objectBean.setDailyRecommendPicture(dailyRecommend.getDailyRecommendPicture());
//                    objectBean.setDailyRecommendContent(dailyRecommend.getDailyRecommendContent());
//                    objectBean.setDailyRecommendModelId(dailyRecommend.getDailyRecommendModelId());
//                    list1.add(objectBean);
//                }
//            }
//        }
//        dailyRecommendModel.setMessage("获取成功");
//        dailyRecommendModel.setCode("1");
//        dailyRecommendModel.setObject(list1);
//        System.out.println(list1.size());
//        String response =
//        GsonUtil.ObjectToJson(dailyRecommendModel,dailyRecommendModel.getClass());
//        return  response;
    return null;
    }

    @Override
    public void insertSelective(DailyRecommend record) {


    }

    @Override
    public String getRecommendByUserId(String userId, int page, int size) {

        List<String> list = getTemplateTag.getTemplateTagIds(userId);

        List<DailyRecommend> list1 = new ArrayList<DailyRecommend>();
        for(String s:list){
            List<DailyRecommend> list2 =dailyRecommendMapper.getRecommendByTemplateId(s,page,size);
            for (DailyRecommend dailyRecommend:list2){
                        list1.add(dailyRecommend);
            }
        }
        DailyRecommendModel dailyRecommendModel = new DailyRecommendModel();
        dailyRecommendModel.setMessage("获取成功");
        dailyRecommendModel.setCode("1");
        dailyRecommendModel.setObject(list1);
        System.out.println(list1.size());
        String response = GsonUtil.ObjectToJson(dailyRecommendModel,dailyRecommendModel.getClass());
        return  response;
    }

    @Override
    public String getConcernedRecommendByUserId(String userId, int page, int size) {

        List<String> list = userConcernMapper.getConcern(userId);
        System.out.println(list.size());
        List<DailyRecommend> list1 = new ArrayList<DailyRecommend>();
        for(String s:list){
            List<DailyRecommend> list2 =dailyRecommendMapper.getRecommendByTemplateId(s,page,size);
            for (DailyRecommend dailyRecommend:list2){
                list1.add(dailyRecommend);
            }
        }
        DailyRecommendModel dailyRecommendModel = new DailyRecommendModel();
        dailyRecommendModel.setMessage("获取成功");
        dailyRecommendModel.setCode("1");
        dailyRecommendModel.setObject(list1);
        System.out.println(list1.size());
        String response = GsonUtil.ObjectToJson(dailyRecommendModel,dailyRecommendModel.getClass());
        return  response;
    }

}
