package netty.tomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import netty.tomcat.http.MyRequest;
import netty.tomcat.http.MyResponse;
import netty.tomcat.http.MyServlet;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyTomcat {
    private int port = 8080;
    private Map<String, MyServlet> servletMapping = new HashMap<>();
    private Properties webxml = new Properties();

    private void start() {
        init();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new HttpRequestDecoder());
                            client.pipeline().addLast(new MyTomcatHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = server.bind(port).sync();
            System.out.println("Tomcat 以启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

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
                    MyServlet servlet = (MyServlet) Class.forName(className).newInstance();
                    servletMapping.put(url, servlet);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class MyTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof HttpRequest) {
                HttpRequest request = (HttpRequest) msg;
                MyRequest myRequest = new MyRequest(ctx, request);
                MyResponse myResponse = new MyResponse(ctx, request);
                String url = myRequest.getUrl();
                if(servletMapping.containsKey(url)) {
                    servletMapping.get(url).service(myRequest, myResponse);
                }else {
                    myResponse.write("404 - Not found");
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyTomcat().start();
    }
}
