package redis.redission.spring;

import com.pig4cloud.plugin.idempotent.exception.IdempotentException;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class RepeatSubmitService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedissonClient redissonClient;

    public String add() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public void repeatCheck(String s) {
        String key = "repeatSubmitKey";
        RMapCache<String, Object> rMapCache = redissonClient.getMapCache("idempotent");
        String value = LocalDateTime.now().toString().replace("T", " ");
        Integer expireTime = 10;
        if (null != rMapCache.get(key)) {
            throw new IdempotentException("重复提交");
        } else {
            synchronized(s) {
                Object v1 = rMapCache.putIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
                if (null != v1) {
                    throw new IdempotentException("重复提交");
                }
            }
        }
    }
}
