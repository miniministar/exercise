package io.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {
    private static final int port = 8080;
    private static Selector selector;
    private static ByteBuffer  buffer = ByteBuffer.allocate(1024);

    static {
        try {
            getSelect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SelectorTest().listen();
    }

    public static void getSelect() throws IOException {
        //创建通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        //绑定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);
        //创建Selector对象
        selector = Selector.open();
        //向Selector中注册事件，既可接收请求
        server.register(selector, SelectionKey.OP_ACCEPT);
    }
    public void listen() {
        System.out.println("监听端口：" + port);
        try {
            while (true) {
                //阻塞直到任意一个通道发生一个事件
                selector.select();
                //获取所有通道的令牌
                Set<SelectionKey> keys = selector.selectedKeys();
                //便利令牌
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据令牌的状态进行对应的处理
     * @param key
     * @throws IOException
     */
    private static void process(SelectionKey key) throws IOException {
        // 接收请求
        if(key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }else
            // 读信息
            if(key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if(len > 0 ) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                SelectionKey skey = channel.register(selector, SelectionKey.OP_WRITE);
                skey.attach(content);
                System.out.println("读取内容：" + content);
            }else {
                channel.close();
            }
            buffer.clear();
        }else
                // 写事件
            if(key.isWritable()){
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            ByteBuffer block = ByteBuffer.wrap(("输出内容：" + content).getBytes());
            channel.close();
        }
    }
}
