package netty.basic.client.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.basic.pojo.UnixTime;

import java.util.List;

public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4) {
            return;
        }
//        out.add(in.readBytes(4));
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
