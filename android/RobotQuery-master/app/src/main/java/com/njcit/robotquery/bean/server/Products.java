package com.njcit.robotquery.bean.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxuan on 2017/4/12 0012.
 */

public class Products implements Serializable{

    /**
     * message : 获取成功
     * code : 1
     * object : [{"productId":"1","productName":"苹果汁","supplierId":"1","typeId":"1","perCount":"每箱24瓶","price":"18.0000","storageCount":"39","orderCouter":"0"}]
     */

    private String message;
    private String code;
    @SerializedName("object")
    private List<ProductBean> proList;

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

    public List<ProductBean> getProList() {
        return proList;
    }

    public void setProList(List<ProductBean> proList) {
        this.proList = proList;
    }

    public static class ProductBean implements Serializable {
        /**
         * productId : 1
         * productName : 苹果汁
         * supplierId : 1
         * typeId : 1
         * perCount : 每箱24瓶
         * price : 18.0000
         * storageCount : 39
         * orderCouter : 0
         */

        private String productId;
        private String productName;
        private String supplierId;
        private String typeId;
        private String perCount;
        private String price;
        private String storageCount;
        private String orderCouter;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStorageCount() {
            return storageCount;
        }

        public void setStorageCount(String storageCount) {
            this.storageCount = storageCount;
        }

        public String getOrderCouter() {
            return orderCouter;
        }

        public void setOrderCouter(String orderCouter) {
            this.orderCouter = orderCouter;
        }

        @Override
        public String toString() {
            return "ProductBean{" +
                    "productId='" + productId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", supplierId='" + supplierId + '\'' +
                    ", typeId='" + typeId + '\'' +
                    ", perCount='" + perCount + '\'' +
                    ", price='" + price + '\'' +
                    ", storageCount='" + storageCount + '\'' +
                    ", orderCouter='" + orderCouter + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Products{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", proList=" + proList +
                '}';
    }
}
