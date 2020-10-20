package com.qf.service.impl;

import com.qf.dao.UserMapper;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User lookUser(String email) {
        return userMapper.lookUser(email);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
