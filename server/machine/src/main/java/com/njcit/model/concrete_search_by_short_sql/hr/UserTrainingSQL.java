package com.njcit.model.concrete_search_by_short_sql.hr;

/**
 * Created by mirko on 2017/4/24.
 */
public class UserTrainingSQL {
    private String trainId;
    private String trainName;
    private String trainTarget;
    private String trainTrainer;
    private String trainDate;

    public String getTrainId() {
        return trainId;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }
    public String getTrainName() {
        return trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    public String getTrainTarget() {
        return trainTarget;
    }
    public void setTrainTarget(String trainTarget) {
        this.trainTarget = trainTarget;
    }
    public String getTrainTrainer() {
        return trainTrainer;
    }
    public void setTrainTrainer(String trainTrainer) {
        this.trainTrainer = trainTrainer;
    }
    public String getTrainDate() {
        return trainDate;
    }
    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
    @Override
    public String toString() {
        return "UserTrainingSQL{" +
                "trainId='" + trainId + '\'' +
                ", trainName='" + trainName + '\'' +
                ", trainTarget='" + trainTarget + '\'' +
                ", trainTrainer='" + trainTrainer + '\'' +
                ", trainDate='" + trainDate + '\'' +
                '}';
    }
}
