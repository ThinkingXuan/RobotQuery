package com.njcit.robotquery.util;

import com.njcit.robotquery.bean.ObjType;
import com.njcit.robotquery.bean.local.TemplateBean;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.PostBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.TrainBean;
import com.njcit.robotquery.bean.server.chartbean.LineBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.http.ApiManage;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.POST;
import rx.Observable;
import rx.Subscription;

/**
 * Created by youxuan on 2017/4/13 0013.
 * 获取对应的被观察者subscription
 */

public class RxServiceUtil {

    private static RxServiceUtil instance;

    private RxServiceUtil() {

    }

    public static RxServiceUtil getInstance() {
        if (instance == null) {
            synchronized (RxServiceUtil.class) {
                if (instance == null) {
                    instance = new RxServiceUtil();
                }
            }
        }
        return instance;
    }


    public Observable getQuery(String userId, String param, int type) {

        if (type == ObjType.EMPLOYEE.ordinal()) {
            Observable<Employee> observable = ApiManage.getInstence().getQueryService().getEmployeeInfo(userId, param);

            return observable;
        } else if (type == ObjType.ORDER.ordinal()) {
            Observable<Orders> observable = ApiManage.getInstence().getQueryService().getOrdersInfo(userId, param);
            return observable;
        } else if (type == ObjType.PRODUCT.ordinal()) {
            Observable<Products> observable = ApiManage.getInstence().getQueryService().getProductInfo(userId, param);
            return observable;
        } else if (type == ObjType.SALE.ordinal()) {
            Observable<LineBean> observable = ApiManage.getInstence().getQueryService().getSalesAcount(userId);
            return observable;
        }
        return null;
    }


    public Observable getQueryObservable(Object o, String type) {
        TemplateBean templateBean = (TemplateBean) o;
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JsonUtil.getObjectJson(templateBean));
        switch (type) {
            case Constants.TEMPLATE_EMPLOYYEE:
                Observable<EmployeeBean> observable = ApiManage.getInstence().getQueryService().getEmployeeData(body);
                return observable;
            case Constants.TEMPLATE_ATTENDANCE:
                Observable<AttendanceBean> observable1 = ApiManage.getInstence().getQueryService().getAttanceData(body);
                return observable1;
            case Constants.TEMPLATE_CHECK:

                Observable<CheckBean> observable2 = ApiManage.getInstence().getQueryService().getCheckData(body);
                return observable2;
            case Constants.TEMPLATE_POST:
                Observable<PostBean> observable3 = ApiManage.getInstence().getQueryService().getPostData(body);
                return observable3;
            case Constants.TEMPLATE_TRAIN:
                Observable<TrainBean> observable4 = ApiManage.getInstence().getQueryService().getTrainData(body);
                return observable4;

            case Constants.TEMPLATE_PRODUCT:
                Observable<Products> observable5 = ApiManage.getInstence().getQueryService().getProductsData(body);
                return observable5;
            case Constants.TEMPLATE_ORDER:
                Observable<Orders> observable6 = ApiManage.getInstence().getQueryService().getOrderData(body);
                return observable6;
            default:
                break;


        }

        return null;

    }
//    public Observable<Employee> getQuery_Employee(String usename) {
//        Observable<Employee> observable = ApiManage.getInstence().getQueryService().getEmployeeInfo(usename);
//        return observable;
//    }
//
//    public Observable getQuery_Prodcut(String productId) {
//        Observable<Products> observable = ApiManage.getInstence().getQueryService().getProductInfo(productId);
//        return observable;
//    }
//
//    public Observable getQuery_Order(String orderId) {
//        Observable<Orders> observable = ApiManage.getInstence().getQueryService().getOrdersInfo(orderId);
//        return observable;
//    }
}
