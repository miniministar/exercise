#security oauth2

```$xslt
├──security-user    --认证服务
├──order-server     --资源服务
```

###四种授权模式
> "authorization_code", "password", "client_credentials", "implicit", "refresh_token"
- 密码模式（resource owner password credentials）(为遗留系统设计)(支持refresh token)
- 授权码模式（authorization code）(正宗方式)(支持refresh token)
- 简化模式（implicit）(为web浏览器应用设计)(不支持refresh token)
- 客户端模式（client credentials）(为后台api服务消费者设计)(不支持refresh token)

1. 授权码模式
- 这种模式算是正宗的oauth2的授权模式, 最安全 
- 设计了auth code，通过这个code再获取token
- 支持refresh token
> 授权码的请求路径
~~~~
/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_url=http://www.baidu.com
~~~~
> 获取token
~~~~
/oauth/token?client_id=c1&response_type=code&scope=all&redirect_url=http://www.baidu.com
~~~~
2. 简化模式
- 这种模式比授权码模式少了code环节，回调url直接携带token
- 这种模式的使用场景是基于浏览器的应用
- 这种模式基于安全性考虑，建议把token时效设置短一些
- 不支持refresh token
> 经过授权后直接返回access_token,
~~~~
/oauth/authorize?client_id=c1&response_type=token&scope=all&redirect_url=http://www.baidu.com

返回值
https://www.baidu.com/#access_token=20ea0a59-a043-492f-86cf-836f948f58cc&token_type=bearer&expires_in=6637
~~~~
3. 密码模式
- 这种模式是最不推荐的，会将密码泄露给client
- 这种模式主要用来做遗留项目升级为oauth2的适配方案
- 当然如果client是自家的应用，也是可以
- 支持refresh token
~~~~
post 请求
/oauth/token
参数
client_id=c1&client_secret=secret&grant_type=password&username=admin&password=1111
~~~~
4. 客户端模式
- 这种模式直接根据client的id和密钥即可获取token，无需用户参与
- 这种模式比较合适消费api的后端服务，比如拉取一组用户信息等
- 不支持refresh token，主要是没有必要
> refresh token的初衷主要是为了用户体验不想用户重复输入账号密码来换取新token，因而设计了refresh token用于换取新token
>
> 这种模式由于没有用户参与，而且也不需要用户账号密码，仅仅根据自己的id和密钥就可以换取新token，因而没必要refresh token
~~~~
post 请求
/oauth/token
参数
client_id=c1&client_secret=secret&grant_type=client_credentials
~~~~


### 资源服务
- 校验token
~~~~
/oauth/check_token?token=289d1f2d-26f5-4f2d-9542-6c4d3f0a936c

访问资源

