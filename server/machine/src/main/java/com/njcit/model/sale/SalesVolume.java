package com.njcit.model.sale;

/**
 * Created by mirko on 2017/4/13.
 */
public class SalesVolume {
    private String salesVolumeId;
    private String salesVolumeProfit;
    private String salesVolumeTime;
    private String salesVolumePureProfit;
    private String salesVolumeDetailId;;
    public String getSalesVolumeId() {
        return salesVolumeId;
    }
    public void setSalesVolumeId(String salesVolumeId) {
        this.salesVolumeId = salesVolumeId;
    }
    public String getSalesVolumeProfit() {
        return salesVolumeProfit;
    }
    public void setSalesVolumeProfit(String salesVolumeProfit) {
        this.salesVolumeProfit = salesVolumeProfit;
    }
    public String getSalesVolumeTime() {
        return salesVolumeTime;
    }
    public void setSalesVolumeTime(String salesVolumeTime) {
        this.salesVolumeTime = salesVolumeTime;
    }
    public String getSalesVolumePureProfit() {
        return salesVolumePureProfit;
    }
    public void setSalesVolumePureProfit(String salesVolumePureProfit) {
        this.salesVolumePureProfit = salesVolumePureProfit;
    }
    public String getSalesVolumeDetailId() {
        return salesVolumeDetailId;
    }
    public void setSalesVolumeDetailId(String salesVolumeDetailId) {
        this.salesVolumeDetailId = salesVolumeDetailId;
    }
    @Override
    public String toString() {
        return "SalesVolume{" +
                "salesVolumeId='" + salesVolumeId + '\'' +
                ", salesVolumeProfit='" + salesVolumeProfit + '\'' +
                ", salesVolumeTime='" + salesVolumeTime + '\'' +
                ", salesVolumePureProfit='" + salesVolumePureProfit + '\'' +
                ", salesVolumeDetailId='" + salesVolumeDetailId + '\'' +
                '}';
    }
}
