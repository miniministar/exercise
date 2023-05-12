package com.exercise;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@EnableRegisterServer
@MapperScan("com.exercise.springboot.mapper")
//@ComponentScan(basePackages = {"com.exercise"})
@EnableCaching
public class Test {

    public static void main(String[] args) {
        SpringApplication.run(Test.class,args);
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world";
    }

    @Autowired
    private Person person;
    @Autowired
    private SimpleBean simpleBean;

    @RequestMapping(value = "/person")
    public Person person() {
        return person;
    }

    @RequestMapping(value = "/simplebean")
    public SimpleBean simplebean() {
        return simpleBean;
    }

    @Cacheable(cacheNames = {"cacheTest"}, key = "#id", condition = "#id>0")
    @RequestMapping(value = "/getUserById")
    public String getUserById(Integer id) {
        return id +"_" + DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN);
    }

    @CachePut(cacheNames = {"person"}, key = "#person.id", condition = "#person.id>0")
    @RequestMapping(value = "/updateUser")
    public String updateUser(Person person) {
        return JSON.toJSONString(person) +"_" + DateUtil.format(new Date(), DatePattern.CHINESE_DATE_TIME_PATTERN);
    }
}
