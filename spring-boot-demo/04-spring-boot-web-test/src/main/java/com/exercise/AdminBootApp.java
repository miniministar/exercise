package com.exercise;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class AdminBootApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminBootApp.class,args);
    }
}
