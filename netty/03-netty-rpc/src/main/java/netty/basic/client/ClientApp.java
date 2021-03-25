package netty.basic.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @Description; 客户端启动类
 */
public class ClientApp {

    private final String host;
    private final int port;

    // 右键选择generater 创建构造函数
    //或者 alt + insert建
    //实例化这个类的时候，需要传这两个参数进来

    public ClientApp(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void run() throws Exception{
        // IO的线程池
        /**
         * @Description; 配置相应的参数，提供链接到远端的方法
         */

        EventLoopGroup group = new NioEventLoopGroup();// IO线程池
        // CTRL +ALT +T 快速输入
        try {
            Bootstrap b = new Bootstrap();// 客户端辅助启动类
            b.group(group)
                    .channel(NioSocketChannel.class) // 实例化一个channel
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler((new ChannelInitializer<SocketChannel>() // 进行通道初始化配置
                    {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 内部配置handler
                            // pipeline 管道，可以看作是handler的容器
                            socketChannel.pipeline().addLast(new ClientHandler()); //添加自定义的handler
                        }
                    }));

            // 配置完成后，链接到远端
            // 连接到远程节点，等待连接完成
            ChannelFuture future = b.connect().sync();

            // 发送消息到服务器，格式是utf8
            future.channel().writeAndFlush(Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));

            // 阻塞操作,closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作，知道连接断开）
            future.channel().closeFuture().sync();
        } finally {
            // 最后要释放资源，释放线程池后，彻底关闭，防止内存泄漏
            // 优雅的关闭线程池
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception{
        new ClientApp("127.0.0.1", 8080).run();
    }

}