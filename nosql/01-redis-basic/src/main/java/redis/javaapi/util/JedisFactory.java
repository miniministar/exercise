package redis.javaapi.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisFactory {
    private static JedisPool pool = null;

    static {
        //类加载时，读取配置文件
        InputStream is = JedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ip = pro.getProperty("redis.host");
        int port = Integer.parseInt(pro.getProperty("redis.port"));
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setEvictorShutdownTimeoutMillis(Long.parseLong(pro.getProperty("redis.pool.timeBetweenEvictionRunsMillis"))) ;

        pool = new JedisPool(poolConfig, ip, port);
    }

    /**
     * 获取连接方法
     * @return
     */
    public static Jedis getJedis(){
        return pool.getResource();
    }

    /**
     * 关闭连接
     * @param jedis
     */
    public static void close(Jedis jedis) {
        if(jedis!=null){
            jedis.close();
        }
    }
}
