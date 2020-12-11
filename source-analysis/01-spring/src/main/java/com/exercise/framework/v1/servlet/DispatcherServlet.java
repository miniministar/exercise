package com.exercise.framework.v1.servlet;

import com.exercise.framework.annotation.Autowired;
import com.exercise.framework.annotation.Controller;
import com.exercise.framework.annotation.RequestMapping;
import com.exercise.framework.annotation.Service;
import com.sun.corba.se.spi.activation.Server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DispatcherServlet extends HttpServlet {

    private Map<String, Object> ioc = new HashMap<>();

    private Map<String, Object> mapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        }catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        uri = uri.replace(contextPath, "").replaceAll("/+", "/");
        if(!this.mapping.containsKey(uri)) {
            resp.getWriter().write("404 not found");
            return;
        }
        Method method = (Method)this.mapping.get(uri);
        Map<String, String[]> params = req.getParameterMap();
        method.invoke(this.ioc.get(method.getDeclaringClass().getName()), new Object[ ]{ req, resp, params.get("name")[0] } );


    }

    //init方法肯定干得的初始化的工作
    //inti首先我得初始化所有的相关的类，IOC容器、servletBean
    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream is = null;
        try {
            Properties configContext = new Properties();
            is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation").replace("classpath:", "").trim());
            configContext.load(is);
            String scanPackage = configContext.getProperty("scanPackage");

            doScanner(scanPackage);

            initBeans();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void initBeans() throws IllegalAccessException {

        for (Object obj : ioc.values()) {
            if(obj == null) continue;
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(Autowired.class))continue;
                Autowired autowired = field.getAnnotation(Autowired.class);
                String beanName = autowired.value();
                if("".equals(beanName)) beanName = field.getType().getName();
                field.setAccessible(true);

                field.set(obj, ioc.get(beanName));

            }

        }

    }

    private void doScanner(String scanPackage) throws Exception {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if(file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")) continue;
                String clazzName = scanPackage + "." + file.getName().replace(".class", "");

                initAnnotationClass(clazzName);
            }
        }
    }

    private void initAnnotationClass(String clazzName) throws Exception {
        Class<?> clazz = Class.forName(clazzName);
        if(clazz.isAnnotationPresent(Controller.class)) {
            ioc.put(clazzName, clazz.newInstance());
            getMapping(clazz);
        }else if(clazz.isAnnotationPresent(Service.class)) {
            Service service = clazz.getAnnotation(Service.class);
            String beanName = service.value();
            if("".equals(beanName)) beanName = clazz.getName();
            Object instance = clazz.newInstance();
            ioc.put(beanName, instance);
            for (Class<?> i : clazz.getInterfaces()) {
                ioc.put(i.getName(), instance);
            }
        }
    }

    private void getMapping(Class<?> clazz) {
        String baseUrl = "";
        if(clazz.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            baseUrl = requestMapping.value();
        }
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(!method.isAnnotationPresent(RequestMapping.class)) continue;
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
            this.mapping.put(url, method);
            System.out.println("Mapped " + url + "," + method);
        }
    }
}
