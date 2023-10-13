package org.example.springboot.controller;


import org.example.springboot.entity.User;
import org.example.springboot.result.PageQuery;
import org.example.springboot.result.PageResult;
import org.example.springboot.result.Result;
import org.example.springboot.service.IUserService;
import org.example.springboot.validate.Create;
import org.example.springboot.validate.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result<User> getById(Integer id) {
        return Result.success(service.get(id));
    }

    @RequestMapping(value = "/add")
    public Result<User> add(@RequestBody @Validated({Create.class}) User user) {
        return Result.success(service.add(user));
    }

    @RequestMapping(value = "/modifyById")
    public Result<User> modifyById(@RequestBody @Validated({Update.class}) User user) {
        return Result.success(service.modifyById(user));
    }

    @RequestMapping(value = "/delete")
    public Result<Boolean> delete(Integer id) {
        return Result.success(service.delete(id));
    }
    @RequestMapping(value = "/list")
    public PageResult<User> list(@RequestBody PageQuery<User> query) {
        query.startPage();
        List<User> list = service.listAll(query.getParams() == null ? new User(): query.getParams());
        PageResult<User> result = query.getPageResult(list);
        return result;
    }
}
