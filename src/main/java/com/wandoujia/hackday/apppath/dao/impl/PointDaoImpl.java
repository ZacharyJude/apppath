package com.wandoujia.hackday.apppath.dao.impl;

import java.util.Collection;

import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.dao.mysql.PointDaoMysqlImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.apache.commons.lang.StringUtils;

public class PointDaoImpl implements PointDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PointDaoMysqlImpl pointDaoMysqlImpl;

    @Override
    public void add(Collection<AddPointArgModel> args) {
        pointDaoMysqlImpl.add(args);
    }
}
