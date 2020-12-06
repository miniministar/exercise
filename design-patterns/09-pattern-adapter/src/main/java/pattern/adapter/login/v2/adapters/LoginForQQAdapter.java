package pattern.adapter.login.v2.adapters;

import pattern.adapter.login.ResultMsg;

public class LoginForQQAdapter implements LoginAdapter {
    @Override
    public boolean support(LoginAdapter adapter) {
        return adapter instanceof LoginAdapter;
    }

    // 可改造为模板模式
    // 统一流程方法： login
    // 1.第三方验证，验证通过后，返回结果 thirdLogin abstract
    // 2.结果判断，成功则
    // 3.查询key是否存在，不存在则自动进行注册 alreadyRegister abstract
    // 4.注册 register abstract
    // 5.调用原来的登陆方法，返回

    // 也可不改，在login中实现全部逻辑
    @Override
    public ResultMsg login(LoginAdapter loginAdapter, Object[] args) {
        //1、openId是全局唯一，我们可以把它当做是一个用户名(加长)
        //2、密码默认为QQ_EMPTY
        //3、注册（在原有系统里面创建一个用户）

        //4、调用原来的登录方法

        System.out.println("通过QQ：" + args[0] + "登陆");
        //逻辑处理，成功或失败
        return null;
    }
}
