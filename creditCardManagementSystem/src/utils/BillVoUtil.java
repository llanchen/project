package utils;

import entity.BillVo;
import entity.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BillVoUtil {
    public static Object[][] formBillListToArray(List<BillVo> billList) {
        Object[][] cells = new Object[billList.size()][5];
        for (int i = 0; i < billList.size(); i++){
            cells[i][0] = billList.get(i).getBillNo();
            cells[i][1] = billList.get(i).getUserName();
            cells[i][2] = billList.get(i).getCardNo();
            cells[i][3] = billList.get(i).getAmount();
            cells[i][4] = billList.get(i).getType();

            if (cells[i][4].toString().equals("1")){
                cells[i][4] = "消费";
            }else if (cells[i][4].toString().equals("2")){
                cells[i][4] = "还款";
            }else {
                cells[i][4] = "取现";
            }

        }

        return cells;
    }

    public static void createBill(String userName, double payMoney, String cardNo, int i) {
        //生成提现账单方法
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        String billvo = "P" + calendar.get(Calendar.YEAR) + "" + month + "" +  calendar.get(Calendar.DATE)+
                "" + calendar.get(Calendar.HOUR) + "" + calendar.get(Calendar.MINUTE) + "" +
                calendar.get(Calendar.SECOND);

        BillVo withdrawbill = new BillVo(billvo,userName,cardNo,payMoney,i);
        Data.billList.add(withdrawbill);
        ObjectStreamUtil.saveBillList(Data.billList);


    }
}
