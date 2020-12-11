package com.exercise.framework.annotation.init.service.impl;

import com.exercise.framework.annotation.Service;
import com.exercise.framework.annotation.init.service.IBeanInit;

import java.util.Map;

public class ServiceBeanInit extends IBeanInit {
    private Class annotation = Service.class;
    @Override
    public Class getAnnotationClass() {
        return this.annotation;
    }

    @Override
    public Map<String, Object> init(Map<String, Object> ioc, Class<?> clazz) throws Exception {
        Service service = clazz.getAnnotation(Service.class);
        String beanName = service.value();
        if("".equals(beanName)) beanName = super.toLowerFirstCase(clazz.getName() );
        Object instance = clazz.newInstance();
        ioc.put(beanName, instance);
        for (Class<?> i : clazz.getInterfaces()) {
            if(ioc.containsKey(i.getName())){
                throw new Exception("The \"" + i.getName() + "\" is exists!");
            }
            //把接口的类型直接当成key了
            ioc.put(i.getName(), instance);
        }
        return ioc;
    }
}
