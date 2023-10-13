package org.example.springboot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
public interface IUserService extends IService<User> {

    User get(Integer id);

    User add(User user);

    User modifyById(User user);

    boolean delete(Integer id);

    List<User> listAll(User user);
}
