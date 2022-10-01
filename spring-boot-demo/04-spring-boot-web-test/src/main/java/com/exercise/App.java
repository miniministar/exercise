package com.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：
 * @date ：Created in 2021/6/3 10:56
 */
@SpringBootApplication
@RestController("/")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("login")
    public String login(String username, String password) {
        return "username=" + username + ",password=" + password;
    }
}
