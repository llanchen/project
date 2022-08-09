package com.seehope.servlet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seehope.entity.Cate;
import com.seehope.entity.User;
import com.seehope.service.CateService;
import com.seehope.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int index=1;
        int size=5;

        String changeIndex =request.getParameter("pi");
        if (changeIndex!=null){
            index=Integer.parseInt(changeIndex);
        }

        UserService b=new UserService();
        PageHelper.startPage(index,size);
        List<User> userList=b.findAllUsers();
        PageInfo<User> p=new PageInfo<>(userList);

        request.getSession().setAttribute("page",p);
        request.getRequestDispatcher("select.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
