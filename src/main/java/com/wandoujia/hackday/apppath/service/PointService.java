package com.wandoujia.hackday.apppath.service;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.protocol.OperationStatus;

public interface PointService {

    public class AddUdidPointArgModel {
        private String udid;
        private Collection<PointDao.AddPointArgModel> points;

        public AddUdidPointArgModel(String udid) {
            this.udid = udid;
            this.points = new ArrayList<PointDao.AddPointArgModel>();
        }

        public void addPoint(String packageName, Date start, Long duration) {
            this.points.add(new PointDao.AddPointArgModel(
                this.udid,
                packageName,
                start,
                duration
            ));
        }

        public Collection<PointDao.AddPointArgModel> getPoints() {
            return this.points;
        }
    }

    OperationStatus addUdidPoints(AddUdidPointArgModel arg);
}
