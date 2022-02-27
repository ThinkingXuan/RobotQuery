package com.njcit.robotquery.bean.server;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by youxuan on 2017/4/25 0025.
 *员工培训
 */

public class TrainBean implements Serializable {

    private String message;
    private String code;

    private List<TrainData> object;

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

    public List<TrainData> getObject() {
        return object;
    }

    public void setObject(List<TrainData> object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "TrainBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", object=" + object +
                '}';
    }


    public static class TrainData implements Serializable{

        private String trainId;
        private String trainName;
        private String trainTarget;
        private String trainTrainer;
        private Date trainDate;

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

        public Date getTrainDate() {
            return trainDate;
        }

        public void setTrainDate(Date trainDate) {
            this.trainDate = trainDate;
        }

        @Override
        public String toString() {
            return "TrainData{" +
                    "trainId='" + trainId + '\'' +
                    ", trainName='" + trainName + '\'' +
                    ", trainTarget='" + trainTarget + '\'' +
                    ", trainTrainer='" + trainTrainer + '\'' +
                    ", trainDate=" + trainDate +
                    '}';
        }
    }
}
