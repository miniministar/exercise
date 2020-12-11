package com.exercise.framework.annotation.init.service;

import java.util.Map;

public abstract class IBeanInit {
    public abstract Class getAnnotationClass();

    public abstract Map<String, Object> init(Map<String, Object> ioc, Class<?> className) throws Exception ;

    public String toLowerFirstCase(String beanName) {
        char [] chars = beanName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
