package netty.chat.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import netty.chat.protocol.IMDDecoder;
import netty.chat.protocol.IMDEncoder;
import netty.chat.server.handler.HttpServerHandler;
import netty.chat.server.handler.TerminalServerHandler;
import netty.chat.server.handler.WebSocketServerHandler;

public class ChatClient {

    public void start() {
        //传输协议封装
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
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
                    });

            ChannelFuture future = b.connect("localhost", 8080).sync();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String [] args){  
    }
    
}
