package com.exercise.compare;

import com.exercise.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompareTest {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setAge(20);
        user1.setName("user1");

        User user2 = new User();
        user2.setAge(21);
        user2.setName("user2");

        User user3 = new User();
        user3.setAge(21);
        user3.setName("user3");

        List<User> list = new ArrayList<>();
        list.add(user2);
        list.add(user3);
        list.add(user1);

        System.out.println("================原始数据====================");
        System.out.println(list);
        Collections.sort(list);
        System.out.println("================comparable 按age升序排列====================");
        System.out.println(list);

        User user4 = new User();
        user4.setAge(21);
        user4.setName("user");
        list.add(user4);

        List<User> collect = list.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        System.out.println("================Comparator 按name升序排列====================");
        System.out.println(collect);

        List<User> collect2 = list.stream().sorted( (u1, u2) -> {
           return u2.getName().compareTo(u1.getName()) ;
                }
        ).collect(Collectors.toList());
        System.out.println("================Comparator 按name降序排列====================");
        System.out.println(collect2);


    }
}
