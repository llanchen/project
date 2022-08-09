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
import java.io.ObjectInputStream;

public class ReturnCard {
    public static JFrame returnCard;
    JLabel jlbMsg;

    public ReturnCard(AccountVo accountVo){
        if (jlbMsg != null){
            jlbMsg.setText("");
        }

        returnCard = new JFrame();
        returnCard.setTitle("还款管理");
        returnCard.setSize(340,400);
        returnCard.setLocationRelativeTo(null);
        returnCard.setVisible(true);
        returnCard.setLayout(new GridLayout(6,1));

        JPanel jpl1 = new JPanel();
        JLabel lblUserName = new JLabel("当前用户：");
        JTextField jtf1 = new JTextField(10);
        jtf1.setText(accountVo.getUserName());
        jtf1.setEditable(false);
        jpl1.add(lblUserName);
        jpl1.add(jtf1);
        returnCard.add(jpl1);

        JPanel jpl2 = new JPanel();
        JLabel lblCreditPresent = new JLabel("预存金额：￥");
        JTextField jtf2 = new JTextField(10);
        jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
        jtf2.setEditable(false);
        jpl2.add(lblCreditPresent);
        jpl2.add(jtf2);
        returnCard.add(jpl2);

        JPanel jpl3 = new JPanel();
        JLabel lblCreditTable = new JLabel("可信用额：￥");
        JTextField jtf3 = new JTextField(10);
        jtf3.setText(String.valueOf(accountVo.getCreditAble()));
        jtf3.setEditable(false);
        jpl3.add(lblCreditTable);
        jpl3.add(jtf3);
        returnCard.add(jpl3);

        JPanel jpl4 = new JPanel();
        JLabel lblCreditOwer = new JLabel("欠款金额：￥");
        JTextField jtf4 = new JTextField(10);
        jtf4.setText(String.valueOf(accountVo.getCreditOwer()));
        jtf4.setEditable(false);
        jpl4.add(lblCreditOwer);
        jpl4.add(jtf4);
        returnCard.add(jpl4);

        JPanel jpl5 = new JPanel();
        JLabel lblReturn = new JLabel("还款金额：￥");
        JTextField jtfReturn = new JTextField(10);
        jpl5.add(lblReturn);
        jpl5.add(jtfReturn);
        returnCard.add(jpl5);

        JPanel jpl6 = new JPanel();
        JButton btnOk = new JButton("确定");
        JButton btnBack = new JButton("返回");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money = jtfReturn.getText().trim();
                if ("".equals(money)){
                    JOptionPane.showMessageDialog(returnCard,"请输入还款金额！");
                    return;
                }
                double returnMoney = Double.parseDouble(jtfReturn.getText().trim());
                if (returnMoney > 0){  //还款金额必须大于0
                    if (returnMoney > accountVo.getCreditOwer()){
                        //如果还款额大于欠款金额，则多出的钱转为预存金额，欠款金额清0
                        double overMoney = returnMoney - accountVo.getCreditOwer(); //超出欠款部分的钱
                        accountVo.setCreditPresent(overMoney + accountVo.getCreditPresent());//加到预存金额中
                        accountVo.setCreditOwer(0);  //欠款清0
                        accountVo.setCreditAble(accountVo.getCreditTotal()); //可用信用额恢复为最大值
                        accountVo.setCreditCash(accountVo.getCreditAble() / 1.1 + accountVo.getCreditPresent());//重新计算可提现金额
                    }else {
                        accountVo.setCreditOwer(accountVo.getCreditOwer() - returnMoney); //减少欠款
                        accountVo.setCreditAble(accountVo.getCreditAble() + returnMoney); //增加可用信用额
                        accountVo.setCreditCash(accountVo.getCreditCash() / 1.1 + accountVo.getCreditPresent());//重新计算可提现金额
                    }

                    ObjectStreamUtil.saveAccountList(Data.accountList);
                    //创建账单，2代表账单类型为还款
                    BillVoUtil.createBill(accountVo.getUserName(),returnMoney,accountVo.getCardNo(),2);
                    JOptionPane.showMessageDialog(returnCard,"还款成功！");

                    //更新数据
                    jtf2.setText(String.valueOf(accountVo.getCreditPresent()));
                    jtf3.setText(String.valueOf(accountVo.getCreditAble()));
                    jtf4.setText(String.valueOf(accountVo.getCreditOwer()));
                }else {
                    JOptionPane.showMessageDialog(returnCard,"还款额必须大于0！");
                }
                jtfReturn.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardManage.cardManage.setVisible(true);
                returnCard.dispose();
            }
        });

        jpl6.add(btnOk);
        jpl6.add(btnBack);
        returnCard.add(jpl6);

        //让还款金额获得焦点
        returnCard.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                jtfReturn.requestFocus();
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
