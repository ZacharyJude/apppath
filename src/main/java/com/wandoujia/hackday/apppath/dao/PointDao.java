package com.wandoujia.hackday.apppath.dao;

import java.util.Collection;
import java.util.Date;

public interface PointDao {

    public class AddPointArgModel {
        public Long uid;
        public String udid;
        public String packageName;
        public Date start;
        public Long duration;

        public AddPointArgModel(Long uid, String udid, String packageName, Date start, Long duration) { 
            this.uid = uid;
            this.udid = udid;
            this.packageName = packageName;
            this.start = start;
            this.duration = duration;
        }

        public AddPointArgModel() {
            this(null, null, null, null, null);
        }
    }
    
    void add(Collection<AddPointArgModel> args);
}
