package netty.chat.protocol;

/**
 * 聊天协议
 */
public enum IMP {
    /**
     * 系统消息
     */
    SYSTEM("SYSTEM"),
    /**
     * 登录指令
     */
    LOGIN("LOGIN"),
    /**
     * 登出指令
     */
    LOGOUT("LOGOUT"),
    /**
     * 聊天消息
     */
    CHAT("CHAT"),
    /**
     * 送鲜花
     */
    FLOWER("FLOWER");

    private String name;

    IMP(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 判断是否消息内容为自己能认识的协议内容
     * @param content
     * @return
     */
    public static boolean isIMP(String content) {
        //责任链模式
        return content.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER)\\]");
    }
}
