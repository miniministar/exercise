package pattern.adapter.login.v2.adapters;

import pattern.adapter.login.ResultMsg;

public class LoginForTelephoneAdapter implements LoginAdapter {
    @Override
    public boolean support(LoginAdapter adapter) {
        return adapter instanceof LoginForTelephoneAdapter;
    }

    @Override
    public ResultMsg login(LoginAdapter loginAdapter, Object[] args) {
        System.out.println("通过电话" + args[1] + args[0] +  "登陆");
        return null;
    }
}
