package com.seehope.dao;

import com.seehope.entity.Admin;
import com.seehope.entity.User;

import java.util.List;

public interface AdminMapper {
     Admin login(String username,String password);

     List<Admin> findAllAdmins();

    // 通过管理员称查找管理员
     Admin findAdminByName(String adminname);

     int addAdmin(Admin admin);
}
