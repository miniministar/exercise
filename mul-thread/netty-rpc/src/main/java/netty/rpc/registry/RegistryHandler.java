package netty.rpc.registry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RegistryHandler extends ChannelInboundHandlerAdapter {
    /**
     * 1、根据一个报名将所有符合条件的class全部扫描处理，放到容器中
     * 如果是分布式，就读取配置文件
     * 2、给每一个对应的class起一个唯一的名字，作为服务名称，保存到容器中
     * 3、当有客户端连接过来的时候，会获取协议内容InvokerProtocol对象
     * 4、要去注册好的容器中找到符合条件的对象
     * 5、
     */

    /**
     * 当客户端连接的时候的回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    /**
     * 连接发生异常的时候回调
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
