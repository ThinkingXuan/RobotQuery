package com.njcit.robotquery.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.CheckBean;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.PostBean;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.TrainBean;
import com.njcit.robotquery.bean.server.chartbean.LineBean;
import com.njcit.robotquery.util.DateUtil;


/**
 * Created by youxuan on 2017/4/13 0013.
 */

public class BindView {

    public static final String TAG = BindView.class.getSimpleName();

    public static View bindEmployeeView(Context context, EmployeeBean employee) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_employee, null);

        TextView tvEmployeeUsername;
        TextView tvEmployeeGender;
        TextView tvEmployeeAge;
        TextView tvEmployeePosition;
        TextView tvEmployeeSalary;
        TextView tvEmployeeDepartment;
        TextView tvDataCardCount;

        tvEmployeeUsername = (TextView) view.findViewById(R.id.tv_employee_username);
        tvEmployeeGender = (TextView) view.findViewById(R.id.tv_employee_gender);
        tvEmployeeAge = (TextView) view.findViewById(R.id.tv_employee_age_detail);
        tvEmployeePosition = (TextView) view.findViewById(R.id.tv_employee_position_detail);
        tvEmployeeSalary = (TextView) view.findViewById(R.id.tv_employee_salary_detail);
        tvEmployeeDepartment = (TextView) view.findViewById(R.id.tv_employee_department_detail);
        EmployeeBean.EmployeeData bean = employee.getObject().get(0);
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        tvEmployeeUsername.setText(bean.getUserUsername());
        tvEmployeeGender.setText(bean.getUserGender());
        tvEmployeeAge.setText(bean.getUserAge() + "");
        tvEmployeePosition.setText(bean.getUserPosition());
        tvEmployeeSalary.setText(bean.getUserSalary() + "");
        tvEmployeeDepartment.setText(bean.getUserDepartment());
        tvDataCardCount.setText(employee.getObject().size() + "");

        return view;
    }

    public static View bindAttendanceView(Context context, AttendanceBean attendanceBean) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_attendance_detail, null);

        TextView tvAttendanceTime;
        TextView tvTvAttendanceAccount;
        TextView tvAttendanceRealAccount;
        TextView tvDataCardCount;


        tvAttendanceTime = (TextView) view.findViewById(R.id.tv_attendance_time);
        tvTvAttendanceAccount = (TextView) view.findViewById(R.id.tv_tv_attendance_account);
        tvAttendanceRealAccount = (TextView) view.findViewById(R.id.tv_attendance_real_account);
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        AttendanceBean.AttendanceData data = attendanceBean.getObject().get(0);
        tvAttendanceTime.setText(DateUtil.getDateToString(data.getAttendance_date()));
        tvTvAttendanceAccount.setText(data.getAttendanceAll() + "");
        tvAttendanceRealAccount.setText(data.getAttendanceReal() + "");
        tvDataCardCount.setText(attendanceBean.getObject().size() + "");
        return view;
    }


    public static View bindPostView(Context context, PostBean postBean) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_post_detail, null);

        TextView tvPostName;
        TextView tvPostPeopleAccount;
        TextView tvDataCardCount;
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);


        tvPostName = (TextView) view.findViewById(R.id.tv_post_name);
        tvPostPeopleAccount = (TextView) view.findViewById(R.id.tv_post_people_account);

        PostBean.Postdata postdata = postBean.getObject().get(0);
        tvPostName.setText(postdata.getPostName());
        tvPostPeopleAccount.setText((postdata.getPostNumber() - postdata.getPostRealNumber()) + "");
        tvDataCardCount.setText(postBean.getObject().size() + "");

        return view;
    }

    public static View bindTrainView(Context context, TrainBean trainBean) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_train_detail, null);

        TextView tvTrainName;
        TextView tvTrainAim;
        TextView tvTrainPeopleName;
        TextView tvTrainTime;
        TextView tvDataCardCount;
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        tvTrainName = (TextView) view.findViewById(R.id.tv_train_name);
        tvTrainAim = (TextView) view.findViewById(R.id.tv_train_aim);
        tvTrainPeopleName = (TextView) view.findViewById(R.id.tv_train_people_name);
        tvTrainTime = (TextView) view.findViewById(R.id.tv_train_time);

        TrainBean.TrainData trainData = trainBean.getObject().get(0);

        tvTrainName.setText(trainData.getTrainName());
        tvTrainAim.setText(trainData.getTrainTarget());
        tvTrainPeopleName.setText(trainData.getTrainTrainer());
        tvTrainTime.setText(trainData.getTrainDate() + "");
        tvDataCardCount.setText(trainBean.getObject().size() + "");

        return view;

    }


    public static View bindCheckView(Context context, CheckBean checkBean) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_check_detail, null);
        TextView tvCheckAttitudeDetail;
        TextView tvCheckResponsibilityDetail;
        TextView tvCheckWorkAbilityDetail;
        TextView tvCheckTrainAbilityDetail;
        TextView tvCheckAllDetail;
        TextView tvCheckUsernameDetail;

        TextView tvDataCardCount;


        tvCheckUsernameDetail = (TextView) view.findViewById(R.id.tv_check_username_detail);
        tvCheckAttitudeDetail = (TextView) view.findViewById(R.id.tv_check_attitude_detail);
        tvCheckResponsibilityDetail = (TextView) view.findViewById(R.id.tv_check_responsibility_detail);
        tvCheckWorkAbilityDetail = (TextView) view.findViewById(R.id.tv_check_workAbility_detail);
        tvCheckTrainAbilityDetail = (TextView) view.findViewById(R.id.tv_check_trainAbility_detail);
        tvCheckAllDetail = (TextView) view.findViewById(R.id.tv_check_all_detail);
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        CheckBean.CheckData checkData = checkBean.getObject().get(0);

        Log.d(TAG, "bindCheckView: " + checkData.toString());
        tvCheckUsernameDetail.setText(checkData.getUserId() + "");
        tvCheckAttitudeDetail.setText(checkData.getUserAttitude() + "");
        tvCheckResponsibilityDetail.setText(checkData.getUserResponsibility() + "");
        tvCheckWorkAbilityDetail.setText(checkData.getUserWorkAbility() + "");
        tvCheckTrainAbilityDetail.setText(checkData.getUserTrainingAbility() + "");
        tvCheckAllDetail.setText(checkData.getUserTotal() + "");
        tvDataCardCount.setText(checkBean.getObject().size() + "");
        return view;
    }

    public static View bindProductView(Context context, Products products) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_products_detail, null);

        TextView tvProductUsernameDetail;
        TextView tvProductTypeDetail;
        TextView tvProductPriceDetail;
        TextView tvProductPerCountDetail;

        TextView tvProductStorageCountDetail;
        TextView tvProductSuppler;

        TextView tvDataCardCount;
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        tvProductUsernameDetail = (TextView) view.findViewById(R.id.tv_product_username_detail);
        tvProductTypeDetail = (TextView) view.findViewById(R.id.tv_product_type_detail);
        tvProductPriceDetail = (TextView) view.findViewById(R.id.tv_product_price_detail);
        tvProductPerCountDetail = (TextView) view.findViewById(R.id.tv_product_per_count_detail);

        tvProductStorageCountDetail = (TextView) view.findViewById(R.id.tv_product_storageCount_detail);
        tvProductSuppler = (TextView) view.findViewById(R.id.tv_product_suppler);

        Products.ProductBean bean = products.getProList().get(0);
        tvProductUsernameDetail.setText(bean.getProductName());
        tvProductTypeDetail.setText(bean.getTypeId());
        tvProductPriceDetail.setText(bean.getPrice());
        tvProductPerCountDetail.setText(bean.getPerCount());
        tvProductStorageCountDetail.setText(bean.getStorageCount());
        tvProductSuppler.setText(bean.getSupplierId());
        tvDataCardCount.setText(products.getProList().size() + "");
        return view;
    }

    public static View bindOrderView(Context context, Orders orders) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_order_detail, null);
        TextView tvOrderNumberDetail;
        TextView tvOrderPrice;
        TextView tvOrderUser;
        TextView tvOrderAddress;
        TextView tvOrderTime;
        TextView tvOrderReceiveTime;

        TextView tvDataCardCount;
        tvDataCardCount = (TextView) view.findViewById(R.id.tv_data_card_count);

        tvOrderNumberDetail = (TextView) view.findViewById(R.id.tv_order_number_detail);
        tvOrderPrice = (TextView) view.findViewById(R.id.tv_order_price);
        tvOrderUser = (TextView) view.findViewById(R.id.tv_order_user);
        tvOrderAddress = (TextView) view.findViewById(R.id.tv_order_address);
        tvOrderTime = (TextView) view.findViewById(R.id.tv_order_time);
        tvOrderReceiveTime = (TextView) view.findViewById(R.id.tv_order_receive_time);
        Orders.OrderBean bean = orders.getOrderList().get(0);

        tvOrderNumberDetail.setText(bean.getOrderId() + "");
        tvOrderPrice.setText(bean.getShipperPrice() + " 元");
        tvOrderUser.setText(bean.getOwnerName() + "");
        tvOrderAddress.setText(bean.getOwnerAddress() + "");
        tvOrderTime.setText(bean.getOrderDate() + "");
        tvOrderReceiveTime.setText(bean.getGetOrderDate() + "");
        tvDataCardCount.setText(orders.getOrderList().size() + "");

        return view;
    }
}
