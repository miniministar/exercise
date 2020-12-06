package pattern.adapter.login.v2.adapters;

import pattern.adapter.login.ResultMsg;

public class LoginForWechatAdapter implements LoginAdapter {
    @Override
    public boolean support(LoginAdapter adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    @Override
    public ResultMsg login(LoginAdapter loginAdapter, Object[] args) {
        System.out.println("通过微信：" + args[0] + "登陆");
        return null;
    }
}
