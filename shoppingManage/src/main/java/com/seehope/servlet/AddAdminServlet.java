package com.seehope.servlet;

import com.seehope.entity.Admin;
import com.seehope.entity.User;
import com.seehope.service.AdminService;
import com.seehope.service.UserService;
import com.seehope.util.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminServlet")
public class AddAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("UTF-8");

        String adminname=request.getParameter("adminname");
        String adminpwd=request.getParameter("adminpwd");


        //将Admin传递给service层
        AdminService adminService=new AdminService();
        Admin admin=adminService.findAdminByName(adminname);

        if (ad)



        if (adminname.equals(service.findAdminByName(adminname))){
            System.out.println("the admin is ");
            response.getWriter().print("用户名重复");
        }else {
         *//*   Admin admin= new Admin();
            admin.setAdminname(adminname);
            admin.setAdminpwd(Md5Utils.getMD5(adminpwd));*//*
        }


       *//* //数据回调
        request.setAttribute("adminname",adminname);
        request.setAttribute("adminpwd",adminpwd);*/

    }
}
