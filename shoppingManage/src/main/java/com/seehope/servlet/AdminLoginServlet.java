package com.seehope.servlet;

import com.seehope.entity.Admin;
import com.seehope.service.AdminService;
import com.seehope.util.Md5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService as=new AdminService();

        //获得输入的用户名和密码
        Md5Utils md5 = new Md5Utils();
        String username = request.getParameter("username");//admin
        String password = md5.getMD5(request.getParameter("password"));  //123456

        System.out.println("username:"+username+",password"+password);
        Admin ad=as.login(username,password);

        if (ad==null){
            response.sendRedirect("login.jsp");
            throw new RuntimeException("Error");
        }else {
            request.getSession().setAttribute("admin",ad);
            response.sendRedirect(request.getContextPath()+"/showAdministator.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
