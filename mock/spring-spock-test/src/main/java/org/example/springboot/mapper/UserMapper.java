package org.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboot.entity.User;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author genetor
 * @since 2023-04-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
