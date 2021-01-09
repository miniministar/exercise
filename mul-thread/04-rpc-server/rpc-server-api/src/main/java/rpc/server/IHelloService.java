package rpc.server;

public interface IHelloService {
    String sayHello(String content);

    String saveUser(User user);

}
