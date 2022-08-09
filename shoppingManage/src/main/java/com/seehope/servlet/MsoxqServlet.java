package com.seehope.servlet;

import com.seehope.entity.Mso;
import com.seehope.service.MsoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/msoxq")
public class MsoxqServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msoid=request.getParameter("msoid");
        MsoService msoService=new MsoService();
        Mso mso=msoService.findMsoByMsoid(msoid);
        request.setAttribute("mso",mso);
        request.getRequestDispatcher("Msodetails.jsp").forward(request,response);
    }
}
