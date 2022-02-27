package com.njcit.model.JsonModel.chartbean.circle;

import java.util.List;

/**
 * Created by mirko on 2017/4/17.
 */
public class ProductPercent {
    private String code;
    private String message;
    private List<ProductPercent.EntryListBean> entryList;

    public static class EntryListBean{

        private String label;
        private String value;
        public String getLabel() {
            return label;
        }
        public void setLabel(String label) {
            this.label = label;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "EntryListBean{" +
                    "label='" + label + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
