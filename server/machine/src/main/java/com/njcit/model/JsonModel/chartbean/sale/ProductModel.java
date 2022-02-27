package com.njcit.model.JsonModel.chartbean.sale;

import java.util.List;

/**
 * Created by mirko on 2017/4/10.
 */
public class ProductModel {

    private String message;
    private String code;
    private List<ProductModel.ObjectBean> object;


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
    public List<ProductModel.ObjectBean> getObject() {
        return object;
    }
    public void setObject(List<ProductModel.ObjectBean> object) {
        this.object = object;
    }
    public static class ObjectBean {
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
            return "ObjectBean{" +
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
}
