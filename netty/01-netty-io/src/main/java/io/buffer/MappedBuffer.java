package io.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedBuffer {
    private static final int start = 0;
    private static final int size = 1024;
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = MappedBuffer.class.getClassLoader();
        String infile = "test.txt";
        String file = classLoader.getResource(infile).getFile();
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel channel = raf.getChannel();
        //把缓冲区跟文件系统进行映射
        //操作缓冲区同时也能修改文件内容
        MappedByteBuffer mapb = channel.map(FileChannel.MapMode.READ_WRITE, start, size);
        mapb.put(0, (byte) 97);
        mapb.put(1023, (byte)122);
        raf.close();
    }
}
