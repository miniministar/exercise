package io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    ServerSocket server;

    public BIOServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("bio 服务启动，监听端口为：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                Socket client = server.accept();
                System.out.println(client.getPort());
                InputStream is = client.getInputStream();
                byte[] buff = new byte[1024];
                int len = is.read(buff);
                if(len > 0) {
                    String msg = new String(buff, 0, len);
                    System.out.println("收到" + msg);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BIOServer(8080).listen();
    }
}
