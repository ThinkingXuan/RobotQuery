package com.njcit.model.sale;

/**
 * Created by mirko on 2017/4/10.
 */
public class Product {

    private String productId;
    private String productName;
    private String supplierId;
    private String typeId;
    private String perCount;
    private float price;
    private int storageCount;
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getTypeId() {
        return typeId;
    }
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getPerCount() {
        return perCount;
    }
    public void setPerCount(String perCount) {
        this.perCount = perCount;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getStorageCount() {
        return storageCount;
    }
    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }
}
