package com.njcit.model.JsonModel.sale;

import com.njcit.model.sale.Product;

import java.util.List;

/**
 * Created by mirko on 2017/5/16.
 */
public class ProductJsonModel {
    private String message;
    private String code;
    private List<Product> object;
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
    public List<Product> getObject() {
        return object;
    }
    public void setObject(List<Product> object) {
        this.object = object;
    }
}
