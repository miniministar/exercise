package io.buffer;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class FileChannelTest {

    @Test
    public void transferTo(){
        String FROM = "data.txt";
        String TO = "to.txt";
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO).getChannel();
        ) {
            //效率高，底层利用操作系统的零拷贝进行优化，最大拷贝2G数据
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("transferTo 用时：" + (end - start) / 1000000.0 + "ms");
    }

    @Test
    public void walkFillTree() throws IOException{
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("./"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println(dir.toAbsolutePath());
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println(file.toAbsolutePath());
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println(dirCount); // 133
        System.out.println(fileCount); // 1479
    }

    @Test
    public void copyDir() throws IOException {
        String source = "D:\\temp\\aa";
        String target = "D:\\temp\\bb";

        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                if(Files.isDirectory(path)) {
                    Files.createDirectories(Paths.get(targetName));
                }else if (Files.isRegularFile(path)){
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
