package com.njcit.robotquery.bean.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/12 0012.
 */

public class Orders implements Serializable {


    /**
     * message : 获取成功
     * code : 1
     * object : [{"orderId":"10249","customerId":"TOMSP","orderDetailId":"11","orderDate":"1996-07-05 00:00:00","getOrderDate":"1996-07-05 00:00:00","sendOrderDate":"1996-07-10 00:00:00","orderDeliverAddress":"22","shipperId":"1","orderTotalPrice":"33","arriveOrderDate":"55","productId":"66","productNumber":"77","productTotalPrice":"88","totalPrice":"99"}]
     */

    private String message;
    private String code;
    @SerializedName("object")
    private List<OrderBean> orderList;

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

    public List<OrderBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderBean implements Serializable {
        /**
         * orderId : 10249
         * customerId : TOMSP
         * orderDetailId : 11
         * orderDate : 1996-07-05 00:00:00
         * getOrderDate : 1996-07-05 00:00:00
         * sendOrderDate : 1996-07-10 00:00:00
         * orderDeliverAddress : 22
         * shipperId : 1
         * orderTotalPrice : 33
         * arriveOrderDate : 55
         * productId : 66
         * productNumber : 77
         * productTotalPrice : 88
         * totalPrice : 99
         */

        private String orderId;
        private String customerId;
        private String orderDetailId;
        private String orderDate;
        private String getOrderDate;
        private String orderDeliverAddress;
        private String shipperId;
        private String orderTotalPrice;
        private String arriveOrderDate;
        private String productId;
        private String productNumber;
        private String productTotalPrice;
        private String totalPrice;
        private String shipperPrice;
        private String ownerName;
        private String ownerAddress;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getOrderDetailId() {
            return orderDetailId;
        }

        public void setOrderDetailId(String orderDetailId) {
            this.orderDetailId = orderDetailId;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public String getGetOrderDate() {
            return getOrderDate;
        }

        public void setGetOrderDate(String getOrderDate) {
            this.getOrderDate = getOrderDate;
        }

        public String getOrderDeliverAddress() {
            return orderDeliverAddress;
        }

        public void setOrderDeliverAddress(String orderDeliverAddress) {
            this.orderDeliverAddress = orderDeliverAddress;
        }

        public String getShipperId() {
            return shipperId;
        }

        public void setShipperId(String shipperId) {
            this.shipperId = shipperId;
        }

        public String getOrderTotalPrice() {
            return orderTotalPrice;
        }

        public void setOrderTotalPrice(String orderTotalPrice) {
            this.orderTotalPrice = orderTotalPrice;
        }

        public String getArriveOrderDate() {
            return arriveOrderDate;
        }

        public void setArriveOrderDate(String arriveOrderDate) {
            this.arriveOrderDate = arriveOrderDate;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(String productNumber) {
            this.productNumber = productNumber;
        }

        public String getProductTotalPrice() {
            return productTotalPrice;
        }

        public void setProductTotalPrice(String productTotalPrice) {
            this.productTotalPrice = productTotalPrice;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getShipperPrice() {
            return shipperPrice;
        }

        public void setShipperPrice(String shipperPrice) {
            this.shipperPrice = shipperPrice;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }


        @Override
        public String toString() {
            return "OrderBean{" +
                    "orderId='" + orderId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", orderDetailId='" + orderDetailId + '\'' +
                    ", orderDate='" + orderDate + '\'' +
                    ", getOrderDate='" + getOrderDate + '\'' +
                    ", orderDeliverAddress='" + orderDeliverAddress + '\'' +
                    ", shipperId='" + shipperId + '\'' +
                    ", orderTotalPrice='" + orderTotalPrice + '\'' +
                    ", arriveOrderDate='" + arriveOrderDate + '\'' +
                    ", productId='" + productId + '\'' +
                    ", productNumber='" + productNumber + '\'' +
                    ", productTotalPrice='" + productTotalPrice + '\'' +
                    ", totalPrice='" + totalPrice + '\'' +
                    ", shipperPrice='" + shipperPrice + '\'' +
                    ", ownerName='" + ownerName + '\'' +
                    ", ownerAddress='" + ownerAddress + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Orders{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
