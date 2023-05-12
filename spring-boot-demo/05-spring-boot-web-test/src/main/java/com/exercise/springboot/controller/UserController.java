package com.exercise.springboot.controller;


import com.exercise.springboot.entity.User;
import com.exercise.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@RestController
@RequestMapping("/springboot/user")
public class UserController {

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/getById")
    public User getById(Integer id) {
        return service.get(id);
    }

    @RequestMapping(value = "/add")
    public User add(User user) {
        return service.add(user);
    }

    @RequestMapping(value = "/modifyById")
    public User modifyById(User user) {
        return service.modifyById(user);
    }

    @RequestMapping(value = "/delete")
    public boolean delete(Integer id) {
        return service.delete(id);
    }
}
