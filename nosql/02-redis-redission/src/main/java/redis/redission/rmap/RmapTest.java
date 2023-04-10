package redis.redission.rmap;

import cn.hutool.core.lang.Assert;
import com.pig4cloud.plugin.idempotent.exception.IdempotentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RFuture;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RmapTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void rmap() throws ExecutionException, InterruptedException {
        RMap<String, String> map = redissonClient.getMap("anyMap");
        //设置值,并返回先前的关联值，新key就返回null
        String prevObject = map.put("123","1");
        Assert.isTrue(prevObject == null, "Redis-key anyMap->123已存在");
        //判断hash是否存在，存在就不插入返回先前value.不存在返回null
        String currentObject = map.putIfAbsent("123", "3");
        Assert.isTrue("1".equals(currentObject), "Redis-key anyMap->123的值不为1");

        //移除key。返回先前值
        String obj = map.remove("123");
        Assert.isTrue("1".equals(obj), "Redis-key anyMap->123的值不为1");

        //比put快 true如果key是哈希中的新密钥，并且已设置值。 false如果密钥已经存在于哈希中并且值已更新
        boolean bl = map.fastPut("1234", "1");
        Assert.isTrue(bl, "Redis-key anyMap->1234不存在");

        //批量移除key，返回移除的数量
        long count = map.fastRemove("321");
        Assert.isTrue(0==count, "Redis-key anyMap->321存在");

        //异步 设置值,并返回先前的关联值，新key就返回null
        RFuture<String> putAsyncFuture = map.putAsync("321","1");
        //返回结果而不会阻塞。
        String putAsyncFutureStr =  putAsyncFuture.get();
        Assert.isTrue(putAsyncFutureStr==null, "Redis-key anyMap->321不存在");

        //异步 true如果key是哈希中的新密钥，并且已设置值。 false如果密钥已经存在于哈希中并且值已更新
        RFuture<Boolean> fastPutAsyncFuture = map.fastPutAsync("321","1");
        Boolean now = fastPutAsyncFuture.get();
        Assert.isTrue(now == false, "Redis-key anyMap->321不存在");
        //异步移除key，返回移除的数量
        RFuture<Long> count2 =  map.fastRemoveAsync("321");
        Long aLong = count2.get();
        Assert.isTrue(aLong == 1, "Redis-key anyMap->321不存在");
    }


    public static void main(String[] args) {

    }
}
