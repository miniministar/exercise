package com.exercise.dao;

import com.exercise.model.PermissionDto;
import com.exercise.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String usernmae)
    {
        String sql = "select id,username,password,fullname from sys_user where username = ?";
        List<UserDto> result = jdbcTemplate.query(sql, new Object[]{usernmae}, new BeanPropertyRowMapper<>(UserDto.class));
        if (result== null && result.size() <= 0)return null;
        return result.get(0);
    }

    // 根据用户id 查询用户权限
    public List<String> findPermissionByUserId(String userId)
    {
        String sql = "select id , permission as code, permission as url, name as description from sys_permission where id in (" +
                "select permission_id from sys_role_permission where role_id in (" +
                "select role_id from sys_role_user where user_id = ?" +
                ")" +
                ")";
        List<PermissionDto> result = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<String>();
        result.forEach( p->permissions.add(p.getCode()) );
        return permissions;

    }
}
