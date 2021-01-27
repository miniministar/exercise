package redis.redission.cluster;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class ClusterTest {
    public static void main(String[] args) {

        //创建配置
        Config config = new Config();
        config.setCodec(new org.redisson.client.codec.StringCodec());

        //指定使用集群部署方式
        config.useClusterServers()
                // 集群状态扫描间隔时间，单位是毫秒
                .setScanInterval(2000)
                //cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
                .addNodeAddress("redis://localhost:6379")
                .addNodeAddress("redis://localhost:6380")
                .addNodeAddress("redis://localhost:6381")
                .addNodeAddress("redis://localhost:7379")
                .addNodeAddress("redis://localhost:7380")
                .addNodeAddress("redis://localhost:7381");

        RedissonClient redisson = Redisson.create(config);

        //首先获取redis中的key-value对象，key不存在没关系
        RBucket<String> keyObject = redisson.getBucket("redisson-cluster-key");
        //如果key存在，就设置key的值为新值value
        //如果key不存在，就设置key的值为value
        keyObject.set("value");

        //最后关闭RedissonClient
        redisson.shutdown();
    }
}
