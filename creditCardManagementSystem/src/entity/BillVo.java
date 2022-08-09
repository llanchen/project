package entity;

import java.io.Serializable;
import java.util.Objects;

//模拟账单
public class BillVo implements Serializable {
    private String billNo;      //账单号
    private String userName;    //姓名
    private String cardNo;      //账户
    private double amount;      //值
    private int type;           //类型：1、消费  2、还款  3、取现

    public BillVo(){
        super();
    }
    public BillVo(String billNo, String userName, String cardNo, double amount, int type) {
        super();
        this.billNo = billNo;
        this.userName = userName;
        this.cardNo = cardNo;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillVo billVo = (BillVo) o;
        return Double.compare(billVo.amount, amount) == 0 &&
                type == billVo.type &&
                Objects.equals(billNo, billVo.billNo) &&
                Objects.equals(userName, billVo.userName) &&
                Objects.equals(cardNo, billVo.cardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, userName, cardNo, amount, type);
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
