package org.example.springboot.service


import org.example.springboot.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Title

@Title("UserService集成测试")
// 注意这里要使用 unittest 的配置文件了，里面使用H2数据库
@ActiveProfiles("unittest")
// 关于@Rollback(false)注解：正常情况下，SpringBoot的测试用例每执行完一个就会回滚一次，
// 比如说再执行完create entity test之后会立马将刚刚insert进去的数据清除，之后执行“get entity test”的时候应该是测试用例不通过的。
// 加了这个注解后，就可以指定某个测试方法，或者某个测试类的执行结果不进行回滚。
@Rollback(false)
@SpringBootTest
class IUserServiceIntegrationTest extends Specification {

    @Autowired
    private IUserService userService;

    def "Get"() {
        given: "设置初始数据"
        userService.delete(50)
        User user = new User(id: 50, username: "zhansan", nickname: "张三")
        userService.add(user);
        User getUser = userService.get(50);
        expect:
        getUser!=null && getUser.getUsername().equals("zhansan")
    }

    def "Add"() {
        given: "设置初始数据"
        User user = new User()
        user.id = id
        user.username = username
        user.nickname = nickname

        expect: ""
        userService.add(user) != null

        where:
        id   | username    | nickname
        1001 | "zhangsan1" | "张三"
        2002 | "zhangsan2" | "李四"
    }

    def "ModifyById"() {
        given: "设置初始数据"
        userService.delete(50)
        User user = new User(id: 50, username: "zhansan", nickname: "张三")
        userService.add(user);
        user = new User(id: 50, username: "zhansan2", nickname: "张三3")
        User getUser = userService.modifyById(user);
        expect:
        getUser!=null && getUser.getUsername().equals("zhansan2")
    }

    def "Delete"() {
        given: "设置初始数据"
        userService.delete(50)
        User user = new User(id: 50, username: "zhansan", nickname: "张三")
        userService.add(user);
        expect:
        userService.delete(50)
    }

    def "ListAll"() {
        given: "设置初始数据"
        User user = new User()
        user.username = username
        user.nickname = nickname
        userService.add(user)
        when: ""
        def all = userService.listAll(user)

        then: ""
        with(all) {
            all!=null && all.size() > 0
        }

        where:
        username    | nickname
        "zhangsan1" | "张三"
        "zhangsan2" | "李四"
    }
}
