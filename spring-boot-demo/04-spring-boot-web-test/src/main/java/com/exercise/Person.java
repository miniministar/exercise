package com.exercise;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private int id;
    private String name;
    private List hobby;
    private String[] farmily;
    private Map map;
    private Pet pet;

    private class Pet {
        private String type;
        private String name;
    }
}
