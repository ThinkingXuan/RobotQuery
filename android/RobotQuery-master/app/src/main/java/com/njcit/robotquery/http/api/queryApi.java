package com.njcit.robotquery.http.api;

import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.bean.server.DayRecommedBean;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.PostBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.SalaryBean;
import com.njcit.robotquery.bean.server.SalesAcountBean;
import com.njcit.robotquery.bean.server.SubscribeBean;
import com.njcit.robotquery.bean.server.TrainBean;
import com.njcit.robotquery.bean.server.chartbean.LineBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by youxuan on 2017/4/10 0010.
 */

public interface queryApi {

    /**
     * 获取员工信息
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("/user/getContactWay.action")
    Observable<Employee> getEmployeeInfo(@Field("userId") String userId,
                                         @Field("userUsername") String userUsername);


    /**
     * 获取订单信息
     *
     * @param userId
     * @param orderId
     * @return
     */

    @FormUrlEncoded
    @POST("/user/getOrder.action")
    Observable<Orders> getOrdersInfo(@Field("userId") String userId,
                                     @Field("orderId") String orderId);


    /**
     * 获取产品信息
     *
     * @param userId
     * @param productId
     * @return
     */
    @FormUrlEncoded
    @POST("/user/getProduct.action")
    Observable<Products> getProductInfo(@Field("userId") String userId,
                                        @Field("productId") String productId);


    /**
     * 获取销售量,已折线图的形式展示
     *
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("/user/getSalesVolume.action")
    Observable<LineBean> getSalesAcount(@Field("userId") String userId);


    /**
     * 获取每日推荐数据
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @FormUrlEncoded
    @POST("/Daily/getRecommendByUserId.action")
    Observable<DayRecommedBean> getDayRecommdBean(@Field("userId") String userId,
                                                  @Field("page") int page,
                                                  @Field("size") int size);

    /**
     * 订阅
     * @param userId  用户Id
     * @param dailyRecommendModelId  数据Id
     * @return
     */
    @FormUrlEncoded
    @POST("/system/Concern.action")
    Observable<BaseBean> subscribe(@Field("userId")String userId,
                                    @Field("templateId")String dailyRecommendModelId);

    /**
     * 取消关注
     * @param userId
     * @param dailyRecommendModelId
     * @return
     */
    @FormUrlEncoded
    @POST("/system/CancelConcern.action")
    Observable<BaseBean> cancelSubscribe(@Field("userId")String userId,
                                         @Field("templateId")String dailyRecommendModelId);

    /**
     * 获取我的订阅
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @FormUrlEncoded
    @POST("/Daily/getConcernRecommendByUserId.action  ")
    Observable<SubscribeBean> getSubscribeMessage(@Field("userId")String userId,
                                                  @Field("page")int page,
                                                  @Field("size")int size);


    /**
     * 获取模板中心的模板信息
     * @param userId
     * @param page
     * @param size
     * @return
     */

    @FormUrlEncoded
    @POST("")
    Observable<SubscribeBean> getTemplateCenterData(@Field("userId")String userId,
                                                    @Field("page")int page,
                                                    @Field("size")int size);
    /**
     * 获取员工信息
     * @param body 模板的Json数据
     * @return
     */
    @POST("/user/getCondition.action")
    Observable<EmployeeBean> getEmployeeData(@Body RequestBody body);

    /**
     * 获取出勤状况
     * @param body
     * @return
     */
    @POST("/hr/getUserAttendance.action")
    Observable<AttendanceBean> getAttanceData(@Body RequestBody body);


    /**
     *获取绩效考核状况
     * @param body
     * @return
     */
    @POST("/hr/getUserPerformance.action")
    Observable<CheckBean> getCheckData(@Body RequestBody body);


    /**
     * 获取员工培训情况
     * @param body
     * @return
     */
    @POST("/hr/getUserTraining.action")
    Observable<TrainBean> getTrainData(@Body RequestBody body);


    @POST("/hr/getPostRecruit.action")
    Observable<PostBean> getPostData(@Body RequestBody body);

    /**
     * 获取员工薪资情况
     * @param body
     * @return
     */
    @POST("/user/getUserSalaryInformation.action")
    Observable<SalaryBean> getSalaryData(@Body RequestBody body);

    /**
     * 获取销售量
     * @param startTime
     * @param endTime
     * @return
     */

    @FormUrlEncoded
    @POST("/user/getSalesVolume.action")
    Observable<SalesAcountBean> getSalesAcount(@Field("startTime")String startTime,
                                               @Field("endTime")String endTime);

    /**
     * 获取产品查询数据
     * @param body
     * @return
     */
    @POST("/sale/getProduct.action")
    Observable<Products> getProductsData(@Body RequestBody body);

    /**
     * 获取订单数据
     * @param body
     * @return
     */
    @POST("/sale/getOrder.action")
    Observable<Orders> getOrderData(@Body RequestBody body);
}
