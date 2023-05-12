package com.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Test extends SpringBootServletInitializer {

    @Override  //这个表示使用外部的tomcat容器
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的启动类
        return builder.sources(Test.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Test.class,args);
    }
    
    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello world";
    }

    @Autowired
    private Person person;

    @RequestMapping(value = "/person")
    public Person person() {
        return person;
    }

}
