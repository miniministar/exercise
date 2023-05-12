package com.exercise;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
@Data
@ToString
public class Person {
    private int id;
    private String name;
    private List hobby;
    private String[] farmily;
    private Map map;
    @Autowired
    private Pet pet;
}
