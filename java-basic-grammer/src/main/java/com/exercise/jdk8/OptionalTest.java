package com.exercise.jdk8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class OptionalTest {
    public static void main(String[] args) {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            User user = User.builder().name("用户" + i).age(random.nextInt(80) + 20 ).build();
            if(i % 3 == 0) {
                user.setAge(null);
            }
            users.add(user);
        }
        User user1 = User.builder().name("用户" + 10).age( -1 ).build();
        users.add(user1);
        User user2 = User.builder().name("用户" + 10).age( -2 ).build();
        users.add(user2);

        User user3 = null;
        Optional<User> optionalUser = Optional.ofNullable(user3);
        // ifPresent() 执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式
        optionalUser.ifPresent(u -> assertTrue(u.getAge() > 10));
        // get() 获取值会抛异常
        try {
            User user = optionalUser.get();
            System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回默认值,若user3为null返回user1
        User user = Optional.ofNullable(user3).orElse(user1);
        assertEquals(user.getName(), "用户10");
        //当user1不为null时，orElse会执行构造用户方法，orElseGet则不会执行
        Optional.ofNullable(user1).orElse(createUser("orElse"));
        Optional.ofNullable(user1).orElseGet(()->createUser("orElseGet"));
        try {
            Optional.ofNullable(user3).orElseThrow(()->new RuntimeException("用户为空了"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        //map 对返回值包装在Optional中，可对返回的Optional进行操作
        Optional<String> s = Optional.ofNullable(user3).map(u -> u.getName());
        String username = s.orElse("默认用户名");
        System.out.println(username);
        Optional.ofNullable(user3).flatMap(u->u.getName());


        System.out.println("this process is end.");
    }

    private static User createUser(String msg) {
        log.info("{}-创建默认用户", msg);
        return User.builder().name("默认用户").age(18).build();
    }
}
