package com.wandoujia.hackday.apppath.dao;

import com.wandoujia.hackday.apppath.model.AppPointModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Autowired
    @Qualifier("adsJdbcTemplate")
    private JdbcTemplate adsJdbcTemplate;

    @Override
    public List<AppPointModel> listAppPoints(String udid, Long uid, Date start, Date end) {

        StringBuilder sql = new StringBuilder();
        Object[] params = null;

        sql.append(" select * from tb_point where udid=? and start >= ? and start < ?");
        if (uid != null && uid >= 0) {
            sql.append(" and uid = ? ");
            params = new Object[] {
                udid, start, end, uid
            };
        } else {
            params = new Object[] {
                udid, start, end
            };
        }

        sql.append(" order by id asc ");

        return (List<AppPointModel>) jdbcTemplate.query(sql.toString(), params,
                new BeanPropertyRowMapper<AppPointModel>(AppPointModel.class));
    }

    @Override
    public BigDecimal sumFreedownload(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(traffic) from FreeDownload where uid = ? and creation >= ? and creation < ?");
        return adsJdbcTemplate.queryForObject(sql.toString(), new Object[] {
            uid, start, end
        }, BigDecimal.class);
    }

    @Override
    public Long countPayOrders(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from PayOrder p,PayAccount ac  where p.payaccount_id = ac.id and ac.uid = ? and p.creation >= ? and p.creation < ?");
        return adsJdbcTemplate.queryForLong(sql.toString(), new Object[] {
            uid, start, end
        });
    }

    @Override
    public BigDecimal sumPayOrders(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(p.money) from PayOrder p,PayAccount ac  where p.payaccount_id = ac.id and ac.uid = ? and p.creation >= ? and p.creation < ?");
        BigDecimal money = adsJdbcTemplate.queryForObject(sql.toString(), new Object[] {
            uid, start, end
        }, BigDecimal.class);

        if (money == null) {
            return BigDecimal.ZERO;
        }
        return money.divide(new BigDecimal("100"), 0);
    }

    @Override
    public Long sumFreeAmount(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(amount) from FreeExchangeRecordV2 where uid = ? and creation >= ? and creation < ?");
        return adsJdbcTemplate.queryForLong(sql.toString(), new Object[] {
            uid, start, end
        });

    }

    @Override
    public List<AppPointModel> listFreeTraffics(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select pn as package_name,traffic,creation as start from FreeDownload where uid = ? and creation >= ? and creation < ?");

        return (List<AppPointModel>) adsJdbcTemplate.query(sql.toString(), new Object[] {
            uid, start, end
        }, new BeanPropertyRowMapper<AppPointModel>(AppPointModel.class));
    }

    @Override
    public List<AppPointModel> listPayOrders(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select money as amount,p.creation as start,app.packagename as package_name");
        sql.append(" from PayOrder p,PayAccount ac,AppKey app ");
        sql.append(" where p.appkey_id = app.id and ac.id = p.payaccount_id and ac.uid = ? and p.creation >= ? and p.creation < ?");

        return (List<AppPointModel>) adsJdbcTemplate.query(sql.toString(), new Object[] {
            uid, start, end
        }, new BeanPropertyRowMapper<AppPointModel>(AppPointModel.class));
    }

    @Override
    public List<AppPointModel> listFreeAmounts(Long uid, Date start, Date end) {
        StringBuilder sql = new StringBuilder();
        sql.append("select amount,creation as start from FreeExchangeRecordV2 where uid = ? and creation >= ? and creation < ?");

        return (List<AppPointModel>) adsJdbcTemplate.query(sql.toString(), new Object[] {
            uid, start, end
        }, new BeanPropertyRowMapper<AppPointModel>(AppPointModel.class));
    }

}
