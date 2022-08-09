package card;

import entity.AccountVo;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardManage extends JFrame{
    public static JFrame cardManage;

    //带参构造方法，用于区别不同持卡人
    public CardManage(AccountVo accountVo){
        cardManage = new JFrame();
        cardManage.setLayout(new GridLayout(3,2));
        cardManage.setTitle("银行管理信用卡");
        cardManage.setSize(600,600);
        cardManage.setLocationRelativeTo(null);
        cardManage.setVisible(true);

        ImageIcon img1 = new ImageIcon("images/myAccount.jpg");
        img1.setImage(img1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b1 = new JButton("1.我的账户",img1);
        b1.setBackground(Color.WHITE);
        b1.setBorder(null);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);
        b1.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img2 = new ImageIcon("images/consumptionManagement.jpg");
        img2.setImage(img2.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b2 = new JButton("2.消费管理",img2);
        b2.setBackground(Color.WHITE);
        b2.setBorder(null);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);
        b2.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img3 = new ImageIcon("images/repayment.jpg");
        img3.setImage(img3.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b3 = new JButton("3.还款管理",img3);
        b3.setBackground(Color.WHITE);
        b3.setBorder(null);
        b3.setVerticalTextPosition(SwingConstants.BOTTOM);
        b3.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img4 = new ImageIcon("images/enchashment.jpg");
        img4.setImage(img4.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b4 = new JButton("4.取现管理",img4);
        b4.setBackground(Color.WHITE);
        b4.setBorder(null);
        b4.setVerticalTextPosition(SwingConstants.BOTTOM);
        b4.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img5 = new ImageIcon("images/search.jpg");
        img5.setImage(img5.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b5 = new JButton("5.账单查询",img5);
        b5.setBackground(Color.WHITE);
        b5.setBorder(null);
        b5.setVerticalTextPosition(SwingConstants.BOTTOM);
        b5.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon img6 = new ImageIcon("images/return.jpg");
        img6.setImage(img6.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        JButton b6 = new JButton("6.返回主界面",img6);
        b6.setBackground(Color.WHITE);
        b6.setBorder(null);
        b6.setVerticalTextPosition(SwingConstants.BOTTOM);
        b6.setHorizontalTextPosition(SwingConstants.CENTER);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("1.我的账户")){
                    new MyAccount(accountVo);
                    cardManage.setVisible(false);
                }else if (e.getActionCommand().equals("2.消费管理")){
                    if (accountVo.getState() == 0){
                        String msg = "该卡已冻结，不能消费，请联系银行工作人员";
                        JOptionPane.showMessageDialog(cardManage,msg);
                    }else {
                        new PayCard(accountVo);
                        cardManage.setVisible(false);
                    }
                }else if (e.getActionCommand().equals("3.还款管理")){
                    new ReturnCard(accountVo);
                    cardManage.setVisible(false);
                }else if (e.getActionCommand().equals("4.取现管理")){
                    if (accountVo.getState() == 0){
                        String msg = "该卡已冻结，不能消费，请联系银行工作人员";
                        JOptionPane.showMessageDialog(cardManage,msg);
                    }else {
                        new CashCard(accountVo);
                        cardManage.setVisible(false);
                    }
                }else if (e.getActionCommand().equals("5.账单查询")){
                    new ShowMyBill(accountVo);
                    cardManage.setVisible(false);
                }else if (e.getActionCommand().equals("6.返回主界面")){
                    Start.start.setVisible(true);
                    cardManage.setVisible(false);
                }
            }
        };

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);
        b3.addActionListener(actionListener);
        b4.addActionListener(actionListener);
        b5.addActionListener(actionListener);
        b6.addActionListener(actionListener);
        cardManage.add(b1);
        cardManage.add(b2);
        cardManage.add(b3);
        cardManage.add(b4);
        cardManage.add(b5);
        cardManage.add(b6);
    }
}
