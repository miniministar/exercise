package netty.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import netty.chat.server.processor.MsgProcessor;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private MsgProcessor processor = new MsgProcessor();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        processor.process(ctx.channel(), msg.text());
    }
}
