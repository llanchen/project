package utils;

import entity.AccountVo;
import entity.Data;

import java.util.List;

public class AccountVoUtil {
    //集合转换为数组的方法
  public static Object[][] AccountListToArray(List<AccountVo> accountVoList){
      Object[][] cells = new Object[accountVoList.size()][9];
      for (int i = 0; i < accountVoList.size(); i++){
          cells[i][0] = accountVoList.get(i).getCardNo();
          cells[i][1] = accountVoList.get(i).getPassword();
          cells[i][2] = accountVoList.get(i).getUserName();
          cells[i][3] = accountVoList.get(i).getCreditTotal();
          cells[i][4] = accountVoList.get(i).getCreditAble();
          cells[i][5] = accountVoList.get(i).getCreditCash();
          cells[i][6] = accountVoList.get(i).getCreditOwer();
          cells[i][7] = accountVoList.get(i).getCreditPresent();
          cells[i][8] = accountVoList.get(i).getState();

          if (cells[i][8].toString().equals("1")){
              cells[i][8] = "开通";
          }else {
              cells[i][8] = "冻结";
          }

      }
      return cells;
  }


  //卡号重复判断
    public static boolean checkCardNo(String cardNo) {
      boolean check = false;
      for (AccountVo vo:Data.accountList){
          if (!"".equals(cardNo)&vo.getCardNo().contains(cardNo)){
              check = vo.getCardNo().contains(cardNo);
          }
      }
      return check;
    }

    //根据卡号查找账户
    public static AccountVo findAccountVoByCardNo(String cardNo) {
        for (AccountVo ac: Data.accountList){
            if (ac.getCardNo().equals(cardNo)){
                return ac;
            }
        }
        return null;
    }
}
