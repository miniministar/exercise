package rpc.server;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;
    private Map<String,Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
//        this.service = service;
        this.handlerMap = handlerMap;
    }
//
//    public ProcessorHandler(Socket socket, Object service) {
//        this.socket = socket;
//        this.service = service;
//    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        ObjectOutputStream os = null;

        try {
            in = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) in.readObject();
            Object result = invoke(rpcRequest);

            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(result);
            os.flush();


        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os!= null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String serviceName = rpcRequest.getClassName();
        String version = rpcRequest.getVersion();
        if(!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }
        Object service = handlerMap.get(serviceName);
        if(service==null) {
            throw new RuntimeException("service not found:" + serviceName);
        }


        Class<?> clazz = Class.forName(rpcRequest.getClassName());
        Object[] args = rpcRequest.getParameters();
        Method method = null;
        if(args!=null) {
            Class[] types = new Class[args.length];
            for(int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            method = clazz.getMethod(rpcRequest.getMethodName(), types);
        }else {
            method = clazz.getMethod(rpcRequest.getMethodName());
        }

        Object result = method.invoke(service, args);
        return result;
    }
}
