package redis.javaapi.datatype;

import redis.javaapi.util.JedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class StringTest {
    public static void main(String[] args) {
        //字符串
        JedisUtil.getJedisUtil1().set("字符串", "abc");
        String str = JedisUtil.getJedisUtil1().get("字符串");
        System.out.println("字符串:" + str);

        //zset
        JedisUtil.getJedisUtil1().zadd("myzset", 20d, "java");
        JedisUtil.getJedisUtil1().zadd("myzset", 30d, "python");
        JedisUtil.getJedisUtil1().zadd("myzset", 90d, "ruby");
        JedisUtil.getJedisUtil1().zadd("myzset", 40d, "erlang");
        JedisUtil.getJedisUtil1().zadd("myzset", 70d, "cpp");
        JedisUtil.getJedisUtil1().zadd("myzset", 50d, "android");
        System.out.println("zset:");
        Set<String> myzset = JedisUtil.getJedisUtil1().zrangeByScore("myzset", 100, 10);
        System.out.println("zset:" + myzset);

        //hash
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "fieldA");
        hashMap.put("b", "fieldB");
        JedisUtil.getJedisUtil1().hmset("h1", hashMap);
        String h1 = JedisUtil.getJedisUtil1().hget("h1", "a");
        System.out.println(h1);

        List<String> list =new ArrayList<>();
        list = JedisUtil.getJedisUtil1().hmget("h1", "a", "b", "c");
        System.out.println(list);
    }
}
