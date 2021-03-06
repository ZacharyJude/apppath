package com.wandoujia.hackday.apppath.dao.mysql;

import com.wandoujia.hackday.apppath.dao.PointDao;
import com.wandoujia.hackday.apppath.dao.mysql.table.TablePoint;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class PointDaoMysqlImpl implements PointDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("apppathJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void add(Collection<AddPointArgModel> args) {
        String insertingFields[] = new String[] {
            TablePoint.UID,
            TablePoint.UDID,
            TablePoint.PACKAGE_NAME,
            TablePoint.DATA,
            TablePoint.START,
            TablePoint.DURATION,
            TablePoint.LONGITUDE,
            TablePoint.LATITUDE
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
            sqlParams.add(a.data);
            sqlParams.add(a.start);
            sqlParams.add(a.duration);
            sqlParams.add(a.longitude);
            sqlParams.add(a.latitude);
        }
    
        jdbcTemplate.update(sb.toString(), sqlParams.toArray());
    }
}
