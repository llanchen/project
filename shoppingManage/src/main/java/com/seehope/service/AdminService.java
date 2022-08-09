package com.seehope.service;

import com.seehope.dao.AdminMapper;
import com.seehope.entity.Admin;
import com.seehope.entity.User;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminService {
    SqlSession sqlSession= MyBatisUtil.getSession();
    AdminMapper adminMapper=sqlSession.getMapper(AdminMapper.class);


    public Admin login(String username, String password){
        return   adminMapper.login(username, password);
    }

    public List<Admin> findAllAdmins() {
        return adminMapper.findAllAdmins();
    }

    public boolean addAdmin(Admin admin){
        int row=0;
        row=adminMapper.addAdmin(admin);
        sqlSession.commit();
        return row >0 ?true:false;
    }

    public Admin findAdminByName(String adminname){
        return adminMapper.findAdminByName(adminname);
    }
}
