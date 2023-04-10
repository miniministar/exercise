package com.consumer;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RefreshScope
public class Provider02 {

    public static void main(String[] args) {
        SpringApplication.run(Provider02.class, args);
    }

    @Value("${server.port}")
    private String serverPort;
    @Value("${nacos.id}")
    private String nacosId;

    @GetMapping(value = "/dept/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return DateUtil.formatDateTime(new Date()) +  ", 端口号： " + serverPort + "，nacos配置参数：" + nacosId + "， 传入的参数：" + id;
    }
}
