package com.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("provider")
public interface ProviderFeignClient {

    @GetMapping("/dept/nacos/{id}")
    String getDept(@PathVariable("id") Integer id);
}
