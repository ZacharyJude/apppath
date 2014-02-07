package com.wandoujia.hackday.apppath.dao.mysql.rowmapper;

import com.wandoujia.hackday.apppath.model.PointModel;
import com.wandoujia.hackday.apppath.dao.mysql.table.TablePoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointRowMapper implements RowMapper<PointModel> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PointModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        PointModel model = new PointModel();
        model.setUid(rs.getLong(TablePoint.UID));
        model.setUdid(rs.getString(TablePoint.UDID));
        model.setPackageName(rs.getString(TablePoint.PACKAGE_NAME));
        model.setStart(rs.getDate(TablePoint.START));
        model.setDuration(Long.valueOf(rs.getLong(TablePoint.DURATION)));
        model.setData(rs.getString(TablePoint.DATA));
        model.setLongitude(Double.valueOf(rs.getDouble(TablePoint.LONGITUDE)));
        model.setLatitude(Double.valueOf(rs.getDouble(TablePoint.LATITUDE)));
        return model;
    }
}
