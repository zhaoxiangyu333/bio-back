package cn.blz.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis = null;

    //添加内容到某个库
    public void set(int dbNum, String key, String value) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        jedis.set(key, value);
        close(jedis);
    }

    //获取
    public String get(int dbNum, String key) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        String value = jedis.get(key);
        close(jedis);
        return value;
    }

    //查看某个键是否存在
    public boolean exists(int dbNum, String key) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        Boolean exists = jedis.exists(key);
        close(jedis);
        return exists;
    }

    //查看超时时间
    public Long ttl(int dbNum, String key) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        Long ttl = jedis.ttl(key);
        close(jedis);
        return ttl;
    }

    //删除
    public void del(int dbNum, String key) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        jedis.del(key);
        close(jedis);
    }

    //追加
    public void append(int dbNum, String key, String value) {
        jedis = this.jedisPool.getResource();
        jedis.select(dbNum);
        jedis.append(key, value);
        close(jedis);
    }

    //归还连接
    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
