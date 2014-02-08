package com.wandoujia.hackday.apppath.dao;

import com.wandoujia.hackday.apppath.model.AppPointModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerry on 2/6/14.
 */
public interface AppTimelineQueryDao {

    /**
     * 获取 timeline 的数据
     * 
     * @param udid
     * @param uid
     * @param date
     * @return
     */
    public List<AppPointModel> listAppPoints(String udid, Long uid, Date start, Date end);

    public BigDecimal sumFreedownload(Long uid, Date start, Date end);

    public Long countPayOrders(Long uid, Date start, Date end);

    public BigDecimal sumPayOrders(Long uid, Date start, Date end);

    public Long sumFreeAmount(Long uid, Date start, Date end);

    public List<AppPointModel> listFreeTraffics(Long uid, Date start, Date end);

    public List<AppPointModel> listPayOrders(Long uid, Date start, Date end);

    public List<AppPointModel> listFreeAmounts(Long uid, Date start, Date end);


}
