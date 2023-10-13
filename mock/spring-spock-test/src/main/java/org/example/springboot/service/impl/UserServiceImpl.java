package org.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.entity.User;
import org.example.springboot.mapper.UserMapper;
import org.example.springboot.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User get(Integer id) {
        return this.getById(id);
    }

    @Override
    public User add(User user) {
        this.save(user);
        return user;
    }

    @Override
    public User modifyById(User user) {
        updateById(user);
        return this.getById(user.getId());
    }

    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    public List<User> listAll(User user) {
        return this.list(new LambdaQueryWrapper<User>()
                .eq(user.getId()!=null, User::getId, user.getId())
                .eq(StrUtil.isNotBlank(user.getUsername()), User::getUsername, user.getUsername())
        );
    }
}
