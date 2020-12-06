package pattern.adapter.login.v2;

import pattern.adapter.login.ResultMsg;
import pattern.adapter.login.v1.service.SiginService;
import pattern.adapter.login.v2.adapters.*;

import java.util.HashMap;
import java.util.Map;

public class PassportForThirdAdapter  extends SiginService implements IPassportForThird {
    public static final String LOGIN_QQ = "LOGIN_QQ";
    public static final String LOGIN_WECHAT = "LOGIN_WECHAT";
    public static final String LOGIN_TOKEN = "LOGIN_TOKEN";
    public static final String LOGIN_TELEPHONE = "LOGIN_TELEPHONE";
    public static Map<String, LoginAdapter> loginAdapterMap = new HashMap<>();

    static {
        loginAdapterMap.put(LOGIN_QQ, new LoginForQQAdapter());
        loginAdapterMap.put(LOGIN_WECHAT, new LoginForWechatAdapter());
        loginAdapterMap.put(LOGIN_TOKEN, new LoginForTokenAdapter());
        loginAdapterMap.put(LOGIN_TELEPHONE, new LoginForTelephoneAdapter());
    }
//    @Override
//    public ResultMsg loginForQQ(String openId) {
//        return null;
//    }
//
//    @Override
//    public ResultMsg loginForWechat(String openId) {
//        return null;
//    }
//
//    @Override
//    public ResultMsg loginForToken(String token) {
//        return null;
//    }
//
//    @Override
//    public ResultMsg loginForTelphone(String telphone, String code) {
//        return null;
//    }

    //结合策略模式、工厂模式、适配器模式
    @Override
    public ResultMsg loginThird(String adapterKey, Object... obj) {
        if(loginAdapterMap.containsKey(adapterKey)) {
            LoginAdapter loginAdapter = loginAdapterMap.get(adapterKey);
            //适配器不一定要实现接口
            //判断传过来的适配器是否能处理指定的逻辑
            if(loginAdapter.support(loginAdapter)) {
                ResultMsg login = loginAdapter.login(loginAdapter, obj);
                return login;
            }
        }
        return null;
    }

    @Override
    public ResultMsg loginForRegister(String username, String password) {
        super.register(username, password);
        return super.login(username, password);
    }
}
