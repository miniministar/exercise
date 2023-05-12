package com.exercise.springboot.service.impl;

import com.exercise.springboot.entity.User;
import com.exercise.springboot.mapper.UserMapper;
import com.exercise.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@CacheConfig(cacheNames = {"user"})
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Cacheable(key = "#id")
    @Override
    public User get(Integer id) {
        return this.getById(id);
    }

    @Override
    public User add(User user) {
        this.save(user);
        return user;
    }

    @CachePut(key = "#user.id")
    @Override
    public User modifyById(User user) {
        updateById(user);
        return getById(user.getId());
    }

    @CacheEvict(key = "#id")
    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }
}
