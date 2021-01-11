package io.bio.tomcat;

import io.bio.tomcat.http.MyRequest;
import io.bio.tomcat.http.MyResponse;
import io.bio.tomcat.http.MyServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String, MyServlet> servletMapping = new HashMap<>();
    private Properties webxml = new Properties();

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webxml.load(fis);

            for (Object k : webxml.keySet()) {
                String key = k.toString();
                if(key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");
                    MyServlet obj = (MyServlet)Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();
        try {
            server = new ServerSocket(this.port);
            System.out.println("Tomcat 启动，监听的端口是：" + this.port);
            while (true) {
                Socket client = server.accept();
                process(client);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        MyRequest request = new MyRequest(is);
        MyResponse response = new MyResponse(os);

        if(servletMapping.containsKey(request.getUrl())) {
            servletMapping.get(request.getUrl()).service(request, response);
        }else {
            response.write("404 - Not found");
        }

        os.flush();
        os.close();
        is.close();
        client.close();
    }

    public static void main(String[] args) {
        new MyTomcat().start();
    }
}
