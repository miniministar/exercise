package com.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class ServiceA {
    public static void main(String[] args) {
        SpringApplication.run(ServiceA.class, args);
    }

    @RequestMapping("login")
    public String login(String username, String password) {
        return "username=" + username + ",password=" + password;
    }
}
