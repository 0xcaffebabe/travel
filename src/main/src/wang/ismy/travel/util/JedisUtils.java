package wang.ismy.travel.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtils {

    private static final JedisPool POOL = new JedisPool();

    public static Jedis get(){
        return POOL.getResource();
    }
}
