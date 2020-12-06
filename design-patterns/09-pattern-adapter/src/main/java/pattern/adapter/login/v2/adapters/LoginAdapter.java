package pattern.adapter.login.v2.adapters;

import pattern.adapter.login.ResultMsg;

public interface LoginAdapter {
    boolean support(LoginAdapter adapter);
    ResultMsg login(LoginAdapter loginAdapter, Object[] args);
}
