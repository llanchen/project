package com.seehope.service;

import com.seehope.dao.MsoMapper;
import com.seehope.entity.Mso;
import com.seehope.entity.Msoxq;
import com.seehope.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MsoService {
    SqlSession sqlSession = MyBatisUtil.getSession();
    MsoMapper msoMapper=sqlSession.getMapper(MsoMapper.class);

    public List<Mso> findAllMsos(){
        return msoMapper.findAllMsos();
    }

    public Mso findMsoByMsoid(String msoid){
        return msoMapper.findMsoByMsoid(msoid);
    }

    public void delMso(String msoid){
        msoMapper.delMsoxq(msoid);
        msoMapper.delMso(msoid);
    }

    public void updateMsoDeliveryState(String msoid){
        msoMapper.updateMsoDeliveryState(msoid);
    }


    public ByteArrayOutputStream getExcel(){
        //1、获取到对应的Excel文件，工作薄文件
        Workbook wb=new XSSFWorkbook();
        //2、创建工作表
        Sheet s=wb.createSheet("订单数据文件");
        //设置通用配置
        //s.setColumnWidth(4,100);
        CellStyle cs_field=wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);

        //制作标题
        s.addMergedRegion(new CellRangeAddress(1,1,1,9));
        Row row_1=s.createRow(1);
        Cell cell_1_1=row_1.createCell(1);
        cell_1_1.setCellValue("订单信息列表");

        //创建一个样式
        CellStyle cs_title=wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        cell_1_1.setCellStyle(cs_title);

        //制作表头
        String[] fields={"订单ID","收货人","订单金额","收货人电话","订单日期","支付状态","收获地址",
                "用户ID","物流状态"};
        Row row_2=s.createRow(2);
        for (int i=0;i<fields.length;i++){
            Cell cell_2_temp=row_2.createCell(1+i);
            cell_2_temp.setCellValue(fields[i]);
            cell_2_temp.setCellStyle(cs_field);
        }

        //制作数据区
        int row_index=0;
        MsoService msoService=new MsoService();
        List<Mso> msolist=msoService.findAllMsos();
        for (Mso mso:msolist){
            int cell_index=0;
            Row row_temp=s.createRow(3+row_index++);

            Cell cell_data_1=row_temp.createCell(1+cell_index++);
            cell_data_1.setCellValue(mso.getMsoid());
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2=row_temp.createCell(1+cell_index++);
            cell_data_2.setCellValue(mso.getMsoname());
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3=row_temp.createCell(1+cell_index++);
            cell_data_3.setCellValue(mso.getMsomoney());
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4=row_temp.createCell(1+cell_index++);
            cell_data_4.setCellValue(mso.getTelephone());
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5=row_temp.createCell(1+cell_index++);
            cell_data_5.setCellValue(mso.getMsoTime());
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6=row_temp.createCell(1+cell_index++);
            cell_data_6.setCellValue(mso.getPaystate());
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_8=row_temp.createCell(1+cell_index++);
            cell_data_8.setCellValue(mso.getUserid());
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9=row_temp.createCell(1+cell_index++);
            cell_data_9.setCellValue(mso.getDeliveryState());
            cell_data_9.setCellStyle(cs_field);
        }

        //创建一个文件对象，作为excel文件内容的输出文件
        //File f=new File("test.xlsx");
        //输出时通过流的形式对外输出，包装对于的目标文件
        //OutputStream os=new FileOutputStream(f);
        //将内存中workbook数据写入到流中
        //wb.write(os);
        //wb.close();
        //os.close();

        //将内存中的workbook数据写入到流中
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        try {
            wb.write(os);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return os;
    }


}
