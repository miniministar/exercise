package com.exercise.reflex;

import com.exercise.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflexBasicApiTest {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("user1");
        user.setAge(20);

        User user0 = new User();
        user0.setName("user2");
        user0.setAge(22);

        //获取class对象
        Class<? extends User> clazz = user.getClass();
        Class<User> clazz2 = User.class;
        Class clazz3 = Class.forName("com.exercise.entity.User");

        //通过class对象获取 类的实例
        User user1 = clazz.newInstance();
        User user2 = clazz2.newInstance();
        User user3 = (User) clazz3.newInstance();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);

        //基本数据类型和包装数据类型获取class
        Class<Integer> integerClass = int.class;
        Class<Integer> type = Integer.TYPE;

        //通过反射获取 对象方法
        Method method1 = clazz.getMethod("getAge");
        Method method2 = clazz.getMethod("compareTo", User.class);
        Method add = clazz.getMethod("add", new Class[]{User.class, User.class});
        Object age = method1.invoke(user);
        Object compareInt = method2.invoke(user, user0);
        Object addInt = add.invoke(user, new Object[]{user, user0});
        System.out.println(age);
        System.out.println(compareInt);
        System.out.println(addInt);

        //获取属性
        Field filed = clazz.getDeclaredField("name");
        filed.setAccessible(true);
        filed.set(user1, "user1");
        System.out.println(filed.get(user1));
    }
}
