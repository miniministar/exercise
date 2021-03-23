package io.buffer;

import java.nio.ByteBuffer;

public class ByteBufferApiTest {
    /**
     * ByteBuffer api 测试
     */
    public static void main(String[] args) {
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
    }
}
