package netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {
    public static void main(String[] args) {
        //1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                .group(new NioEventLoopGroup(2)) // 1
                .channel(NioServerSocketChannel.class) // 2
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 3
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder()); // 5
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() { // 6
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                Channel channel = ctx.channel();
                                System.out.println(channel + "->" + msg);
                            }
                        });
                    }
                })
                .bind(8080); // 4
    }
}
