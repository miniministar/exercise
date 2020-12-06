package pattern.adapter.login.v2;

import org.junit.Test;

import static org.junit.Assert.*;

public class PassportForThirdAdapterTest {

    @Test
    public void login() {
        PassportForThirdAdapter adapter = new PassportForThirdAdapter();
        adapter.loginThird(adapter.LOGIN_QQ, "10057");

        adapter.loginThird(adapter.LOGIN_TELEPHONE, "110", "+89");

        adapter.loginThird(adapter.LOGIN_TOKEN, "token");

        adapter.loginThird(adapter.LOGIN_WECHAT, "wechat");

        adapter.login("zhangsan", "123");
    }
}