package pattern.adapter.login.v2.adapters;

import pattern.adapter.login.ResultMsg;

public class LoginForTokenAdapter implements LoginAdapter {
    @Override
    public boolean support(LoginAdapter adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public ResultMsg login(LoginAdapter loginAdapter, Object[] args) {
        System.out.println("通过token:" + args[0] + "登陆");
        return null;
    }
}
