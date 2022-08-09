package card;

import entity.AccountVo;
import entity.Data;
import utils.BillVoUtil;
import utils.ObjectStreamUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CashCard {
    public static JFrame cashCard;
    JLabel jlbMsg;

    public CashCard(AccountVo accountVo){
        if (jlbMsg != null){
            jlbMsg.setText("");
        }

        cashCard = new JFrame();
        cashCard.setTitle("取现管理");
        cashCard.setSize(340,400);
        cashCard.setLocationRelativeTo(null);
        cashCard.setVisible(true);
        cashCard.setVisible(true);
        cashCard.setLayout(new GridLayout(6,1));

        JPanel jpl1 = new JPanel();
        JLabel lblUserName = new JLabel("当前用户：");
        JTextField jtf1 = new JTextField(10);
        jtf1.setText(accountVo.getUserName());
        jtf1.setEditable(false);
        jpl1.add(lblUserName);
        jpl1.add(jtf1);
        cashCard.add(jpl1);

        JPanel jpl2 = new JPanel();
        JLabel lblCreditPresent = new JLabel("预存金额：￥");
        JTextField jtf2 = new JTextField(10);
        jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
        jtf2.setEditable(false);
        jpl2.add(lblCreditPresent);
        jpl2.add(jtf2);
        cashCard.add(jpl2);

        JPanel jpl3 = new JPanel();
        JLabel lblCreditTable = new JLabel("可用信用额：￥");
        JTextField jtf3 = new JTextField(10);
        jtf3.setText(String.valueOf(accountVo.getCreditAble()));
        jtf3.setEditable(false);
        jpl3.add(lblCreditTable);
        jpl3.add(jtf3);
        cashCard.add(jpl3);

        JPanel jpl4 = new JPanel();
        JLabel lblCreditCash = new JLabel("可取现金额：￥");
        JTextField jtf4 = new JTextField(10);
        jtf4.setText(String.valueOf(accountVo.getCreditCash()));
        jtf4.setEditable(false);
        jpl4.add(lblCreditCash);
        jpl4.add(jtf4);
        cashCard.add(jpl4);

        JPanel jpl5 = new JPanel();
        JLabel lblCash = new JLabel("取现金额：￥");
        JTextField jtfCash = new JTextField(10);
        jpl5.add(lblCash);
        jpl5.add(jtfCash);
        cashCard.add(jpl5);

        JPanel jpl6 = new JPanel();
        JButton btnOk = new JButton("确定");
        JButton btnBack = new JButton("返回");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money = jtfCash.getText().trim();
                if ("".equals(money)){
                    JOptionPane.showMessageDialog(cashCard,"请输入本次取现金额！");
                    return;
                }

                double cashMoney = Double.parseDouble(jtfCash.getText().trim());


                /*if ("开通".equals(accountVo.getState())){
                    if (cashMoney > 0){  //取现金额必须大于0
                        if (cashMoney <= accountVo.getCreditCash()){ //取现金额不得超过可取金额
                            if (accountVo.getCreditPresent() >= cashMoney){
                                //如果取现金额小于预存金额，则直接减少预存余额，不影响信用额
                                accountVo.setCreditPresent(accountVo.getCreditPresent() - cashMoney);//减少预存金额
                                accountVo.setCreditCash(accountVo.getCreditAble() / 1.1 + accountVo.getCreditPresent());//重新计算可提现金额
                            }else{//否则动用了信用额，相关数据都要改变
                                double overMoney = cashMoney - accountVo.getCreditPresent(); //这是超出预存金额部分
                                accountVo.setCreditAble(accountVo.getCreditAble() - overMoney);//减少可用信用额
                                accountVo.setCreditCash(accountVo.getCreditAble() / 1.1); //重新计算可提现金额
                                accountVo.setCreditOwer(accountVo.getCreditOwer() + overMoney);  //增加欠款
                                accountVo.setCreditPresent(0);   //预存金额设为0
                            }

                            ObjectStreamUtil.saveAccountList(Data.accountList);
                            //创建账单，3代表账单类型为取现
                            BillVoUtil.createBill(accountVo.getUserName(),cashMoney,accountVo.getCardNo(),3);

                            JOptionPane.showMessageDialog(cashCard,"取现成功！");

                            //更新数据
                            jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
                            jtf3.setText(String.valueOf(accountVo.getCreditAble()));
                            jtf4.setText(String.valueOf(accountVo.getCreditCash()));
                        }else {
                            JOptionPane.showMessageDialog(cashCard,"取现失败，取现额超过可取现额！");
                        }
                    }else {
                        JOptionPane.showMessageDialog(cashCard,"取现失败，取现额必须大于0！");
                    }
                }else {
                    JOptionPane.showMessageDialog(cashCard,"对不起，您的账号已被冻结，取现失败！");
                }*/


                if (cashMoney > 0){  //取现金额必须大于0
                    if (cashMoney <= accountVo.getCreditCash()){ //取现金额不得超过可取金额
                        if (accountVo.getCreditPresent() >= cashMoney){
                            //如果取现金额小于预存金额，则直接减少预存余额，不影响信用额
                            accountVo.setCreditPresent(accountVo.getCreditPresent() - cashMoney);//减少预存金额
                            accountVo.setCreditCash(accountVo.getCreditAble() / 1.1 + accountVo.getCreditPresent());//重新计算可提现金额
                        }else{//否则动用了信用额，相关数据都要改变
                            double overMoney = cashMoney - accountVo.getCreditPresent(); //这是超出预存金额部分
                            accountVo.setCreditAble(accountVo.getCreditAble() - overMoney);//减少可用信用额
                            accountVo.setCreditCash(accountVo.getCreditAble() / 1.1); //重新计算可提现金额
                            accountVo.setCreditOwer(accountVo.getCreditOwer() + overMoney);  //增加欠款
                            accountVo.setCreditPresent(0);   //预存金额设为0
                        }

                        ObjectStreamUtil.saveAccountList(Data.accountList);
                        //创建账单，3代表账单类型为取现
                        BillVoUtil.createBill(accountVo.getUserName(),cashMoney,accountVo.getCardNo(),3);

                        JOptionPane.showMessageDialog(cashCard,"取现成功！");

                        //更新数据
                        jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
                        jtf3.setText(String.valueOf(accountVo.getCreditAble()));
                        jtf4.setText(String.valueOf(accountVo.getCreditCash()));
                    }else {
                        JOptionPane.showMessageDialog(cashCard,"取现失败，取现额超过可取现额！");
                    }
                }else {
                    JOptionPane.showMessageDialog(cashCard,"取现失败，取现额必须大于0！");
                }
                jtfCash.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardManage.cardManage.setVisible(true);
                cashCard.dispose();
            }
        });

        jpl6.add(btnOk);
        jpl6.add(btnBack);
        cashCard.add(jpl6);

        //让消费金额获得焦点
        cashCard.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                jtfCash.requestFocus();
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
