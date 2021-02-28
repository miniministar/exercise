package com.exercise.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements Comparable<User> {
    private int age;
    private String name;

    @Override
    public int compareTo(User user) {
        int i = this.age - user.getAge();
        if(i==0 ){
            i = this.name.compareTo(user.getName());
        }
        return i;
    }

    public int add(User user1, User user2) {
        return user1.getAge() + user2.getAge();
    }
}
