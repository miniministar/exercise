package io.bio;

import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class BIOClient {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 8080);
        OutputStream os = client.getOutputStream();
        String name = UUID.randomUUID().toString();
        System.out.println("客户端发送数据：" + name);
        os.write(name.getBytes());
        os.flush();
        os.close();
        client.close();
    }
}
