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

public class PayCard {
    public static JFrame payCard;
    JLabel jlbMsg;

    public PayCard(AccountVo accountVo){
        if (jlbMsg != null){
            jlbMsg.setText("");
        }
        payCard = new JFrame();
        payCard.setTitle("消费管理");
        payCard.setSize(340,400);
        payCard.setLocationRelativeTo(null);
        payCard.setVisible(true);
        payCard.setLayout(new GridLayout(6,1));

        JPanel jpl1 = new JPanel();
        JLabel lblUserName = new JLabel("当 前 用 户：");
        JTextField jtf1 = new JTextField(10);
        jtf1.setText(accountVo.getUserName());
        jtf1.setEditable(false);
        jpl1.add(lblUserName);
        jpl1.add(jtf1);
        payCard.add(jpl1);

        JPanel jpl2 = new JPanel();
        JLabel lblCreditPresent = new JLabel("预 存 金 额：￥");
        JTextField jtf2 = new JTextField(10);
        jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
        jtf2.setEditable(false);
        jpl2.add(lblCreditPresent);
        jpl2.add(jtf2);
        payCard.add(jpl2);

        JPanel jpl3 = new JPanel();
        JLabel lblCreditTable = new JLabel("可用信用额：￥");
        JTextField jtf3 = new JTextField(10);
        jtf3.setText(String.valueOf(accountVo.getCreditAble()));
        jtf3.setEditable(false);
        jpl3.add(lblCreditTable);
        jpl3.add(jtf3);
        payCard.add(jpl3);

        JPanel jpl4 = new JPanel();
        JLabel lblCreditTotal = new JLabel("总可消费额：￥");
        JTextField jtf4 = new JTextField(10);
        jtf4.setText(String.valueOf(accountVo.getCreditAble() + accountVo.getCreditPresent()));
        jtf4.setEditable(false);
        jpl4.add(lblCreditTotal);
        jpl4.add(jtf4);
        payCard.add(jpl4);

        JPanel jpl5 = new JPanel();
        JLabel lblPay = new JLabel("本 次 消 费：￥");
        JTextField jtfPay = new JTextField(10);
        jpl5.add(lblPay);
        jpl5.add(jtfPay);
        payCard.add(jpl5);

        JPanel jpl6 = new JPanel();
        JButton btnOk = new JButton("确定");
        JButton btnBack = new JButton("返回");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money = jtfPay.getText().trim();
                if ("".equals(money)) {
                    JOptionPane.showMessageDialog(payCard, "请输入本次消费金额！");
                    return;
                }

                double payMoney = Double.parseDouble(jtfPay.getText().trim());

                if (payMoney > 0){
                    if (payMoney <= accountVo.getCreditAble() + accountVo.getCreditPresent()) {  //消费额不得超过可消费额
                        if (accountVo.getCreditPresent() >= payMoney) {
                            //如果消费金额小于预存金额，则没有动用信用额，相当于储蓄卡，只减少预存余额
                            accountVo.setCreditPresent(accountVo.getCreditPresent() - payMoney);//减少预存金额
                        } else {//否则动用了信用额，相关数据都要改变
                            double overMoney = payMoney - accountVo.getCreditPresent(); //这是超出预存金额部分
                            accountVo.setCreditAble(accountVo.getCreditAble() - overMoney);//减少可用信用额
                            accountVo.setCreditCash(accountVo.getCreditAble() / 1.1); //重新计算可提现金额
                            accountVo.setCreditOwer(accountVo.getCreditOwer() + overMoney);  //增加欠款
                            accountVo.setCreditPresent(0);   //预存金额设为0
                        }

                        ObjectStreamUtil.saveAccountList(Data.accountList);
                        //创建账单，1代表账单类型为消费
                        BillVoUtil.createBill(accountVo.getUserName(), payMoney, accountVo.getCardNo(), 1);

                        JOptionPane.showMessageDialog(payCard, "消费成功！");

                        //更新数据
                        jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
                        jtf3.setText(String.valueOf(accountVo.getCreditAble()));
                        jtf4.setText(String.valueOf(accountVo.getCreditAble() + accountVo.getCreditPresent()));
                    } else {
                        JOptionPane.showMessageDialog(payCard, "本次消费额不可超过总消费额！");
                    }

                }else {
                    JOptionPane.showMessageDialog(payCard,"消费金额必须大于0！");
                }

                jtfPay.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardManage.cardManage.setVisible(true);
                payCard.dispose();
            }
        });

        jpl6.add(btnOk);
        jpl6.add(btnBack);
        payCard.add(jpl6);

        //让消费金额获得焦点
        payCard.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                jtfPay.requestFocus();
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
