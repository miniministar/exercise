package com.exercise.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestBeanTest {
    private ApplicationContext applicationContext;
    private static ApplicationContext ac;

    @BeforeClass
    public static void setClass() {
        ac = new FileSystemXmlApplicationContext("G:\\workspace\\exercise\\maven-test-01\\tomcat7-run-01\\src\\main\\resources\\beans.xml");
        System.out.println("BeforeClass 注解");
    }

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("before 注解");
    }

    @Test
    public void printHello() {
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        TestBean testBean1 = (TestBean) ac.getBean("testBean");
        testBean.printHello();
        testBean1.printHello();
    }

    @Test
    public void printHello1() {
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        testBean.printHello();
        TestBean testBean1 = (TestBean) ac.getBean("testBean");
        testBean1.printHello();
    }
}