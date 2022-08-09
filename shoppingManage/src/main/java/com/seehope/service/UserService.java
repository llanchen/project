package com.seehope.service;

import com.seehope.dao.UserMapper;
import com.seehope.entity.User;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class UserService {
    SqlSession sqlSession= MyBatisUtil.getSession();
    UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

    public boolean register(User user){
        int row=0;
        sqlSession.commit();
        return row>0?true:false;
    }

    public boolean checkUsername(String username){
        User user=userMapper.findUserByName(username);
        if (user==null){
            return false;
        }
        return true;
    }


    public User login(String username, String password){
        return findUserByNameAndPassword(username,password);
    }

    public List<User> findAllUsers(){
        return userMapper.findAllUsers();
    }

    public User findUserByNameAndPassword(String username, String password) {
        return   userMapper.findUserByNameAndPassword(username, password);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }


}
