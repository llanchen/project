package bank;

import entity.AccountVo;
import entity.Data;
import utils.AccountVoUtil;
import utils.ObjectStreamUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddAccount extends JFrame {

    public static JFrame addAccount;
    DefaultTableModel model;
    //列标题
    String [] columnNames = {"卡号","密码","姓名","总信用额","可用信用额","可取现额","欠款额","预存金额","开通状态"};
    //要展示的数据
    Object [][] cells = {};

    public AddAccount(){
        addAccount = new JFrame();
        addAccount.setTitle("添加账户");
        addAccount.setSize(600,300);
        addAccount.setLocationRelativeTo(null);
        addAccount.setVisible(true);

        if (Data.accountList != null){
            //将泛型集合转化为二维数组，以便给表格作为数据
            cells = AccountVoUtil.AccountListToArray(Data.accountList);
        }

        model = new DefaultTableModel(cells,columnNames);
        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        addAccount.add(jScrollPane, BorderLayout.CENTER);

        JPanel jplGroup = new JPanel(new GridLayout(2,3));//将中间容器的布局改为网络布局
        JLabel jl1 = new JLabel("卡   号：");
        JTextField jtfCardNo = new JTextField(10);
        JPanel jpl1 = new JPanel();
        jpl1.add(jl1);
        jpl1.add(jtfCardNo);
        jplGroup.add(jpl1);  //第一行第一列

        JLabel jl2 = new JLabel("姓   名：");
        JTextField jtfUserName = new JTextField(10);
        JPanel jpl2 = new JPanel();
        jpl2.add(jl2);
        jpl2.add(jtfUserName);
        jplGroup.add(jpl2);  //第一行第二列

        JLabel jl3 = new JLabel("密   码：");
        JTextField jtfPassword = new JTextField(10);
        JPanel jpl3 = new JPanel();
        jpl3.add(jl3);
        jpl3.add(jtfPassword);
        jplGroup.add(jpl3);

        JLabel jl4 = new JLabel("信用额度：");
        JTextField jtfCreditTotal = new JTextField(10);
        JPanel jpl4 = new JPanel();
        jpl4.add(jl4);
        jpl4.add(jtfCreditTotal);
        jplGroup.add(jpl4);

        JLabel jl5 = new JLabel("预存金额");
        JTextField jtfCreditPresent = new JTextField(10);
        JPanel jp5 = new JPanel();
        jp5.add(jl5);
        jp5.add(jtfCreditPresent);
        jplGroup.add(jp5);

        JButton addBtn = new JButton("添加新卡");
        JButton cancelBtn = new JButton("返回上级");
        JPanel jpl6 = new JPanel();
        jpl6.add(addBtn);
        jpl6.add(cancelBtn);
        jplGroup.add(jpl6);  //第二行第三列
        addAccount.add(jplGroup,BorderLayout.SOUTH);

        //添加新卡功能
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtfCardNo.getText().trim();  //获取卡号
                if ("".equals(cardNo)){  //非空验证
                    JOptionPane.showMessageDialog(addAccount,"卡号不能为空！");
                    return;
                }

                //重复验证
                if (AccountVoUtil.checkCardNo(cardNo)){
                    JOptionPane.showMessageDialog(addAccount,"卡号不能重复！");
                    return;
                }

                String password = jtfPassword.getText().trim();  //获取密码
                String userName = jtfUserName.getText().trim();  //获取用户名
                double creditTotal = Double.parseDouble(jtfCreditTotal.getText().trim()); //获取信用额
                double creditPresent = Double.parseDouble(jtfCreditPresent.getText().trim());//获取预存金额
                AccountVo accountVo = new AccountVo(cardNo,password,userName,new Date(),creditTotal,0,
                        creditPresent,1);  //创建一个AccountVo对象

                Data.accountList.add(accountVo);  //添加到集合中
                //调用方法用对象流将包含最新数据的Data.accountList保存到文本文件
                ObjectStreamUtil.saveAccountList(Data.accountList);
                //获取文本框的数据，及信用卡的其他信息，并存放于一维数组，作为新一行的内容

                String newRow[] = {cardNo,password,userName,String.valueOf(creditTotal),
                String.valueOf(creditPresent), String.valueOf(accountVo.getCreditCash()),
                String.valueOf(accountVo.getCreditOwer()),
                String.valueOf(accountVo.getCreditPresent()),"开通"};


                //将新的一行加入表格
                model.addRow(newRow);
                JOptionPane.showMessageDialog(addAccount,"添加成功！");
            }
        });

        //返回功能
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount.dispose(); //关闭当前窗口
                BankAccount.bankAccount.setVisible(true);  //跳转到上一个界面
            }
        });

    }
}


