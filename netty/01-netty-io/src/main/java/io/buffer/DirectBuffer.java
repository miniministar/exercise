package io.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirectBuffer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ClassLoader classLoader = DirectBuffer.class.getClassLoader();
        String inputfile = "test.txt";
        URL resource = classLoader.getResource(inputfile);
        FileInputStream fin = new FileInputStream(resource.getFile());
        FileChannel channel = fin.getChannel();
        String outfile = "test_copy.txt";
        String path = resource.toURI().getPath();
        path = path.replace(inputfile, "");
        FileOutputStream fout = new FileOutputStream(new File(path, outfile));
        FileChannel foutChannel = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();
            int read = channel.read(buffer);
            if(read == -1) {
                break;
            }
            buffer.flip();
            foutChannel.write(buffer);
        }
    }
}
