package com.seehope.servlet;

import com.seehope.service.MsoService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/excelServlet")
public class ExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 返回的数据类型为文件xlsx类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName=new String("订单信息列表.xlsx".getBytes(),"iso8859-1");
        response.addHeader("Content-Disposition","attachment;fileName="+fileName);

        MsoService msoService=new MsoService();
        // 生成报告的文件，然后传递到前端页面
        ByteArrayOutputStream os=msoService.getExcel();
        // 获取产生的相应的流对象
        ServletOutputStream sos=response.getOutputStream();
        // 将数据从原始的字节流对象中提取出来写入到servlet对应的输出流中
        os.writeTo(sos);
        // 将输出流刷新
        sos.flush();
        os.close();
    }
}
