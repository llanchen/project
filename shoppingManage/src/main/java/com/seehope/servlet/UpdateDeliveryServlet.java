package com.seehope.servlet;

import com.seehope.service.MsoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateDelivery")
public class UpdateDeliveryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String mosid=request.getParameter("msoid");
        System.out.println("updateDelivery:"+mosid);
        MsoService msoService=new MsoService();
        msoService.updateMsoDeliveryState(mosid);
        response.getWriter().print(1);
    }
}
