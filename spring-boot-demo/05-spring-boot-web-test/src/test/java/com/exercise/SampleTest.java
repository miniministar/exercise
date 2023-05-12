package com.exercise;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exercise.springboot.entity.User;
import com.exercise.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SampleTest {


    @Autowired
    private IUserService userService;

    @org.junit.Test
    public void test1() {
        // 插入新记录
        User user = new User();
        //User.setId(1L);
        user.setEmail("test66@baomidou.com");
        user.setUsername("user1");
        user.setNickname("Tom");
        userService.save(user);
        // 或者
//        userService.saveOrUpdate(user);
        // 更新完成后，User对象的id会被补全
        log.info("User={}", user.toString());

    }

    @org.junit.Test
    public void test2() {
        // 通过主键id查询
        User User = userService.getById(1);
        log.info("User={}", User.toString());
    }

    @org.junit.Test
    public void test3() {
        // 条件查询，下面相当于xml中的 select * from mp_user where nickname = 'Tom' limit 1
        User user = userService.getOne(new QueryWrapper<User>().eq("nickname", "Tom").last("limit 1"));
        log.info("User={}", user.toString());
        // 批量查询
        List<User> UserList = userService.list();
        log.info("------------------------------all");
        UserList.forEach(i->{
            log.info("{}", i.toString());
        });
        // 分页查询 
        int pageNum = 1;
        int pageSize = 10;
        IPage<User> UserIPage = userService.page(new Page<>(pageNum, pageSize), new QueryWrapper<User>().eq("status", 1));
        // IPage to List
        List<User> UserList1 = UserIPage.getRecords();
        log.info("------------------------------page");
        UserList1.forEach(i->{
            log.info("{}", i.toString());
        });
        // 总页数
        long allPageNum = UserIPage.getPages();
        log.info("------------------------------allPageNum");
        log.info("{}", allPageNum);
    }

     @org.junit.Test
    public void test4() {
        User user = userService.getById(4);
        // 修改更新
         user.setMobile("123456");
        //userService.updateById(User);
        // 或者
         userService.saveOrUpdate(user);
        // 通过主键id删除
        userService.removeById(4);
    }
}

