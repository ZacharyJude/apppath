package com.wandoujia.hackday.apppath.dao.mysql;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.dao.mysql.table.TablePoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.apache.commons.lang.StringUtils;

public class PointDaoMysqlImpl implements PointDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void add(Collection<AddPointArgModel> args) {
        String insertingFields[] = new String[] {
            TablePoint.UID,
            TablePoint.UDID,
            TablePoint.PACKAGE_NAME,
            TablePoint.START,
            TablePoint.DURATION
        };

        List<Object> sqlParams = new ArrayList<Object>();
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO %s (%s) VALUES ", TablePoint.TABLE_NAME, StringUtils.join(insertingFields, ",")));
        sb.append(
            StringUtils.repeat(
                String.format("(%s)", StringUtils.repeat("?", ",", insertingFields.length)),
                ",",
                args.size()
            )
        );

        for(AddPointArgModel a : args) {
            sqlParams.add(a.uid);
            sqlParams.add(a.udid);
            sqlParams.add(a.packageName);
            sqlParams.add(a.start);
            sqlParams.add(a.duration);
        }
    
        jdbcTemplate.update(sb.toString(), sqlParams.toArray());
    }
}
