package com.exercise.framework.annotation.init;

import com.exercise.framework.annotation.init.service.IBeanInit;
import com.exercise.framework.annotation.init.service.impl.ControllerBeanInit;
import com.exercise.framework.annotation.init.service.impl.ServiceBeanInit;

import java.util.HashMap;
import java.util.Map;

public class BeanInitStrategy {
    public static final String AUTOWIRED = "Autowired";
    public static final String CONTROLLER = "Controller";
    public static final String QUERYPARAM = "QueryParam";
    public static final String REQUESTMAPPING = "RequestMapping";
    public static final String SERVICE = "Service";

    private static Map<String, IBeanInit> annotationStrategy = new HashMap<>();

    static {
        annotationStrategy.put(SERVICE, new ServiceBeanInit());
        annotationStrategy.put(CONTROLLER, new ControllerBeanInit());
    }

    public static Object initBean(Map<String, Object> ioc, String className) throws Exception {
        Class<?> clazz = null;
        clazz = Class.forName(className);
        for (IBeanInit beanInit : annotationStrategy.values()) {
            if( clazz.isAnnotationPresent(beanInit.getAnnotationClass()) ) {
                ioc =  beanInit.init(ioc, clazz);
                break;
            }
        }
        return ioc;
    }

}
