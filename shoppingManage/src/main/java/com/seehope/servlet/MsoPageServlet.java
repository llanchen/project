package com.seehope.servlet;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seehope.entity.Mso;
import com.seehope.service.MsoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/msoPage")
public class MsoPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int pageno=1;
        int pageSize=4;
        String changeIndex=request.getParameter("pi");
        if (changeIndex!=null){
            pageno=Integer.parseInt(changeIndex);
        }

        MsoService msoService=new MsoService();
        PageHelper.startPage(pageno,pageSize);
        List<Mso> msoList=msoService.findAllMsos();
        PageInfo<Mso> mpi=new PageInfo<>(msoList);
        request.setAttribute("mpi",mpi);
        request.getRequestDispatcher("shoppingMSO.jsp").forward(request,response);




    }
}
