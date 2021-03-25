package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramClient {
    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();

        StringBuilder sb = new StringBuilder();
        sb.append("2017-03-09 12:30:00;")
                .append("aaa")
                .append("testapp;")
                .append("test.do;")
                .append("param=hello;")
                .append("test;")
                .append("100;")
                .append("1");
        ByteBuffer buffer = ByteBuffer.allocate(10240);
        buffer.clear();
        buffer.put(sb.toString().getBytes());
        buffer.flip();
        //此处IP为服务端IP地址，端口和服务端的端口一致
        int n = channel.send(buffer, new InetSocketAddress("127.0.0.1", 8080));
        System.out.println(sb);
        //每次数据发送完毕之后，一定要调用close方法，来关闭占用的udp端口，否则程序不结束，端口不会释放
        channel.close();
    }
}
