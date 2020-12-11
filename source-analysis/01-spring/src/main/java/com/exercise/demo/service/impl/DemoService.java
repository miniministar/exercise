package com.exercise.demo.service.impl;

import com.exercise.demo.service.IDemoService;
import com.exercise.framework.annotation.Service;

@Service
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My name is " + name;
    }
}
