package com.exercise.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext ac = new FileSystemXmlApplicationContext("G:\\workspace\\exercise\\maven-test-01\\tomcat7-run-01\\src\\main\\resources\\beans.xml");
        TestBean testBean1 = (TestBean) ac.getBean("testBean");
        TestBean testBean = (TestBean) applicationContext.getBean("testBean");
        testBean.printHello();
        testBean1.printHello();

    }

}
