package com.seehope.servlet;

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

//展示一个商品详情以便修改
@WebServlet("/toUpdatePro")
public class ToUpdateProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String proid=request.getParameter("id");
        int id=Integer.parseInt(proid);
        ProService proService=new ProService();
        Pro pro=proService.findId(id);
        CateService cateService=new CateService();
        List<Cate> cates=cateService.findCates();
        request.setAttribute("cates",cates);
        request.setAttribute("pro",pro);
        request.getRequestDispatcher("UpdatePro.jsp").forward(request,response);



    }
}
