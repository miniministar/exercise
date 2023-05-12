package com.exercise;

import com.exercise.springboot.entity.Product;
import com.exercise.springboot.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SampleTest2 {


    @Autowired
    private IProductService service;

    @org.junit.Test
    public void test1() {
        List<Product> list1 = service.findAllProductDB1();
        list1.forEach(i->{
            log.info("{}", i.toString());
        });
        List<Product> list2 = service.findAllProductDB2();
        list2.forEach(i->{
            log.info("{}", i.toString());
        });
    }

    @org.junit.Test
    public void test2() {
        List<Product> list1 = service.findAllProductDB1_1();
        list1.forEach(i->{
            log.info("{}", i.toString());
        });
        List<Product> list2 = service.findAllProductDB2_2();
        list2.forEach(i->{
            log.info("{}", i.toString());
        });
    }

    @org.junit.Test
    public void test3() {
        Product byCache = service.getByCache(1);
        log.info("{}", byCache.toString());
        Product byCache1 = service.getByCache(1);
        log.info("{}", byCache1.toString());
    }
}

