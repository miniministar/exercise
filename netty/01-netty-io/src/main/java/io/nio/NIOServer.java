package io.nio;

import cn.hutool.core.util.HexUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NIOServer {
    private static List<byte[]> command = new ArrayList<>();
    public static final int port = 1024;
    private static void handlerAccept(SelectionKey key, Selector selector) throws IOException {
        // 选择器关注了ServerSocketChannel通道的OP_ACCEPT事件，所以这里的通道肯定是ServerSocketChannel通道
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        // ServerSocketChannel通道的accept()与ServerSocket的accept()差不多，较ServerSocket多了一些nio操作api
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        // 通道注册选择器，并让选择器关注它“读事件”和“写事件”
        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        System.out.println("服务器接受连接，客户端地址：" + socketChannel.getRemoteAddress());
    }

    public static void main(String[] args) throws IOException {
        // 自动创建命令，用于向客户发送数据
        autoRunCommand();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器就绪，端口：" + port + "，等待连接....");
        while (true) {
            if(selector.select(1000) == 0) continue;
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if(key.isValid()) {
                    if(key.isAcceptable()) {
                        handlerAccept(key, selector);
                    }else if(key.isReadable()) {
                        handlerRead(key);
                    }else if(key.isWritable()) {
                        handlerWrite(key);
                    }
                }
            }
        }
    }

    private static void handlerWrite(SelectionKey key) throws IOException {
        if(command .size() > 0 ) {
            byte[] bytes = command.get(0);
            ByteBuffer writeBuffer = ByteBuffer.wrap(bytes);
            command.remove(0);
            SocketChannel socketChannel = (SocketChannel) key.channel();
            socketChannel.write(writeBuffer);
            System.out.println( "服务器 发送数据 " + System.currentTimeMillis() / 1000 +
                    " ==> " + HexUtil.encodeHexStr(writeBuffer.array()));
        }
    }

    /**
     * 选择器关注了SocketChannel通道的OP_READ事件，所以这里的通道肯定是SocketChannel通道
     * @param key
     */
    private static void handlerRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        int readSize = socketChannel.read(buffer);
        int pos = buffer.position();
        buffer.flip();
        byte[] bs = Arrays.copyOf(buffer.array(), pos);
        if(readSize > 0) {
            System.out.print("服务器 读取数据 " + System.currentTimeMillis() / 1000 + " <== ");
            for (byte b : bs) {
                String hex = Integer.toHexString(b & 0xFF);
                System.out.print(hex.length() < 2 ? "0" + hex : hex);
            }
            System.out.println("");
        }else if(readSize == 0) {
            // 没有数据可读，可不操作
        }else {
            System.out.println("服务器 断开连接" + socketChannel.getRemoteAddress());
            // key.cancel()后会在下次select()期间注销该key对应的通道，而非即时注销
            key.cancel();
        }
    }

    private static void autoRunCommand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    command.add(new byte[] {0x1a, 0x2b, 0x3c});
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
