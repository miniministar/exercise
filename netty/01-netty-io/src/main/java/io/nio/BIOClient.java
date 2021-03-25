package io.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BIOClient {
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private ExecutorService pool = Executors.newFixedThreadPool(2);
    private static int readCount = 10;

    public BIOClient(String address, int port) {
        try {
            socket = new Socket(address, port);
            input = socket.getInputStream();
            output = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new BIOClient("localhost", NIOServer.port).start();
    }
    private void start() {
        pool.submit(new Writer());
        pool.submit(new Reader());
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
//            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    byte[] bs = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
                    if(output!=null) {
                        output.write(bs);
                        System.out.print("客户端 发送数据 ==> ");
                        for (byte b : bs) {
                            String hex = Integer.toHexString(b & 0xFF);
                            System.out.print(hex.length() < 2 ? "0" + hex : hex);
                        }
                        System.out.println("");
                    }else {
//                        System.out.println("客户端 发送数据完成");
//                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("客户端 写线程关闭");
                    if (!pool.isShutdown()) pool.shutdown();
                }
            }
//        }
    }

    private class Reader implements Runnable{
        @Override
        public void run() {
            try {
                byte[] bs = new byte[2];
                int readSize;
                readCount--;
                if(readCount == 0 && !socket.isClosed()) socket.close();
                while ((readSize = input.read(bs) ) > 0) {
                    System.out.print("客户端 读取数据 <== ");
                    for (byte b : Arrays.copyOf(bs, readSize)) {
                        String hex = Integer.toHexString(b & 0xFF);
                        System.out.print(hex.length() < 2 ? "0" + hex : hex);
                    }
                    System.out.println("");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                System.out.println("客户端 读线程关闭");
                if (!pool.isShutdown())pool.shutdown();
            }
        }
    }
}
