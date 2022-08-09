package com.seehope.dao;

import com.seehope.entity.User;

import java.util.List;

public interface UserMapper {
    //添加注册成功的用户
     int addUser(User user);

    // 通过用户名称查找用户
     User findUserByName(String username);


    User findUserByNameAndPassword(String username, String password);

    List<User> findAllUsers();

    void updateUser(User user);
}
