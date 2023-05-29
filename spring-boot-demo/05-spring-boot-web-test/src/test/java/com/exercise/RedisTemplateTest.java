package com.exercise;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @org.junit.Test
    public void testString() {
        String key = "test:key:string";
        String value = "test:value:string";
        redisTemplate.opsForValue().set(key, value);
        assertEquals(value, redisTemplate.opsForValue().get(key));
    }

    @org.junit.Test
    public void testHash() {
        String key = "test:key:hash";
        Map<String, Object> value = new HashMap<>();
        value.put("field1", "value1");
        value.put("field2", "value2");
        redisTemplate.opsForHash().putAll(key, value);
        assertEquals(value, redisTemplate.opsForHash().entries(key));
        redisTemplate.opsForHash().delete(key, "field2");
        assertEquals(1L, redisTemplate.opsForHash().size(key).longValue());
    }

    @org.junit.Test
    public void testList() {
        String key = "test:key:list";
        List<Object> value = new ArrayList<>();
        value.add("value1");
        value.add("value2");
        redisTemplate.opsForList().rightPushAll(key, value);
        assertEquals(value, redisTemplate.opsForList().range(key, 0, -1));
        redisTemplate.opsForList().remove(key, 1, "value1");
        assertEquals("value2", redisTemplate.opsForList().index(key, 0));
    }

    @org.junit.Test
    public void testSet() {
        String key = "test:key:set";
        Set<Object> value = new HashSet<>();
        value.add("value1");
        value.add("value2");
        redisTemplate.opsForSet().add(key, value.toArray());
        assertEquals(value, redisTemplate.opsForSet().members(key));
        redisTemplate.opsForSet().remove(key, "value1");
        assertEquals(1L, redisTemplate.opsForSet().size(key).longValue());
    }

    @org.junit.Test
    public void testZset() {
        String key = "test:key:zset";
        Set<ZSetOperations.TypedTuple<Object>> value = new HashSet<>();
        value.add(new DefaultTypedTuple<Object>("value1", 1.0));
        value.add(new DefaultTypedTuple<Object>("value2", 2.0));
        redisTemplate.opsForZSet().add(key, value);
        assertEquals(value, redisTemplate.opsForZSet().rangeWithScores(key, 0, -1));
        redisTemplate.opsForZSet().remove(key, "value1");
        assertEquals(1L, redisTemplate.opsForZSet().size(key).longValue());
    }
}
