package bank;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.BAD_CONTEXT;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankManage extends JFrame {
    public static JFrame bankManage;

    public BankManage(){
        //创建窗体
        bankManage = new JFrame();
        bankManage.setLayout(new GridLayout(2,2));
        bankManage.setTitle("银行管理信用卡");
        bankManage.setSize(600,600);
        bankManage.setLocationRelativeTo(null);
        bankManage.setVisible(true);

        //创建第一个按钮
        ImageIcon img1 = new ImageIcon("images/data.jpg");
        img1.setImage(img1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
        JButton b1 = new JButton("1.账户资料管理",img1);
        b1.setBorder(null);   //消除边框
        b1.setBackground(Color.WHITE);   //设置背景颜色
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);
        b1.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img2 = new ImageIcon("images/find.jpg");
        img2.setImage(img2.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
        JButton b2 = new JButton("2.账户欠款查询",img2);
        b2.setBorder(null);
        b2.setBackground(Color.WHITE);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);
        b2.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img3 = new ImageIcon("images/search.jpg");
        img3.setImage(img3.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
        JButton b3 = new JButton("3.账单查询",img3);
        b3.setBorder(null);
        b3.setBackground(Color.WHITE);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);
        b3.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img4 = new ImageIcon("images/return.jpg");
        img4.setImage(img4.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
        JButton b4 = new JButton("4.退出到系统主页",img4);
        b4.setBorder(null);
        b4.setBackground(Color.WHITE);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);
        b4.setHorizontalTextPosition(SwingConstants.CENTER);

        bankManage.add(b1);
        bankManage.add(b2);
        bankManage.add(b3);
        bankManage.add(b4);

        //创建监听器
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("1.账户资料管理")){
                    new BankAccount();
                    bankManage.setVisible(false);
                }else if (e.getActionCommand().equals("2.账户欠款查询")){
                    new OweAccount();
                    bankManage.setVisible(false);
                }else if (e.getActionCommand().equals("3.账单查询")){
                    new ShowAllBills();
                    bankManage.setVisible(false);
                }else if (e.getActionCommand().equals("4.退出到系统主页")){
                    bankManage.dispose();
                    Start.start.setVisible(true);
                }
            }
        };

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
    }
}
