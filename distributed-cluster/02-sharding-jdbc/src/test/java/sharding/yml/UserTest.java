package sharding.yml;

import sharding.yml.dao.*;
import sharding.yml.entity.*;
import sharding.yml.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void insert1() throws Exception {
//        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("test");
            user.setAge(22);
//            user.setUserId(2673 + i);
            user.setUserId(2673 );
            this.userDao.addOne(user);
            log.info("插入用户id：{}", user.getUserId());
//        }
    }
}

