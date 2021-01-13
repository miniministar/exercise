package netty.chat.protocol;

import lombok.Data;
import lombok.ToString;
import org.msgpack.annotation.Message;

/**
 * java中传播自定义协议的消息
 */
@Message
@Data
@ToString
public class IMMessage {
    //ip地址及端口
    private String addr;
    //命令类型[LOGIN]或者[SYSTEM]或者[LOGOUT]
    private String cmd;
    //命令发送时间
    private long time;
    //当前在线人数
    private int online;
    //发送人
    private String sender;
    //接收人
    private String receiver;
    //消息
    private String content;
    //终端
    //[命令][命令发送时间][命令发送人][终端类型][命令接收人]
    private String terminal;

    public IMMessage() {
    }

    public IMMessage(String cmd, long time, int online, String content) {
        this.cmd = cmd;
        this.time = time;
        this.online = online;
        this.content = content;
    }

    public IMMessage(String cmd, String terminal,  long time, String sender) {
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
        this.terminal = terminal;
    }

    public IMMessage(String addr, long time, String sender, String content) {
        this.addr = addr;
        this.time = time;
        this.sender = sender;
        this.content = content;
    }

    public IMMessage(String cmd, long time, int online) {
        this.cmd = cmd;
        this.time = time;
        this.online = online;
    }
}
