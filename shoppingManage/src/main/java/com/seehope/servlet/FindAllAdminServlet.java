package com.seehope.servlet;

import com.seehope.entity.Admin;
import com.seehope.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAllAdminsServlet")
public class FindAllAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService adminService = new AdminService();
        List<Admin> adminList = adminService.findAllAdmins();
        request.setAttribute("adminList", adminList);
        request.getRequestDispatcher("Adminselect.jsp").forward(request, response);
    }
}
