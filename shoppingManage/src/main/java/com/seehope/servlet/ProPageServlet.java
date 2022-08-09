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

@WebServlet("/proPage")
public class ProPageServlet extends HttpServlet {
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

        ProService b=new ProService();
        PageHelper.startPage(index,size);
        List<Pro> proList=b.findAllPros();
        PageInfo<Pro> page=new PageInfo<>(proList);

        request.setAttribute("page",page);
        CateService cateService=new CateService();
        List<Cate> cates=cateService.findCates();
        request.getSession().setAttribute("cates",cates);
        request.getRequestDispatcher("shoppingselect.jsp").forward(request,response);

    }
}
