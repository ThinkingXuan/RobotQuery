package com.njcit.robotquery.moudle.detail.sub;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.base.BaseFragment;
import com.njcit.robotquery.bean.ObjType;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.Products;
import com.njcit.robotquery.bean.server.chartbean.LineBean;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.databinding.FragmentDetailTextBinding;


/**
 * Created by youxuan on 2017/4/7 0007.
 */

public class TextFragemnt extends BaseFragment<FragmentDetailTextBinding> {

    private TextView tvEmployeePhone;
    private TextView tvQqNumber;
    private TextView tvWechatNumber;
    private TextView tvEmialNumber;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initData();
    }

    private void initData() {
        Bundle bundle = getActivity().getIntent().getExtras();
        int type = (int) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_TYPE);

        if (type == ObjType.EMPLOYEE.ordinal()){
            Employee employee = (Employee) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
            Employee.ObjectBean objectBean = employee.getObject().get(0);

            View view = LayoutInflater.from(getContext()).inflate(R.layout.card_employee,null);


//            tvEmployeePhone = (TextView) view.findViewById(R.id.tv_employee_phone);
//            tvQqNumber = (TextView) view.findViewById(R.id.tv_qq_number);
//            tvWechatNumber = (TextView) view.findViewById(R.id.tv_wechat_number);
//            tvEmialNumber = (TextView) view.findViewById(R.id.tv_emial_number);
//
//            tvEmployeePhone.setText(objectBean.getUserPhone());
//            tvQqNumber.setText(objectBean.getUserQQ());
//            tvWechatNumber.setText(objectBean.getUserWechat());
//            tvEmialNumber.setText(objectBean.getUserEmail());

            mBindingView.linearDetail.addView(view);

        }else if (type == ObjType.PRODUCT.ordinal()){
            Products products = (Products) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
            //mBindingView.tvDetailText.setText(products.toString());
        }else if (type == ObjType.ORDER.ordinal()){
            Orders orders = (Orders) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
            //mBindingView.tvDetailText.setText(orders.toString());
        }else if (type == ObjType.SALE.ordinal()){
            LineBean lineBean = (LineBean) bundle.get(Constants.ACTIVIIY_BUNDLE_KEY_OBJ);
           // mBindingView.tvDetailText.setText(lineBean.toString());
        }
    }

    @Override
    public int setContent() {
        return R.layout.fragment_detail_text;
    }
}
