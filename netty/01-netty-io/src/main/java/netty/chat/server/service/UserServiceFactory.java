package netty.chat.server.service;

public abstract class UserServiceFactory {

    private static UserService userService = new cn.itcast.server.service.UserServiceMemoryImpl();

    public static UserService getUserService() {
        return userService;
    }
}
