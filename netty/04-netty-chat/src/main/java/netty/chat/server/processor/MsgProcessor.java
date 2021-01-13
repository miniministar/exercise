package netty.chat.server.processor;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import netty.chat.protocol.IMDDecoder;
import netty.chat.protocol.IMDEncoder;
import netty.chat.protocol.IMMessage;
import netty.chat.protocol.IMP;

public class MsgProcessor {
    private IMDEncoder encoder = new IMDEncoder();
    private IMDDecoder decoder = new IMDDecoder();

    //netty里的工具，解决了线程安全问题，线程的执行性能
    private static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //定义一些扩展属性
    public static final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");
    public static final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    public static final AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");
    public static final AttributeKey<String> FROM = AttributeKey.valueOf("from");

    public void process(Channel client, String msg) {
        IMMessage request = decoder.decode(msg);
        if(null == request) return;
        if(request.getCmd().endsWith(IMP.LOGIN.getName())) {
            //昵称
            client.attr(NICK_NAME).getAndSet(request.getSender());
            //保存用户的ip地址
            //端口
            client.attr(IP_ADDR).getAndSet(getAddress(client));
            //终端类型
            client.attr(FROM).getAndSet(request.getTerminal());
            //把这个用户保存到一个统一的容器里，给所有已经在线的推送消息
            onlineUsers.add(client);

            //通知所有的在线用户，连接的用户上线了
            for (Channel channel : onlineUsers) {
                boolean isSelf = (channel == client);
                if(!isSelf) {
                    request = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlineUsers.size(), getNickName(client) + "上线");
                }else {
                    request = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlineUsers.size(), "已与服务器建立连接");
                }

                //消息准备好了， 推送消息包
                //如果终端是控制台，推送IMMessage;
                if("Console".equals(channel.attr(FROM).get())) {
                    channel.writeAndFlush(request);
                    continue;
                }

                //如果总得是控制台，推送WebSocket的自定义协议字符串
                String content = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }


        }else if(request.getCmd().endsWith(IMP.CHAT.getName())) {

            for (Channel channel : onlineUsers) {
                boolean isSelf = (channel == client);
                if(!isSelf) {
                    request.setSender(getNickName(client));
                }else {
                    request.setSender("you");
                }

                //消息准备好了， 推送消息包
                //如果终端是控制台，推送IMMessage;
                if("Console".equals(channel.attr(FROM).get()) && !isSelf) {
                    channel.writeAndFlush(request);
                    continue;
                }

                //如果总得是控制台，推送WebSocket的自定义协议字符串
                String content = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }


        }else if(request.getCmd().endsWith(IMP.FLOWER.getName())) {
            JSONObject attrs = getAttrs(client);
            long currentTime = sysTime();
            if(null!= attrs) {
                long lastTime = attrs.getLongValue("lastFlowerTime");
                //60秒内不允许重复
                int seconds = 60;
                long sub = currentTime - lastTime;
                if(sub < 1000 * seconds) {
                    request.setSender("you");
                    request.setCmd(IMP.SYSTEM.getName());
                    request.setContent("操作太频繁，" +  (seconds - Math.round(sub / 1000)) + "秒后再重试") ;

                    client.writeAndFlush(new TextWebSocketFrame(encoder.encode(request)));
                    return;
                }

            }

            for (Channel channel : onlineUsers) {
                if(channel == client) {
                    request.setSender("you");
                    request.setContent("你给大家送了一波鲜花雨");
                    setAttrs(client, "lastFlowerTime", currentTime);
                }else {
                    request.setSender(getNickName(client));
                    request.setContent(getNickName(client) + "送来一波鲜花雨");
                }
                request.setTime(sysTime());
                channel.writeAndFlush(new TextWebSocketFrame(encoder.encode(request) ) );
            }


        }else if(request.getCmd().endsWith(IMP.LOGOUT.getName())) {

        }else if(request.getCmd().endsWith(IMP.SYSTEM.getName())) {

        }
    }

    private JSONObject getAttrs(Channel client) {
        return client.attr(ATTRS).get();
    }

    private String getNickName(Channel client) {
        return client.attr(NICK_NAME).get();
    }

    private long sysTime() {
        return System.currentTimeMillis();
    }

    public void logout(Channel client) {
        if(getNickName(client) == null) return;
        for (Channel channel : onlineUsers) {
            IMMessage request = new IMMessage(IMP.SYSTEM.getName(), sysTime(), onlineUsers.size(), getNickName(client) + "离开");
            String content = encoder.encode(request);
            channel.writeAndFlush(new TextWebSocketFrame(content));
        }
        onlineUsers.remove(client);
    }

    public void setAttrs(Channel client, String key, Object value) {
        JSONObject json = null;
        try {
            json = client.attr(ATTRS).get();
        }catch (Exception e) {
            json = new JSONObject();
        }
        json.put(key, value);
        client.attr(ATTRS).set(json);
    }

    /**
     * 获取用户远程ip地址
     * @param client
     * @return
     */
    private String getAddress(Channel client) {
        return client.remoteAddress().toString().replaceFirst("/", "");
    }

    public void process(Channel client, IMMessage msg) {
        process(client, encoder.encode(msg));
    }
}
