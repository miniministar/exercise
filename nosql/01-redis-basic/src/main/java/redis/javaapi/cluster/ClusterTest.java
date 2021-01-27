package redis.javaapi.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class ClusterTest {
    public static void main(String[] args) {
        // 不管是连主备，还是连几台机器都是一样的效果
        HostAndPort server1 = new HostAndPort("localhost", 6379);
        HostAndPort server2 = new HostAndPort("localhost", 6380);
        HostAndPort server3 = new HostAndPort("localhost", 6381);
        HostAndPort server4 = new HostAndPort("localhost", 7379);
        HostAndPort server5 = new HostAndPort("localhost", 7380);
        HostAndPort server6 = new HostAndPort("localhost", 7381);

        Set nodes = new HashSet<HostAndPort>();
        nodes.add(server1);
        nodes.add(server2);
        nodes.add(server3);
        nodes.add(server4);
        nodes.add(server5);
        nodes.add(server6);

        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("cluster:key:test:1", "value111");
        String str = cluster.get("cluster:key:test:1");
        System.out.println(str);
    }
}
