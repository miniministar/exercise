package netty.rpc.server.impl;

import netty.rpc.server.IHelloService;
import netty.rpc.server.RpcService;
import netty.rpc.server.User;

@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloSeviceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("【V1.0】request is sayHello " + content);
        return "【V1.0】sayHello" + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser:" + user);
        return "【V1.0】success";
    }
}
