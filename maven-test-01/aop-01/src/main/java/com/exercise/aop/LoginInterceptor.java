package com.exercise.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class LoginInterceptor {
    static Logger logger= LogManager.getLogger("Console");

    @Pointcut("@annotation(com.exercise.aop.MyLog)")
    private void around() {};

    @Around("around()")
    public Object mylog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("方法开始>>>>>>>");
        Object o = pjp.proceed();

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        Signature sig = pjp.getSignature();
        MethodSignature msign = null;
        if(!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        String method = sig.getName();
        msign = (MethodSignature) sig;
        Class[] parameterTypes = msign.getMethod().getParameterTypes();

        Object target = pjp.getTarget();
        Method m = target.getClass().getMethod(method, parameterTypes);
        MyLog myLog  = m.getAnnotation(MyLog.class);
        MyLog.Logtype logtype = myLog.type();

        String value = myLog.value();
        System.out.println("操作类型：" + logtype +  "，说明：" + value);
//        Logger log = LogManager.getLogger("mylog");
//        log.info(ip + ":" + "1111");
        logger.info("1111");
//        log.error(ip + ":" + "2222");
        return  o;
    }


    /**
     * 从request中获取请求方IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
