package rpc.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rpc.server.impl.HelloSeviceImpl;

public class App {
    public static void main(String[] args) {
//        IHelloService helloService = new HelloSeviceImpl();
//        RpcProxyServer rpcProxyServer = new RpcProxyServer();
//        rpcProxyServer.publisher(helloService, 8080);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext)context).start();
    }
}
