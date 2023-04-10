package redis.redission.spring;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class IdGenerator {

    @Resource
    private RedissonClient redissonClient;


    public String gen(String prefix) {
        try {
            Thread.sleep(500);
            //通过redis的自增获取序号
            RAtomicLong atomicLong = redissonClient.getAtomicLong(prefix);
            Thread.sleep(500);
            //设置过期时间
            atomicLong.expire(2, TimeUnit.DAYS);
            Thread.sleep(500);
            long id = atomicLong.incrementAndGet();
            Thread.sleep(500);
            return prefix + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + String.format("%06d", id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
