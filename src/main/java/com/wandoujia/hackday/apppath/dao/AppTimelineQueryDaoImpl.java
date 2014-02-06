package com.wandoujia.hackday.apppath.dao;

import com.wandoujia.hackday.apppath.model.AppPointModel;
import com.wandoujia.hackday.apppath.utils.DateTimeUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jerry on 2/6/14.
 */
@Repository
public class AppTimelineQueryDaoImpl implements AppTimelineQueryDao {

    @Autowired
    @Qualifier("apppathJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    @Override
    public List<AppPointModel> listAppPoint(String udid, Long uid, Date date) {

        StringBuilder sql = new StringBuilder();
        Object[] params = null;
        Date endDate = DateTimeUtil.getDateBeforeOrAfter(date, 1);
        endDate = DateUtils.truncate(endDate, Calendar.DAY_OF_MONTH);
        sql.append(" select * from tb_point where udid=? and start >= ? and start < ?");
        if (uid != null && uid >= 0) {
            sql.append(" and uid = ? ");
            params = new Object[] {
                udid, date, endDate, uid
            };
        } else {
            params = new Object[] {
                udid, date, endDate
            };
        }

        sql.append(" order by id asc ");

        return (List<AppPointModel>) jdbcTemplate.query(sql.toString(), params,
                new BeanPropertyRowMapper<AppPointModel>(AppPointModel.class));
    }
}
