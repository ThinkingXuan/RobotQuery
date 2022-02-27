package com.njcit.model.sale;

public class Order {
    private String orderId;

    private String customerId;

    private String employeeId;

    private String orderDate;

    private String getOrderDate;

    private String shipperId;

    private float shipperPrice;

    private String ownerName;

    private String ownerAddress;

    private String ownerCity;

    private String ownerState;

    private String ownerZipCode;

    private String ownerCountry;

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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public float getShipperPrice() {
        return shipperPrice;
    }

    public void setShipperPrice(float shipperPrice) {
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

    public String getOwnerCity() {
        return ownerCity;
    }

    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    public String getOwnerState() {
        return ownerState;
    }

    public void setOwnerState(String ownerState) {
        this.ownerState = ownerState;
    }

    public String getOwnerZipCode() {
        return ownerZipCode;
    }

    public void setOwnerZipCode(String ownerZipCode) {
        this.ownerZipCode = ownerZipCode;
    }


    public String getOwnerCountry() {
        return ownerCountry;
    }

    public void setOwnerCountry(String ownerCountry) {
        this.ownerCountry = ownerCountry;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", getOrderDate='" + getOrderDate + '\'' +
                ", shipperId='" + shipperId + '\'' +
                ", shipperPrice=" + shipperPrice +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", ownerCity='" + ownerCity + '\'' +
                ", ownerState='" + ownerState + '\'' +
                ", ownerZipCode='" + ownerZipCode + '\'' +
                ", ownerCountry='" + ownerCountry + '\'' +
                '}';
    }
}