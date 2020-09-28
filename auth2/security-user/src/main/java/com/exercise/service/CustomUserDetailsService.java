package com.exercise.service;

import com.exercise.dao.UserDao;
import com.exercise.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto bean = userDao.getUserByUsername(s);
        if (bean == null)
            return null;
        List<String> permissions = userDao.findPermissionByUserId(bean.getId());
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(bean.getUsername()).password(bean.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}