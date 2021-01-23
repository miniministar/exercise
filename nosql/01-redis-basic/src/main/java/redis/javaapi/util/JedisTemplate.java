package redis.javaapi.util;

import redis.clients.jedis.Jedis;

import static redis.javaapi.util.JedisFactory.close;

public abstract class JedisTemplate {

    public <T> T execute(String methodName, Object[] args) {
        Jedis jedis = getJedic();
        T t = doExecute(jedis, methodName, args);
        close(jedis);
        return t;
    }

    protected abstract <T> T doExecute(Jedis jedis, String methodName, Object[] args);

    protected Jedis getJedic(){
        return JedisFactory.getJedis();
    }
}
