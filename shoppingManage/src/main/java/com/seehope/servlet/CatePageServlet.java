package com.seehope.servlet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seehope.entity.Cate;
import com.seehope.entity.Pro;
import com.seehope.service.CateService;
import com.seehope.service.ProService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catePage")
public class CatePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        int index=1;
        int size=5;

        String changeIndex =request.getParameter("pi");
        if (changeIndex!=null){
            index=Integer.parseInt(changeIndex);
        }

        CateService b=new CateService();
        PageHelper.startPage(index,size);
        List<Cate> cateList=b.findCates();
        PageInfo<Cate> page=new PageInfo<>(cateList);
        request.setAttribute("page",page);

        request.getRequestDispatcher("shoppingcateselect.jsp").forward(request,response);
    }
}
