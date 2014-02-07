package com.wandoujia.hackday.apppath.dao;

import java.util.Collection;
import java.util.Date;

public interface PointDao {

    public class AddPointArgModel {
        public Long uid;
        public String udid;
        public String packageName;
        public String data;
        public Date start;
        public Long duration;
        public Double longitude;
        public Double latitude;

        public AddPointArgModel(
            Long uid, 
            String udid, 
            String packageName, 
            String data,
            Date start, 
            Long duration,
            Double longitude,
            Double latitude
        ) { 
            this.uid = uid;
            this.udid = udid;
            this.packageName = packageName;
            this.data = data;
            this.start = start;
            this.duration = duration;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public AddPointArgModel() {
            this(null, null, null, null, null, null, null, null);
        }
    }
    
    void add(Collection<AddPointArgModel> args);
}
