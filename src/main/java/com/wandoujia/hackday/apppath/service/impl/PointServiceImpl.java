package com.wandoujia.hackday.apppath.service.impl;

import com.wandoujia.hackday.apppath.service.PointService;
import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.protocol.OperationStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.apache.commons.lang.StringUtils;

public class PointServiceImpl implements PointService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("pointDaoImpl")
    private PointDao pointDao;

    @Override
    public OperationStatus addUdidPoints(AddUdidPointArgModel arg) {
        pointDao.add(arg.getPoints());
        return OperationStatus.SUCCESS;
    }
}
