package com.wandoujia.hackday.apppath.service;


import com.wandoujia.hackday.apppath.model.AppPointModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Jerry on 2/6/14.
 */
public interface AppTimelineQueryService {
    /**
     * 获取 timeline 的数据
     * 
     * @param udid
     * @param uid
     * @param date
     * @return
     */
    public List<AppPointModel> listAppPoint(String udid, Long uid, Date date);
}
