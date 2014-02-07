package com.wandoujia.hackday.apppath.service;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.protocol.OperationStatus;

public interface PointService {

    public class AddUdidPointArgModel {
        private Long uid;
        private String udid;
        private Collection<PointDao.AddPointArgModel> points;

        public AddUdidPointArgModel(Long uid, String udid) {
            this.uid = uid;
            this.udid = udid;
            this.points = new ArrayList<PointDao.AddPointArgModel>();
        }

        public void addPoint(
            String packageName, 
            String data,
            Date start, 
            Long duration,
            Long longitude,
            Long latitude
        ) {
            this.points.add(new PointDao.AddPointArgModel(
                this.uid,
                this.udid,
                packageName,
                data,
                start,
                duration,
                longitude,
                latitude
            ));
        }

        public Collection<PointDao.AddPointArgModel> getPoints() {
            return this.points;
        }
    }

    OperationStatus addUdidPoints(AddUdidPointArgModel arg);
}
