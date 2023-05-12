package com.exercise;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoutingAspect {
    @Around("@annotation(routingWith)")
    public Object routingWithDataSource(ProceedingJoinPoint proceedingJoinPoint,
                                        RoutingWith routingWith) throws Throwable {
        String key = routingWith.value();
        RoutingDataSourceContext routingDataSourceContext = new RoutingDataSourceContext(key);
        //设置数据源，原方法继续执行
        return proceedingJoinPoint.proceed();
    }
}
