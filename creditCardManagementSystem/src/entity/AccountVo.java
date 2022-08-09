package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//模拟信用卡对应一个持卡人
public class AccountVo implements Serializable {
    private String cardNo;              //账户（卡号）
    private String Password;            //密码
    private String userName;            //姓名
    private Date settlementDate;        //结算日期
    private double creditTotal;         //总信用额（总信用额=可用信用额+预存金额）
    private double creditAble;          //可用信用额
    private double creditCash;          //可取现额度（可取现额度=可用信用额/1.1）
    private  double creditOwer;         //欠款额
    private  double creditPresent;      //预存金额
    private int state;                  //状态（1：开通  0：冻结）

    public AccountVo(){
        super();
    }
    public AccountVo(String cardNo, String password, String userName, Date settlementDate,
                     double creditCash, double creditOwer, double creditPresent, int state) {
        super();
        this.cardNo = cardNo;
        Password = password;
        this.userName = userName;
        this.settlementDate = settlementDate;
        this.creditCash = creditCash;
        this.creditOwer = creditOwer;
        this.creditPresent = creditPresent;
        this.state = state;
    }


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(double creditTotal) {
        this.creditTotal = creditTotal;
    }

    public double getCreditAble() {
        return creditAble;
    }

    public void setCreditAble(double creditAble) {
        this.creditAble = creditAble;
    }

    public double getCreditCash() {
        return creditCash;
    }

    public void setCreditCash(double creditCash) {
        this.creditCash = creditCash;
    }

    public double getCreditOwer() {
        return creditOwer;
    }

    public void setCreditOwer(double creditOwer) {
        this.creditOwer = creditOwer;
    }

    public double getCreditPresent() {
        return creditPresent;
    }

    public void setCreditPresent(double creditPresent) {
        this.creditPresent = creditPresent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountVo accountVo = (AccountVo) o;
        return Double.compare(accountVo.creditTotal, creditTotal) == 0 &&
                Double.compare(accountVo.creditAble, creditAble) == 0 &&
                Double.compare(accountVo.creditCash, creditCash) == 0 &&
                Double.compare(accountVo.creditOwer, creditOwer) == 0 &&
                Double.compare(accountVo.creditPresent, creditPresent) == 0 &&
                state == accountVo.state &&
                Objects.equals(cardNo, accountVo.cardNo) &&
                Objects.equals(Password, accountVo.Password) &&
                Objects.equals(userName, accountVo.userName) &&
                Objects.equals(settlementDate, accountVo.settlementDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, Password, userName, settlementDate, creditTotal, creditAble, creditCash, creditOwer, creditPresent, state);
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "cardNo='" + cardNo + '\'' +
                ", Password='" + Password + '\'' +
                ", userName='" + userName + '\'' +
                ", settlementDate=" + settlementDate +
                ", creditTotal=" + creditTotal +
                ", creditAble=" + creditAble +
                ", creditCash=" + creditCash +
                ", creditOwer=" + creditOwer +
                ", creditPresent=" + creditPresent +
                ", state=" + state +
                '}';
    }
}
