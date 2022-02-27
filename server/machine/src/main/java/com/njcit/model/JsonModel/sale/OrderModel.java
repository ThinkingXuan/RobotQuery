package com.njcit.model.JsonModel.sale;

import com.njcit.model.sale.Order;

import java.util.List;

/**
 * Created by mirko on 2017/5/17.
 */
public class OrderModel {

    private String message;
    private String code;
    private List<Order> object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Order> getObject() {
        return object;
    }

    public void setObject(List<Order> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }
}
