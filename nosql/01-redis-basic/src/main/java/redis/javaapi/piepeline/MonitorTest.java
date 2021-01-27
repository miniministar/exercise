package redis.javaapi.piepeline;

import com.google.common.util.concurrent.AtomicLongMap;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

import java.util.List;

public class MonitorTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //获取10万条命令
        long t1 = System.currentTimeMillis();
        jedis.monitor(new JedisMonitor() {
            @Override
            public void onCommand(String command) {
                System.out.println("#monitor: " + command);
                AtomicLongMap<String> ATOMIC_LONG_MAP = AtomicLongMap.create();
                // ATOMIC_LONG_MAP.incrementAndGet(command);
            }
        });
        System.out.println("monitor获取10万条命令耗时："+(System.currentTimeMillis() - t1));
    }
}
