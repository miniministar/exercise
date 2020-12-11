package com.exercise.framework.annotation.init.service.impl;

import com.exercise.framework.annotation.Controller;
import com.exercise.framework.annotation.init.service.IBeanInit;

import java.util.Map;

public class ControllerBeanInit extends IBeanInit {
    private Class annotation = Controller.class;

    @Override
    public Class getAnnotationClass() {
        return this.annotation;
    }

    @Override
    public Map<String, Object> init(Map<String, Object> ioc, Class<?> className) throws Exception {
        Object instance = className.newInstance();
        //Spring默认类名首字母小写
        String beanName = super.toLowerFirstCase(className.getSimpleName());
        ioc.put(beanName,instance);
        return ioc;
    }
}
