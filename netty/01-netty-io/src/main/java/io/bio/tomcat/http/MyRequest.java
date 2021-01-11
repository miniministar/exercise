package io.bio.tomcat.http;

import lombok.Data;
import lombok.ToString;

import java.io.InputStream;


@Data
@ToString
public class MyRequest {
    private String method;
    private String url;
    public MyRequest(InputStream in) {
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = in.read(buffer) ) != -1) {
                System.out.println(len);
                content += new String(buffer, 0, len);
            }
            String line =  content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
            System.out.println(content);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
