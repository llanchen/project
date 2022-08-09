package card;

import entity.AccountVo;
import entity.Data;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLogin extends JFrame {
    //事先定义要用到的各个组件
    JPanel jp1, jp2, jp3;
    JLabel jlb1, jlb2;
    JButton jb1, jb2;
    JTextField jtf1;
    JPasswordField jpf1;
    public JFrame cardLogin = new JFrame();

    public CardLogin() {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jlb1 = new JLabel("卡  号");
        jlb2 = new JLabel("密  码");

        jb1 = new JButton("登  录");
        jb2 = new JButton("取  消");

        jtf1 = new JTextField(10);  //用户名框列数

        jpf1 = new JPasswordField(10);  //密码框列数
        cardLogin.setLayout(new GridLayout(3, 1));  //设置表格布局

        //向中间容器加入各个组件
        jp1.add(jlb1);
        jp1.add(jtf1);

        jp2.add(jlb2);
        jp2.add(jpf1);

        jp3.add(jb1);
        jp3.add(jb2);

        //加入到JFrame
        cardLogin.add(jp1);
        cardLogin.add(jp2);
        cardLogin.add(jp3);

        cardLogin.setSize(360, 200);
        cardLogin.setLocationRelativeTo(null);
        cardLogin.setVisible(true);
        cardLogin.setTitle("持卡人登录");

        //为登录按钮点击事件添加监听器
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtf1.getText().trim();
                String password = jpf1.getText().trim();
                JDialog d = new JDialog();
                d.setBounds(550, 320, 200, 100);

                if ("".equals(cardNo) || cardNo.isEmpty()) {
                    JOptionPane.showMessageDialog(cardLogin, "卡号不能为空", "", JOptionPane.WARNING_MESSAGE);
                } else if ("".equals(password) || password.isEmpty()) {
                    JOptionPane.showMessageDialog(cardLogin, "密码不能为空", "", JOptionPane.WARNING_MESSAGE);
                } else if (cardLogin(cardNo, password) != null) {
                    AccountVo accountVo = cardLogin(cardNo, password);
                    new CardManage(accountVo);
                    cardLogin.dispose();
                } else {
                    JOptionPane.showMessageDialog(cardLogin, "卡号或密码不正确！", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLogin.dispose();
                new Start();
            }
        });

        cardLogin.setVisible(true);
    }

    //遍历所有账户，查找用户名和密码都满足要求的那个账户
    public static AccountVo cardLogin(String cardNo, String password) {
        for (AccountVo account : Data.accountList) {
            if (account.getCardNo().equals(cardNo) && account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;

    }

}