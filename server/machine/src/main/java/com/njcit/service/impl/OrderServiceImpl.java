package com.njcit.service.impl;

import com.njcit.dao.sale.OrderMapper;
import com.njcit.model.JsonModel.sale.OrderModel;
import com.njcit.model.concrete_search_by_short_sql.sale.OrderSQL;
import com.njcit.model.sale.Order;
import com.njcit.model.user.TemplateModelBean;
import com.njcit.service.OrderService;
import com.njcit.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mirko on 2017/5/17.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;


    @Override
    public String getOrder(TemplateModelBean templateModelBean) {

        OrderSQL orderSQL = new OrderSQL();

        Map map = templateModelBean.getmMap();

        String sql1 = (String) map.get("order_id");
        String sql2 = (String) map.get("customer_id");
        String sql3 = (String)map.get("employee_id");
        String sql4 = (String)map.get("order_date");
        String sql5 = (String)map.get("get_order_date");
        String sql6 = (String)map.get("shipper_id");
        String sql7 = (String)map.get("shipper_price");
        String sql8 = (String)map.get("owner_name");
        String sql9 = (String)map.get("owner_address");
        String sql10 = (String)map.get("owner_city");
        String sql11 = (String)map.get("owner_state");
        String sql12 = (String)map.get("owner_zip_code");

        if (!(sql1==null||sql1.equals(""))){

            orderSQL.setOrder_id(sql1);

        }
        if (!(sql2==null||sql2.equals(""))){
            orderSQL.setCustomer_id(sql2);

        } if (!(sql3==null||sql3.equals(""))){
            orderSQL.setEmployee_id(sql3);

        }
        if (!(sql4==null||sql4.equals(""))){
            orderSQL.setOrder_date(sql4);

        }
        if (!(sql5==null||sql5.equals(""))){
            orderSQL.setGet_order_date(sql5);
        }
        if (!(sql6==null||sql6.equals(""))){
            orderSQL.setShipper_id(sql6);

        }
        if (!(sql7==null||sql7.equals(""))){
            orderSQL.setShipper_price(sql7);

        }
        if (!(sql8==null||sql8.equals(""))){
            orderSQL.setOwner_name(sql8);

        }
        if (!(sql9==null||sql9.equals(""))){

            orderSQL.setOwner_address(sql9);

        }
        if (!(sql10==null||sql10.equals(""))){
            orderSQL.setOwner_city(sql10);

        }
        if (!(sql11==null||sql11.equals(""))){
            orderSQL.setOwner_state(sql11);

        }
        if (!(sql12==null||sql12.equals(""))){
            orderSQL.setOwner_zip_code(sql12);

        }

        List<Order> list = orderMapper.selectOrder(orderSQL);

        OrderModel orderModel = new OrderModel();

        orderModel.setCode("1");
        orderModel.setMessage("发送成功");
        orderModel.setObject(list);
        return GsonUtil.ObjectToJson(orderModel,orderModel.getClass());
    }
}
