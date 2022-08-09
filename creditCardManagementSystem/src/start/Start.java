package start;

import bank.BankLogin;
import card.CardLogin;
import entity.AccountVo;
import entity.BillVo;
import entity.Data;
import utils.ObjectStreamUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Start extends JFrame {
    JToolBar jtb;
    JButton jb1, jb2, jb3;
    public static JFrame start = new JFrame();

    public Start() {
        //创建工具条
        jtb = new JToolBar();

        ImageIcon img1 = new ImageIcon("images/manager.jpg");
        //所放图标
        img1.setImage(img1.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT));

        jb1 = new JButton("银行管理员登录", img1);  //把图标应用到按钮上
        jb1.setVerticalTextPosition(SwingConstants.BOTTOM); //文本（相对图片）垂直靠下
        jb1.setHorizontalTextPosition(SwingConstants.CENTER);  //文本水平居中
        jb1.setToolTipText("银行管理员登录");  //提示图片

        ImageIcon img2 = new ImageIcon("images/user.jpg");
        img2.setImage(img2.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT));
        jb2 = new JButton("持卡人登录", img2);
        jb2.setVerticalTextPosition(SwingConstants.BOTTOM);
        jb2.setHorizontalTextPosition(SwingConstants.CENTER);
        jb2.setToolTipText("持卡人登录");   //鼠标移动到的位置显示文字

        ImageIcon img3 = new ImageIcon("images/exit.jpg");
        img3.setImage(img3.getImage().getScaledInstance(220, 220, Image.SCALE_DEFAULT));
        jb3 = new JButton("退出", img3);
        jb3.setVerticalTextPosition(SwingConstants.BOTTOM);
        jb3.setHorizontalTextPosition(SwingConstants.CENTER);
        jb3.setToolTipText("退出");

        //将上述三个按钮添加到工具条
        jtb.add(jb1);
        jtb.add(jb2);
        jtb.add(jb3);
        start.add(jtb);  //将工具条添加到当前窗体
        start.setTitle("银行信用卡管理系统");
        start.setSize(760,300);
        start.setLocationRelativeTo(null);  //居中
        start.setVisible(true);
        start.setDefaultCloseOperation(start.EXIT_ON_CLOSE);

        //给第一个按钮添加点击事件
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BankLogin();
                start.setVisible(false);
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CardLogin();
                start.setVisible(false);
            }
        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        initData();   //初始化一些测试用的数据存入Data.accountList
        new Start();
    }

    //初始化一些测试用的数据存入Data.accountList
    private static void initData() {

        //调用方法，利用对象流，读取data/account.txt文件，取出里面的list<AccountVo>
        Data.accountList = (ArrayList<AccountVo>) ObjectStreamUtil.getAccountList();


        if (Data.accountList.size() == 0) { //判断是否有数据，没有就往库初始化几条测试用
            AccountVo av1 = new AccountVo("10010", "123", "aaa", new Date(), 8000, 0, 1000, 1);
            AccountVo av2 = new AccountVo("10011", "111", "bbb", new Date(), 5000, 0, 0, 0);
            AccountVo av3 = new AccountVo("10012", "222", "ccc", new Date(), 1000, 0, 100, 1);
            AccountVo av4 = new AccountVo("10013", "333", "ddd", new Date(), 5000, 1000, 0, 0);

            Data.accountList.add(av1);
            Data.accountList.add(av2);
            Data.accountList.add(av3);
            Data.accountList.add(av4);

            ///调用方法，利用对象流，读将对象Data.accountList用对象流存入文本文件account.txt
            ObjectStreamUtil.saveAccountList(Data.accountList);

        }

        //调用方法，利用对象流，将账单数据从文本文件中读取出来
        Data.billList=(ArrayList<BillVo>)  ObjectStreamUtil.getBillList();}

    }

