package io.buffer;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        //创建自读缓冲区，共享原缓冲区数据，原缓冲区变化自读缓冲区也会变化，但不能直接修改自读缓冲区
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }
        readOnly.position(0);
        readOnly.limit(readOnly.capacity());
        while (readOnly.remaining() > 0) {
            System.out.println(readOnly.get());
        }
    }
}
