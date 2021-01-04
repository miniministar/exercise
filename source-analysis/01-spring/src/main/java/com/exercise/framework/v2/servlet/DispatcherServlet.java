package com.exercise.framework.v2.servlet;

import com.exercise.framework.annotation.Autowired;
import com.exercise.framework.annotation.QueryParam;
import com.exercise.framework.annotation.RequestMapping;
import com.exercise.framework.annotation.init.BeanInitStrategy;
import com.exercise.framework.convert.ConvertStrategy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<String>();

    private Map<String, Object> ioc = new HashMap<>();

    private List<Handler> handlerMapping = new ArrayList<>();

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
        Handler handler = getHandler(req);
        if(handler == null) {
            resp.getWriter().write("404 not found");
            return;
        }

        Class<?>[] paramTypes = handler.getParamTypes();
        Object[] paramValues = new Object[paramTypes.length];
        Map<String, String[]> params = req.getParameterMap();

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
            if(!handler.paramIndexMapping.containsKey(entry.getKey())) continue;

            Integer index = handler.paramIndexMapping.get(entry.getKey());
            //url传过来的参数都是String类型的，HTTP是基于字符串协议
            //只需要把String转换为任意类型就好
            paramValues[index] = ConvertStrategy.convert(paramTypes[index], value);

        }

        if(handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }

        if(handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Method method = handler.getMethod();
        Object returnValues = method.invoke(handler.controller, paramValues);
        if(returnValues == null || returnValues instanceof Void) return;
        resp.getWriter().write(returnValues.toString());

    }


    private Handler getHandler(HttpServletRequest req) {
        if(handlerMapping.isEmpty()){return null;}
        //绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (Handler handler : this.handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if(matcher.matches()) return handler;
        }
        return null;
    }

    //init方法肯定干得的初始化的工作
    //inti首先我得初始化所有的相关的类，IOC容器、servletBean
    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            //获取配置信息
            initContextConfig(config.getInitParameter("contextConfigLocation"));

            //扫描相关的类
            doScanner(contextConfig.getProperty("scanPackage") );

            //初始化相关类
            initBeans();

            //自动注入
            doAutowired();

            //初始化url映射 HandlerMapping
            initHandlerMapping();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandlerMapping() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
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

                Pattern pattern = Pattern.compile(url);
                this.handlerMapping.add(new Handler(pattern, method, entry.getValue()));
                System.out.println("Mapped " + url + "," + method);
            }

        }
    }

    private void doAutowired() throws IllegalAccessException {
        if(ioc.isEmpty()){return;}

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

    private void initContextConfig(String contextConfigLocation) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:", "").trim());
            this.contextConfig.load(is);
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

    private void initBeans() throws Exception {
        //初始化，为DI做准备
        if(this.classNames.isEmpty()){return;}
        for (String className : this.classNames) {
            //什么样的类才需要初始化呢？
            //加了注解的类，才初始化，怎么判断？
            //为了简化代码逻辑，主要体会设计思想，只举例 @Controller和@Service,
            // @Componment...就一一举例了
            ioc = (Map<String, Object>) BeanInitStrategy.initBean(ioc, className);
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
                this.classNames.add(clazzName);
            }
        }
    }


    public class Handler {
        private Pattern pattern;
        private Method method;
        private Object controller;
        private Class<?> [] paramTypes;
        private String[] paramNames;

        //形参列表
        //参数的名字作为key,参数的顺序，位置作为值
        private Map<String,Integer> paramIndexMapping;

        public Pattern getPattern() {
            return pattern;
        }

        public void setPattern(Pattern pattern) {
            this.pattern = pattern;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }

        public void setParamTypes(Class<?>[] paramTypes) {
            this.paramTypes = paramTypes;
        }

        public Handler(Pattern pattern, Method method, Object controller) {
            this.pattern = pattern;
            this.method = method;
            this.controller = controller;

//            this.paramNames = getMethodParameterNamesByAsm4(controller.getClass(), method);
//            List<String> namesByAsm = AsmMethods.getParamNamesByAsm(method);
//            this.paramNames = namesByAsm.toArray(new String[namesByAsm.size()]);

            paramTypes = method.getParameterTypes();
            paramIndexMapping = new HashMap<String, Integer>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method){
            //提取方法中加了注解的参数
            //把方法上的注解拿到，得到的是一个二维数组
            //因为一个参数可以有多个注解，而一个方法又有多个参数
            Annotation[] [] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length ; i ++) {
                for(Annotation a : pa[i]){
                    if(a instanceof QueryParam){
                        String paramName = ((QueryParam) a).value();
                        if(!"".equals(paramName.trim())){
                            paramIndexMapping.put(paramName, i);
                        }
                    }
                }
            }

            //提取方法中的request和response参数
//            Class<?> [] paramsTypes = method.getParameterTypes();
//            for (int i = 0; i < paramsTypes.length ; i ++) {
//                Class<?> type = paramsTypes[i];
//                if(type.isAnnotationPresent(QueryParam.class)) continue;
//                if(type == HttpServletRequest.class ||
//                        type == HttpServletResponse.class){
//                    paramIndexMapping.put(type.getName(),i);
//                }
//            }

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length ; i ++) {
                Parameter parameter = parameters[i];
                if(parameter.isAnnotationPresent(QueryParam.class)) continue;

                Class<?> type = parameter.getType();
                if(type == HttpServletRequest.class ||
                        type == HttpServletResponse.class){
                    paramIndexMapping.put(type.getName(),i);
                    continue;
                }
                boolean namePresent = parameter.isNamePresent();
                String name = parameter.getName();
                paramIndexMapping.put(parameter.getName(), i);
            }


        }


    }

    /**
     * 获取指定类指定方法的参数名
     *
     * @param clazz 要获取参数名的方法所属的类
     * @param method 要获取参数名的方法
     * @return 按参数顺序排列的参数名列表，如果没有参数，则返回null
     */
//    public static String[] getMethodParameterNamesByAsm4(Class<?> clazz, final Method method) {
//        final Class<?>[] parameterTypes = method.getParameterTypes();
//        if (parameterTypes == null || parameterTypes.length == 0) {
//            return null;
//        }
//        final Type[] types = new Type[parameterTypes.length];
//        for (int i = 0; i < parameterTypes.length; i++) {
//            types[i] = Type.getType(parameterTypes[i]);
//        }
//        final String[] parameterNames = new String[parameterTypes.length];
//
//        String className = clazz.getName();
//        int lastDotIndex = className.lastIndexOf(".");
//        className = className.substring(lastDotIndex + 1) + ".class";
//        InputStream is = clazz.getResourceAsStream(className);
//        try {
//            ClassReader classReader = new ClassReader(is);
//            classReader.accept(new ClassVisitor(Opcodes.ASM4) {
//                @Override
//                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//                    // 只处理指定的方法
//                    Type[] argumentTypes = Type.getArgumentTypes(desc);
//                    if (!method.getName().equals(name) || !Arrays.equals(argumentTypes, types)) {
//                        return null;
//                    }
//                    return new MethodVisitor(Opcodes.ASM4) {
//                        @Override
//                        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
//                            // 静态方法第一个参数就是方法的参数，如果是实例方法，第一个参数是this
//                            if (Modifier.isStatic(method.getModifiers())) {
//                                parameterNames[index] = name;
//                            }
//                            else if (index > 0) {
//                                parameterNames[index - 1] = name;
//                            }
//                        }
//                    };
//
//                }
//            }, 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return parameterNames;
//    }
}
