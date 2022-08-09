package utils;

import entity.AccountVo;
import entity.BillVo;
import entity.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamUtil {

    //将泛型集合List<AccountVo>作为对象，用对象流存入文件data/account.txt
    public static void saveAccountList(List<AccountVo> list) {
        ObjectOutputStream oops = null;
        try { //覆盖模式
            OutputStream fos = new FileOutputStream("data/account.txt",false);
            oops = new ObjectOutputStream(fos);
            oops.writeObject(list);
        } catch (IOException e) {

        }finally {
            try {
                oops.flush();
                oops.close();
            } catch (IOException e) {

            }
        }
    }

    //将泛型集合List<BillVo>作为对象，用对象流存入文件data/bill.txt
    public static void saveBillList(List<BillVo> list) {
        ObjectOutputStream oops = null;
        try { //覆盖模式
            oops = new ObjectOutputStream(new FileOutputStream("data/bill.txt",false));
            oops.writeObject(list);

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oops.flush();
                oops.close();
            } catch (IOException e) {

            }
        }
    }


    //从文件中读取对象（该文件存储的对象为泛型集合List<BillVo>）
    public static List<BillVo> getBillList() {
        List<BillVo> list = new ArrayList<BillVo>();
        try {
            InputStream fis = new FileInputStream("data/bill.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<BillVo>)ois.readObject();
        } catch (Exception e) {
            list = new ArrayList<BillVo>();
        }
        return list;
    }
    
    //从account.txt文件中读取对象流（该文件存储的对象为泛型集合List<AccountVo>）
    public static List<AccountVo> getAccountList(){
        List<AccountVo> list = new ArrayList<AccountVo>();

        try {
            InputStream fis = new FileInputStream("data/account.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (List<AccountVo>)ois.readObject();
        } catch (Exception e) {
            list = new ArrayList<AccountVo>();
        }
        return list;
    }

}
