package bank;

import entity.AccountVo;
import entity.Data;
import utils.AccountVoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

//可以进行模糊查询
public class ShowAccount {


    public static JFrame showAccount;
    private DefaultTableModel model;

    String [] columnNames = {"卡号","密码","姓名","总信用额","可用信用额","可取现额","欠款额","预存金额","开通状态"};
    Object[][] cells = {};
    public ShowAccount(){
        showAccount = new JFrame();
        showAccount.setTitle("信用卡列表");
        showAccount.setSize(600,300);
        showAccount.setLocationRelativeTo(null);
        showAccount.setVisible(true);

        //北部
        JPanel pnlSearch = new JPanel();
        JLabel jlbCardNo = new JLabel("卡号：");
        JTextField jtfCardNo = new JTextField(10);
        JLabel jlbUserName = new JLabel("用户名：");
        JTextField jtfUserName = new JTextField(10);
        JButton btnSearch = new JButton("查找");
        pnlSearch.add(jlbCardNo);
        pnlSearch.add(jtfCardNo);
        pnlSearch.add(jlbUserName);
        pnlSearch.add(jtfUserName);
        pnlSearch.add(btnSearch);
        showAccount.add(pnlSearch, BorderLayout.NORTH);

        //中部
        if (Data.accountList != null){
            //将泛型集合转化为二维数组，以便给表格作为数据
            cells = AccountVoUtil.AccountListToArray(Data.accountList);
        }
        model = new DefaultTableModel(cells,columnNames);
        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        showAccount.add(jScrollPane,BorderLayout.CENTER);

        //南部
        JButton printButton = new JButton("打印");
        JButton backButton = new JButton("返回");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(backButton);
        showAccount.add(buttonPanel,BorderLayout.SOUTH);

        //查询按钮点击事件
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtfCardNo.getText().trim();
                String userName = jtfUserName.getText().trim();

                if (Data.accountList != null){
                    //先清空表格模型，再一一添加符合查询条件的新行
                    model.setRowCount(0);  //清空表格模型
                    for (AccountVo vo:Data.accountList){//筛选符合条件的结果
                        if (!"".equals(cardNo)&vo.getCardNo().contains(cardNo)
                        ||!"".equals(userName)&vo.getUserName().contains(userName)) {
                            model.addRow(new Object[]{vo.getCardNo(),//将符合条件的对象创建为一维数组添加到表格模型
                            vo.getPassword(),vo.getUserName(),vo.getCreditTotal(),
                            vo.getCreditAble(),vo.getCreditCash(),vo.getCreditOwer(),
                            vo.getCreditPresent(),vo.getState() == 1?"开通":"冻结"
                            });
                        }

                    }
                }
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.print();
                } catch (PrinterException printerException) {
                    printerException.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankAccount.bankAccount.setVisible(true);
                showAccount.dispose();
            }
        });




    }

}
