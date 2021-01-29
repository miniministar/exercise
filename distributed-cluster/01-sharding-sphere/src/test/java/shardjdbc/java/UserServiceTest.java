package shardjdbc.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shardjdbc.java.entity.UserInfo;
import shardjdbc.java.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 演示取模的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Resource
    UserService userService;

    @Test
    public void insert(){
        userService.insert();
    }

    @Test
    public void select(){
        UserInfo userInfo1= userService.getUserInfoByUserId(1L);
        System.out.println("------userInfo1:"+userInfo1);

        UserInfo userInfo2= userService.getUserInfoByUserId(2L);
        System.out.println("------userInfo2:"+userInfo2);
    }

    @Test
    public void selectByRange() {
        List<UserInfo> userInfoList = userService.selectByRange(1L, 100L);
        System.out.println(userInfoList);
    }
}