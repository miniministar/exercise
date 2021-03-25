package io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatagramServer {
    public static void main(String[] args) throws IOException {
        new DatagramServer().init();
    }

    //每次发送接收的数据包大小
    private final int MAX_BUFF_SIZE = 1024 * 10;
    //服务端监听端口，客户端也通过该端口发送数据
    private int port = 8080;
    private DatagramChannel channel;
    private Selector selector;
    private ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    public void init() throws IOException {
        //创建通道和选择器
        selector = Selector.open();
        channel = DatagramChannel.open();
        //设置为非阻塞模式
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        //将通道注册至selector，监听只读消息（此时服务端只能读数据，无法写数据）
        channel.register(selector, SelectionKey.OP_READ);
        //使用线程的方式，保证服务端持续等待接收客户端数据
        pool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    while (selector.select() > 0) {
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            try {
                                iterator.remove();
                                if (key.isReadable()) {
                                    //接收数据
                                    doReceive(key);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                try {
                                    if (key != null) {
                                        key.cancel();
                                        key.channel().close();
                                    }
                                } catch (ClosedChannelException cex) {
                                    System.out.println(cex.getMessage());
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, 0L, 2L, TimeUnit.MINUTES);
    }

    //处理接收到的数据
    private void doReceive(SelectionKey key) throws IOException {
        StringBuilder content = new StringBuilder();
        DatagramChannel sc = (DatagramChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(MAX_BUFF_SIZE);
        buffer.clear();
        sc.receive(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            byte[] buf = new byte[buffer.limit()];
            buffer.get(buf);
            content.append(new String(buf));
        }
        buffer.clear();
        System.out.println("receive content=" + new String(content));
        //其他操作
    }
}