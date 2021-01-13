package netty.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IMDDecoder extends ByteToMessageDecoder {
    /**
     * 上行命令：指服务器向客户端发送的消息内容
     * SYSTEM	系统命令，例如[命令][命令发送时间][接收人] - 系统提示内容
     * 例如：[SYSTEM][124343423123][Tom老师] – Student加入聊天室
     * 下行命令：指客户端想服务器发送的命令
     * LOGIN	登录动作：[命令][命令发送时间][命令发送人][终端类型]
     * 例如：[LOGIN][124343423123][Tom老师][WebSocket]
     * LOGOUT	退出登录动作：[命令][命令发送时间][命令发送人]
     * 例如：[LOGOUT][124343423123][Tom老师]
     * CHAT	聊天:[命令][命令发送时间][命令发送人][命令接收人] – 聊天内容
     * 例如：[CHAT][124343423123][Tom老师][ALL] - 大家好，我是Tom老师！
     * FLOWER	发送送鲜花特效:[命令][命令发送时间][命令发送人][终端类型][命令接收人]
     * 例如：[FLOWER][124343423123][you][WebSocket][ALL]
     *
     * 消息头 -
     */
    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final int length = in.readableBytes();
        final byte[] array = new byte[length];

        //网络传输过来的内容，变成一个字符串
        String content = new String(array, in.readerIndex(), length);

        //空消息不解析
        if(content == null || "".equals(content.trim())) {
            if(!IMP.isIMP(content)) {
                ctx.channel().pipeline().remove(this);
                return;
            }
        }
        //把字符串变成一个我们能识别的IMMessage对象
        in.getBytes(in.readerIndex(), array, 0, length);
        //利用序列化框架，将网络流信息直接转化为IMMessage对象
        out.add(new MessagePack().read(array, IMMessage.class));
        in.clear();
    }

    public IMMessage decode(String msg) {
        if(null == msg || "".equals(msg.trim())) {
            return null;
        }
        //解析字符串
        Matcher m = pattern.matcher(msg);
        String header = "";
        String content = "";
        if(m.matches()) {
            header = m.group(1);
            content = m.group(3);
        }

        //LOGIN][124343423123][Tom老师][WebSocket
        String[] headers = header.split("\\]\\[");

        long time = 0;
        time = Long.parseLong(headers[1]);

        String nickName = "";
        nickName = headers[2];
        //昵称最多十个字
        nickName = nickName.length() < 10 ? nickName : nickName.substring(0, 9);

        if(msg.startsWith("[" + IMP.LOGIN.getName() + "]")) {
            return new IMMessage(headers[0], headers[3], time, nickName);
        }else if(msg.startsWith("[" + IMP.CHAT.getName() + "]")) {
            return new IMMessage(headers[0], time, nickName, content);
        }else if(msg.startsWith("[" + IMP.FLOWER.getName() + "]")) {
            return new IMMessage(headers[0], headers[3], time, nickName);
        }else {
            return null;
        }
    }
}
