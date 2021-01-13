package netty.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.chat.protocol.IMMessage;
import netty.chat.server.processor.MsgProcessor;

public class TerminalServerHandler extends SimpleChannelInboundHandler<IMMessage> {
    private MsgProcessor processor = new MsgProcessor();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessage msg) throws Exception {
        processor.process(ctx.channel(), msg);
    }
}
