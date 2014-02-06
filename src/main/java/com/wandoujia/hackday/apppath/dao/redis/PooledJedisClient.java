package com.wandoujia.hackday.apppath.dao.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PooledJedisClient {
    
    @Autowired
    private JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String set(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.set(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String get(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long del(String[] keys) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.del(keys);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> mget(String[] keys) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.mget(keys);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String mset(String[] keysvalues) {
        if(keysvalues.length % 2 != 0) {
            throw new IllegalArgumentException("keysvalues argument should be a string array with even number of elements");
        }

        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            for(int i=0;i<keysvalues.length;i+=2) {
                jedis.set(keysvalues[i], keysvalues[i+1]);
            }
            return "OK";
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Boolean exists(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.exists(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String type(String s) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.type(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long expire(String s, int i) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.expire(s, i);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long expireAt(String s, long l) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.expireAt(s, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long ttl(String s) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.ttl(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Boolean setbit(String s, long l, boolean b) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.setbit(s, l, b);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Boolean getbit(String s, long l) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.getbit(s, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long setrange(String s, long l, String s2) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.setrange(s, l, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String getrange(String s, long l, long l2) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.getrange(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String getSet(String s, String s2) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.getSet(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long setnx(String s, String s2) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.setnx(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String setex(String s, int i, String s2) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.setex(s, i ,s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long decrBy(String s, long l) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.decrBy(s, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long decr(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.decr(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long incrBy(String s, long l) {
        Jedis jedis = null; 
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.incrBy(s, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long incr(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.incr(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long append(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.append(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String substr(String s, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.substr(s, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long hset(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hset(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String hget(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hget(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long hsetnx(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hsetnx(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String hmset(String s, Map<String, String> stringStringMap) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hmset(s, stringStringMap);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> hmget(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hmget(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long hincrBy(String s, String s2, long l) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hincrBy(s, s2, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Boolean hexists(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hexists(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long hdel(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hdel(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long hlen(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hlen(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> hkeys(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hkeys(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> hvals(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hvals(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Map<String, String> hgetAll(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.hgetAll(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long rpush(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.rpush(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long lpush(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lpush(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long llen(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.llen(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> lrange(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lrange(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String ltrim(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.ltrim(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String lindex(String s, long l) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lindex(s, l);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String lset(String s, long l, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lset(s, l, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long lrem(String s, long l, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lrem(s, l, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String lpop(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lpop(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String rpop(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.rpop(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long sadd(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.sadd(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> smembers(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.smembers(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long srem(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.srem(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String spop(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.spop(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long scard(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.scard(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Boolean sismember(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.sismember(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public String srandmember(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.srandmember(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zadd(String s, double v, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zadd(s, v, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zadd(String s, Map<Double, String> doubleStringMap) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zadd(s, doubleStringMap);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrange(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrange(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zrem(String s, String... strings) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrem(s, strings);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Double zincrby(String s, double v, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zincrby(s, v, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zrank(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrank(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zrevrank(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrank(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrevrange(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrange(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrangeWithScores(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeWithScores(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrevrangeWithScores(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeWithScores(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zcard(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zcard(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Double zscore(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zscore(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> sort(String s) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.sort(s);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public List<String> sort(String s, SortingParams sortingParams) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.sort(s, sortingParams);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zcount(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zcount(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zcount(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zcount(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrangeByScore(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScore(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrangeByScore(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScore(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrevrangeByScore(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrangeByScore(String s, double v, double v2, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScore(s, v, v2, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrevrangeByScore(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrangeByScore(String s, String s2, String s3, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScore(s, s2, s3, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrevrangeByScore(String s, double v, double v2, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(s, v, v2, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScoreWithScores(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScoreWithScores(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v2, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScoreWithScores(s, v, v2, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<String> zrevrangeByScore(String s, String s2, String s3, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(s, s2, s3, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScoreWithScores(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScoreWithScores(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, String s2, String s3, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrangeByScoreWithScores(s, s2, s3, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v2, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScoreWithScores(s, v, v2, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s2, String s3, int i, int i2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScoreWithScores(s, s2, s3, i, i2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zremrangeByRank(String s, long l, long l2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zremrangeByRank(s, l, l2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zremrangeByScore(String s, double v, double v2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zremrangeByScore(s, v, v2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long zremrangeByScore(String s, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.zremrangeByScore(s, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long linsert(String s, BinaryClient.LIST_POSITION list_position, String s2, String s3) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.linsert(s, list_position, s2, s3);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long lpushx(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.lpushx(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long rpushx(String s, String s2) {
        Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try{
            jedis = jedisPool.getResource();
            return jedis.rpushx(s, s2);
        } catch (JedisConnectionException e) {
            borrowOrOprSuccess = false;
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            return null;
        } finally {
            if(null != jedis && borrowOrOprSuccess) {
                jedisPool.returnResource(jedis);
            }
        }
    }
}
