package pattern.adapter.login.v1.service;

import pattern.adapter.login.Member;
import pattern.adapter.login.ResultMsg;

public class SiginService {

    /**
     * 注册方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg register(String username, String password){
        return  new ResultMsg(200,"注册成功",new Member());
    }


    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password){
        System.out.println("用户名密码登陆，用户：" + username);
        return new ResultMsg(200,"登陆成功",new Member());
    }
}
