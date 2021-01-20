package rabbitmq.javaapi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 连接 IP
        factory.setHost("127.0.0.1");
        // 连接端口
        factory.setPort(5672);
        // 虚拟机
        factory.setVirtualHost("/");
        // 用户
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 建立连接
        Connection con = factory.newConnection();
        // 创建消息通道
        Channel channel = con.createChannel();
        String msg = "hello rabbit mq";
        //发送消息
        channel.basicPublish(EXCHANGE_NAME, "routing.key", null, msg.getBytes());
        //关闭通道
        channel.close();
        //关闭连接
        con.close();
    }
}
