package com.exercise.aop;

import java.lang.annotation.*;

/**
 *
 * 自定义日志注解
 * Retention(RetentionPolicy.RUNTIME) 生命周期永远不会被丢弃
 * Target(ElementType.METHOD) 作用于方法上
 * Documented
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    // 描述
    String value() default "";

    //类型
    public enum Logtype {ADD,UPDATE,DEL,SET,LOGIN,LOGOUT,MESSAGE};

    Logtype type() default Logtype.ADD;
}