package com.seehope.servlet;

import com.seehope.entity.Pro;
import com.seehope.service.ProService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/addPro")
public class AddProServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加
        request.setCharacterEncoding("utf-8");
        ProService pros=new ProService();
        String uploadFileName="";  //上传的文件名
        String strName="";   //表单字段元素的name属性值
        Pro p=new Pro();

        //请求信息中的内容是否是multipart类型
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);

        if (isMultipart){
            //创建文件的工厂
            DiskFileItemFactory factory=new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024*50);
            //factory.setRepository(new File("E:\\mtemp\\"));
            //执行上传的对象

            ServletFileUpload upload=new ServletFileUpload(factory);

            try {
                //upload.setFileSizeMax(50*1024*1024); //表示单个文件最大不能超过50M
                //解析form表单中所有文件
                List<FileItem> items=upload.parseRequest(request);
                Iterator<FileItem> iter=items.iterator();

                while (iter.hasNext()){  //依次处理每个表单项
                    FileItem item=(FileItem) iter.next();
                    if (item.isFormField()){  //普通表单字段
                        strName=item.getFieldName();  //表单字段的name属性值
                        if (strName.equals("pName")){
                            //获取表单字段的值
                            String pName=item.getString("utf-8");
                            p.setpName(pName);
                        }else if (strName.equals("pSn")){
                            //获取表单的值
                            String pSn=item.getString("utf-8");
                            p.setpSn(pSn);
                        }else if (strName.equals("pNum")){
                            //获取表单的数量
                            String pNum=item.getString("utf-8");
                            int pNums=Integer.parseInt(pNum);
                            p.setpNum(pNums);
                        }else if (strName.equals("mPrice")){
                            //获取表单的mPrice
                            String mPrice=item.getString("utf-8");
                            double mPrices=Double.valueOf(mPrice.toString());
                            p.setmPrice(mPrices);
                        }else if (strName.equals("iPrice")){
                            //获取表单的iPrice
                            String iPrice=item.getString("utf-8");
                            double iPrices=Double.valueOf(iPrice.toString());
                            p.setiPrice(iPrices);
                        }else if (strName.equals("pDesc")){
                            //获取表单的pDesc
                            String pDesc=item.getString("utf-8");
                            p.setpDesc(pDesc);
                        }else if (strName.equals("pubTime")){
                            //获取表单的pubTime
                            String pubTime=item.getString("utf-8");
                            p.setPubTime(pubTime);
                        }else if (strName.equals("isShow")){
                            //获取表单的isShow
                            String isShow=item.getString("utf-8");
                            int isShows=Integer.parseInt(isShow);
                            p.setIsShow(isShows);
                        }else if (strName.equals("isHot")){
                            //获取表单的isHot
                            String isHot=item.getString("utf-8");
                            int isHots=Integer.parseInt(isHot);
                            p.setIsHot(isHots);
                        }else if (strName.equals("cId")){
                            //获取表单的cId
                            String cid=item.getString("utf-8");
                            int cids=Integer.parseInt(cid);
                            p.setCid(cids);
                        }
                    }else {  //文件表单字段
                        //文件的名字
                        String fileName1=item.getName();
                        fileName1= FilenameUtils.getName(fileName1);

                        if (fileName1!=null && !"".equals(fileName1)){
                            //获得当前项目的放商品图片的proimage文件夹的路径
                            String upload1FilePath=request.getServletContext().getRealPath("proimages");

                            //还要考虑有另外一个图片放在不同的路径，即文件夹xqImg下，通前端表单过的name属性进行区分
                            if (item.getFieldName().equals("xqImg")){
                                upload1FilePath=request.getServletContext().getRealPath("xqimg");
                                p.setXqImg("xqimg/"+fileName1);
                            }else {
                                //赋值
                                p.setpImg("proimages/"+fileName1);
                            }

                            /*System.out.println("upload1FilePath:"+upload1FilePath);
                            File saveFile=new File(upload1FilePath,fileName1);
                            item.write(saveFile);  //上传图片
*/

                            //接下来还要存一份图片到shopping商城项目
                            //获得shopping商城项目的图片文件夹路径
                            String upload1FilePath3=upload1FilePath.replaceAll("shoppingManage","shopping");
                            System.out.println("upload1FilePath3:"+upload1FilePath3);

                            //upload1FilePath是卖家后台项目AdminManage的图片路径，由于这个项目跟买家项目SeehopeShop是放在同一个服务器上，
                            // 所以通过上面的办法进行简单替换就可以获取到SeehopShop商城项目的图片文件夹路径
                            //还要存一份图片到SeehopeShop商城项目
                            File saveFile=new File(upload1FilePath3,fileName1);
                            //System.out.println(saveFile.getAbsolutePath());
                            item.write(saveFile);  //上传图片到shopping项目
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            pros.addPro(p);

      //      if (i>0){
                response.sendRedirect("proPage");
       //     }else {
        //        response.sendRedirect(request.getContextPath()+"/AddPro.jsp");
         //   }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
