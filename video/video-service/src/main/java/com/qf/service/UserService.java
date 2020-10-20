package com.qf.service;

import com.qf.pojo.User;

public interface UserService {
    User login(User user);
    int insert(User record);
    User lookUser(String email);
    void updateUser(User user);
}
