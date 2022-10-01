package com.exercise.jdk8;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class User {
    private String name;
    private Integer age;

    public void print() {
        System.out.println("name=" + this.name +", age=" + this.age );
    }

}
