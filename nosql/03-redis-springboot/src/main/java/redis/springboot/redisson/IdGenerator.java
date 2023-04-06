package redis.springboot.redisson;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class IdGenerator {

    private static RedissonClient redissonClient;
    private static final ConcurrentHashMap map = new ConcurrentHashMap();
    static int num = 100;
    static CountDownLatch countDownLatch = new CountDownLatch(num);


    static {
        Config config=new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient= Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        final String lockKey = "CS";
        for (int i = 0; i < num; i++) {
            final int index = i;
            Thread.sleep(50 );
            new Thread(()->{
                try {
                    Thread.sleep(1000  * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String Id = gen(lockKey);
                System.out.println("thread-" + index + ", Id:" + Id);
                map.put(Id, 1);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(map.keySet().size());

        Object collect = map.keySet().stream().sorted().collect(Collectors.toList());
        System.out.println(collect.toString());
    }

    public static String gen(String prefix) {
        //通过redis的自增获取序号
        RAtomicLong atomicLong = redissonClient.getAtomicLong(prefix);
        //设置过期时间
        atomicLong.expire(2, TimeUnit.DAYS);
        long id = atomicLong.incrementAndGet();
        return prefix + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + String.format("%06d", id);
    }
}
