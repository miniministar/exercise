package netty.rpc.registry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.rpc.protocol.InvokerProtocol;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {
    //用保存所有可用的服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String,Object>();

    //保存所有相关的服务类
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler() {
        //完成递归扫描
        scannerClass("netty.rpc.provider");
        doRegister();
    }
    /**
     * 1、根据一个报名将所有符合条件的class全部扫描处理，放到容器中
     * 如果是分布式，就读取配置文件
     * 2、给每一个对应的class起一个唯一的名字，作为服务名称，保存到容器中
     * 3、当有客户端连接过来的时候，会获取协议内容InvokerProtocol对象
     * 4、要去注册好的容器中找到符合条件的对象
     * 5、通过远程调用provider得到返回结果，并回复给客户端
     */

    /**
     * 当客户端连接的时候的回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;
        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用

        if(registryMap.containsKey(request.getClassName())) {
            Object clazz = registryMap.get(request.getClassName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParams());
            result = method.invoke(clazz, request.getValues());
        }

        ctx.write(result);
        ctx.flush();
        ctx.close();
        System.out.println("服务端对客户端连接时候的回调");
    }

    private void doRegister() {
        if(classNames.size() == 0) return;
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if(file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            }else {
                classNames.add(packageName + "." + file.getName().replaceAll(".class", "").trim());
            }
        }


    }

    /**
     * 连接发生异常的时候回调
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
