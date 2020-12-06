package pattern.adapter.login.v2;

import pattern.adapter.login.ResultMsg;

public interface IPassportForThird {

    /**
     *
     * @param adapterKey
     * @param obj
     * @return
     */
    ResultMsg loginThird(String adapterKey, Object ... obj);

//    /**
//     * QQ登录
//     * @param openId
//     * @return
//     */
//    ResultMsg loginForQQ(String openId);
//
//    /**
//     * 微信登录
//     * @param openId
//     * @return
//     */
//    ResultMsg loginForWechat(String openId);
//
//    /**
//     * 记住登录状态后自动登录
//     * @param token
//     * @return
//     */
//    ResultMsg loginForToken(String token);
//
//    /**
//     * 手机号登录
//     * @param telphone
//     * @param code
//     * @return
//     */
//    ResultMsg loginForTelphone(String telphone, String code);

    /**
     * 注册后自动登录
     * @param username
     * @param passport
     * @return
     */
    ResultMsg loginForRegister(String username, String passport);
}
