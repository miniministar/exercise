package netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import netty.chat.protocol.IMDDecoder;
import netty.chat.protocol.IMDEncoder;
import netty.chat.server.handler.HttpServerHandler;
import netty.chat.server.handler.TerminalServerHandler;
import netty.chat.server.handler.WebSocketServerHandler;

public class ChatServer {
    private int port = 8080;
    public void start() {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //解析自定义协议 inbound
                            pipeline.addLast(new IMDDecoder());
                            //outbound
                            pipeline.addLast(new IMDEncoder());

                            //inbound
                            //处理直接发送IMMessage对象的控制台
                            pipeline.addLast(new TerminalServerHandler());

                            //解析http请求
                            //outbound
                            pipeline.addLast(new HttpServerCodec());
                            //主要是将同一个http请求或相应的多个消息对象编程一个fullHttpRequest完整的消息对象
                            // inbound
                            pipeline.addLast(new HttpObjectAggregator(64* 1024));
                            //inbound/outbound
                            pipeline.addLast(new ChunkedWriteHandler());
                            //处理web页面，解析http协议的
                            pipeline.addLast(new HttpServerHandler());

                            //inbound
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                            //处理im输出聊天， websocket通信协议
                            pipeline.addLast(new WebSocketServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

                //正式启动服务，相当于用一个死循环开始轮询
                ChannelFuture future = b.bind(this.port).sync();
                System.out.println("chat server start listen at " + this.port);
                future.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer().start();
    }
}
