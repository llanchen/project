package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccount extends JFrame {
    public static JFrame bankAccount;

    public BankAccount(){
        bankAccount = new JFrame();
        bankAccount.setLayout(new FlowLayout());
        bankAccount.setTitle("账户资料管理");
        bankAccount.setSize(820,240);
        bankAccount.setLocationRelativeTo(null);
        bankAccount.setVisible(true);

        //创建5个按钮并添加到窗体
        ImageIcon img1 = new ImageIcon("images/add.jpg","添加");
        img1.setImage(img1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b1 = new JButton("1.增加账户信息", img1);  //将图标设置为按钮背景
        b1.setBorder(null);
        b1.setBackground(Color.WHITE);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        bankAccount.add(b1);

        ImageIcon img2 = new ImageIcon("images/delete.jpg");
        img2.setImage(img2.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b2 = new JButton("2.删除账户信息",img2);
        b2.setBorder(null);
        b2.setBackground(Color.WHITE);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        bankAccount.add(b2);

        ImageIcon img3 = new ImageIcon("images/alter.jpg");
        img3.setImage(img3.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b3 = new JButton("3.修改账户信息",img3);
        b3.setBorder(null);
        b3.setBackground(Color.WHITE);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);
        b3.setHorizontalTextPosition(SwingConstants.CENTER);
        bankAccount.add(b3);

        ImageIcon img4 = new ImageIcon("images/search.jpg");
        img4.setImage(img4.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b4 = new JButton("4.查询账户信息", img4);
        b4.setBorder(null);
        b4.setBackground(Color.WHITE);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);
        b4.setHorizontalTextPosition(SwingConstants.CENTER);
        bankAccount.add(b4);

        ImageIcon img5 = new ImageIcon("images/return.jpg");
        img5.setImage(img5.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b5 = new JButton("5.返回上一级", img5);
        b5.setBorder(null);
        b5.setBackground(Color.WHITE);
        b5.setVerticalTextPosition(SwingConstants.BOTTOM);
        b5.setHorizontalTextPosition(SwingConstants.CENTER);
        bankAccount.add(b5);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("1.增加账户信息")){
                    new AddAccount();
                    bankAccount.setVisible(false);
                }else if (e.getActionCommand().equals("2.删除账户信息")){
                    new DeleteAccount();
                    bankAccount.setVisible(false);
                }else if (e.getActionCommand().equals("3.修改账户信息")){
                    new UpdateAccount();
                    bankAccount.setVisible(false);
                }else if (e.getActionCommand().equals("4.查询账户信息")){
                    new ShowAccount();
                    bankAccount.setVisible(false);
                }else if (e.getActionCommand().equals("5.返回上一级")){
                    BankManage.bankManage.setVisible(true);
                    bankAccount.setVisible(false);
                }
            }
        };

        //将监听器绑定到各个按钮
        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
        b5.addActionListener(actionListener);
    }
}
