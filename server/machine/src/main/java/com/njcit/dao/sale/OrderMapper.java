package com.njcit.dao.sale;

import com.njcit.model.concrete_search_by_short_sql.sale.OrderSQL;
import com.njcit.model.sale.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> selectOrder(OrderSQL orderSQL);

}