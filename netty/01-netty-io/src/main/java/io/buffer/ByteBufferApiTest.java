package io.buffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static io.buffer.ByteBufferUtil.debugAll;

@Slf4j
public class ByteBufferApiTest {
    /**
     * ByteBuffer api 测试
     */
    public static void main(String[] args) throws IOException {
        //声明一个字节类型的非直接缓冲区，可以使用allocateDirect()声明一个直接缓冲区，
        // 该缓冲区建立和释放成本高，主要用于大型、长寿命的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }
        //创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        //改变子缓冲区内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        //获取缓冲区内容输出
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }

        bufferToString();
//        readLongMsg();
    }

    public static void bufferToString() {
        //字符串转buffer
        String str = "你好";
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.put(str.getBytes(StandardCharsets.UTF_8));

        //2.Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode(str);

        //3. wrap
        ByteBuffer buffer2 = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));

        //buffer 转String
        buffer.flip();
        String str1 = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(str1);
        str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str1);
        str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);
    }

    /**
     * 分散读
     */
    @Test
    public void scattingReads() {
        try (RandomAccessFile file = new RandomAccessFile("3parts.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            debugAll(a);
            debugAll(b);
            debugAll(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 集中写入
     */
    @Test
    public void scatteringWrites() {
        try (RandomAccessFile file = new RandomAccessFile("3parts.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer d = ByteBuffer.allocate(4);
            ByteBuffer e = ByteBuffer.allocate(4);
            channel.position(11);
            d.put(new byte[]{'f', 'o', 'u', 'r'});
            e.put(new byte[]{'f', 'i', 'v', 'e'});
            d.flip();
            e.flip();
            debugAll(d);
            debugAll(e);
            channel.write(new ByteBuffer[]{d, e});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 黏包和半包
     */
    @Test
    public void bufferMergeAndSplit() {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                System.out.println(i+ "\t"+ source.position() + "\t" + target.capacity());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                target.flip();
                System.out.print(StandardCharsets.UTF_8.decode(target).toString());
                debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }


    /**
     * 粘包半包问题
     */
    @Test
    public void readLongMsg() throws IOException {
        // 1. 创建 selector, 管理多个 channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // 2. 建立 selector 和 channel 的联系（注册）
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key 只关注 accept 事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("sscKey:{}", sscKey);
        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            // 3. select 方法, 没有事件发生，线程阻塞，有事件，线程才会恢复运行
            // select 在事件未处理时，它不会阻塞, 事件发生后要么处理，要么取消，不能置之不理
            selector.select();
            // 4. 处理事件, selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator(); // accept, read
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                // 处理key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                iter.remove();
                log.debug("key: {}", key);
                // 5. 区分事件类型
                if (key.isAcceptable()) { // 如果是 accept
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16); // attachment
                    // 将一个 byteBuffer 作为附件关联到 selectionKey 上
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                    log.debug("scKey:{}", scKey);
                } else if (key.isReadable()) { // 如果是 read
                    try {
                        SocketChannel channel = (SocketChannel) key.channel(); // 拿到触发事件的channel
                        // 获取 selectionKey 上关联的附件
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer); // 如果是正常断开，read 的方法的返回值是 -1
                        if(read == -1) {
                            key.cancel();
                        } else {
                            split(buffer);
                            // 需要扩容
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer); // 0123456789abcdef3333\n telnet localhost 8080 测试
                                key.attach(newBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();  // 因为客户端断开了,因此需要将 key 取消（从 selector 的 keys 集合中真正删除 key）
                    }
                }
            }
        }
    }
}
