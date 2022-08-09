package bank;


import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import entity.AccountVo;
import entity.Data;
import utils.AccountVoUtil;
import utils.ObjectStreamUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UpdateAccount{


    public static JFrame updateAccount;
    JScrollPane jScrollPane;
    JTable table;
    String[] columnNames = {"卡号","密码","姓名","总信用额","可用信用额","可取现额","欠款额","预存金额","开通状态"};
    DefaultTableModel model;
    Object[][] cells = {};
    JTextField jtfCardNo2;
    JTextField jtfUserName;
    JTextField jtfPassword;
    JTextField jtfCreditTotal;
    JTextField jtfCreditPresent;
    JRadioButton jrbOpen;
    JRadioButton jrbIce;

    public UpdateAccount() {
        updateAccount = new JFrame();
        updateAccount.setTitle("修改信用卡");
        updateAccount.setSize(600,400);
        updateAccount.setLocationRelativeTo(null);
        updateAccount.setVisible(true);

        JPanel pnlSearch = new JPanel();
        JLabel jlbCardNo = new JLabel("从列表选择或输入卡号：");
        JTextField jtfCardNo = new JTextField(10);
        JPanel buttonPanel = new JPanel();
        JButton btnSearch = new JButton("查询");
        pnlSearch.add(jlbCardNo);
        pnlSearch.add(jtfCardNo);
        pnlSearch.add(btnSearch);
        updateAccount.add(pnlSearch, BorderLayout.NORTH);

        if (Data.accountList != null) {
            //将泛型集合转化为二维数组，以便表格作为数据
            cells = AccountVoUtil.AccountListToArray(Data.accountList);
        }
        model = new DefaultTableModel(cells, columnNames);
        table = new JTable(model);
        MouseListener mouseListener = new MouseListener() {
            //鼠标点击事件，点击后将该数据显示到下面的有关文本框中
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();  //获取点击行号
                //根据行索引与列索引获取有关单元格数据
                String cardNo = (String) table.getValueAt(row, 0);
                jtfCardNo.setText(cardNo);

                jtfCardNo2.setText(cardNo);
                jtfPassword.setText((String) table.getValueAt(row, 1));
                jtfUserName.setText((String) table.getValueAt(row, 2));

                jtfCreditTotal.setText(String.valueOf(table.getValueAt(row, 3)));
                jtfCreditPresent.setText(String.valueOf(table.getValueAt(row, 7)));
                if (table.getValueAt(row, 8).equals("开通")) {
                    jrbOpen.setSelected(true);
                } else {
                    jrbIce.setSelected(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        table.addMouseListener(mouseListener);
        jScrollPane = new JScrollPane(table);
        updateAccount.add(jScrollPane, BorderLayout.CENTER);

        JPanel jplGroup = new JPanel(new GridLayout(4, 2));
        JPanel jpl1 = new JPanel();
        JLabel jl1 = new JLabel("卡  号：");
        jtfCardNo2 = new JTextField(10);
        jtfCardNo2.setEditable(false);  //卡号不能修改
        jpl1.add(jl1);
        jpl1.add(jtfCardNo2);
        jplGroup.add(jpl1);

        JPanel jpl2 = new JPanel();
        JLabel jl2 = new JLabel("姓  名：");
        jtfUserName = new JTextField(10);
        jpl2.add(jl2);
        jpl2.add(jtfUserName);
        jplGroup.add(jpl2);

        JPanel jpl3 = new JPanel();
        JLabel jl3 = new JLabel("密  码：");
        jtfPassword = new JTextField(10);
        jpl3.add(jl3);
        jpl3.add(jtfPassword);
        jplGroup.add(jpl3);

        JPanel jpl4 = new JPanel();
        JLabel jl4 = new JLabel("信用额度：");
        jtfCreditTotal = new JTextField(10);
        jpl4.add(jl4);
        jpl4.add(jtfCreditTotal);
        jplGroup.add(jpl4);

        JPanel jpl5 = new JPanel();
        JLabel jl5 = new JLabel("预存金额");
        jtfCreditPresent = new JTextField(10);
        jpl5.add(jl5);
        jpl5.add(jtfCreditPresent);
        jplGroup.add(jpl5);

        JLabel jl6 = new JLabel("状态：");
        ButtonGroup btnGroup = new ButtonGroup();   //创建按钮组
        jrbOpen = new JRadioButton("开通");
        jrbIce = new JRadioButton("冻结");
        btnGroup.add(jrbOpen);
        btnGroup.add(jrbIce);
        JPanel jpl6 = new JPanel();
        jpl6.add(jl6);
        jpl6.add(jrbOpen);
        jpl6.add(jrbIce);
        jplGroup.add(jpl6);

        JButton updateButton = new JButton("确定修改");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtfCardNo.getText().trim();

                if ("".equals(cardNo)) {
                    JOptionPane.showMessageDialog(updateAccount, "请选择或输入卡号");
                    return;
                }
                AccountVo accountVo = AccountVoUtil.findAccountVoByCardNo(cardNo); //查找信用卡对象

                if (accountVo == null) {
                    JOptionPane.showMessageDialog(updateAccount, "该卡号不存在");
                    return;
                }
                int okCancel = JOptionPane.showConfirmDialog(updateAccount, "你确定要修改吗？", "确定修改",
                        JOptionPane.OK_CANCEL_OPTION);

                if (okCancel == 0) {  //点击了对话框的确定按钮
                    String password = jtfPassword.getText().trim();  //获取密码
                    String userName = jtfUserName.getText().trim();  //获取用户名
                    double creditTotal = Double.parseDouble(jtfCreditTotal.getText().trim());//获取信用额
                    double creditPresent = Double.parseDouble(jtfCreditPresent.getText().trim());//获取预存金额
                    boolean isOpen = jrbOpen.isSelected();
                    int state = 0;

                    if (isOpen) {
                        state = 1;
                    }
                    accountVo.setPassword(password);
                    accountVo.setUserName(userName);

                    double range = creditTotal - accountVo.getCreditTotal(); //总信用额度增减量
                    accountVo.setCreditAble(accountVo.getCreditAble() + range); //可用信用额变化
                    accountVo.setCreditTotal(creditTotal); //修改总信用额度
                    accountVo.setCreditPresent(creditPresent);  //修改预存金额
                    accountVo.setCreditCash(accountVo.getCreditAble() / 1.1 + creditPresent); //修改可取现金额
                    accountVo.setState(state);   //修改状态
                    //注：由于accountVo是引用类型，故Data.accountList中的对应的成员对象也会随之改变，无须再另外修改

                    int index = Data.accountList.indexOf(accountVo);  //获取行索引
                    model.setValueAt(password, index, 1); //修改当前行的各个单元格
                    model.setValueAt(userName, index, 2);
                    model.setValueAt(creditTotal, index, 3);
                    model.setValueAt(accountVo.getCreditAble(), index, 4);
                    model.setValueAt(accountVo.getCreditCash(), index, 5);
                    model.setValueAt(accountVo.getCreditOwer(), index, 6);
                    model.setValueAt(creditPresent, index, 7);

                    String open = "开通";
                    if (state == 0) {
                        open = "冻结";
                    }
                    model.setValueAt(open, index, 8);

                    //调用方法用对象流将包含最新数据的Data.accountList保存到文本文件
                      ObjectStreamUtil.saveAccountList(Data.accountList);

                    JOptionPane.showMessageDialog(updateAccount, "修改成功！");
                } else {
                    return;
                }
            }
        });

        JPanel jpl7 = new JPanel();
        jpl7.add(updateButton);
        jplGroup.add(jpl7);

        JButton backButton = new JButton("返回上级");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankAccount.bankAccount.setVisible(true);
                updateAccount.dispose();
            }
        });

        JPanel jpl8 = new JPanel();
        jpl8.add(backButton);
        jplGroup.add(jpl8);
        updateAccount.add(jplGroup, BorderLayout.SOUTH);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtfCardNo.getText().trim();
                if ("".equals(cardNo)) {
                    JOptionPane.showMessageDialog(updateAccount, "请选择或输入卡号");
                    return;
                }

                int rowcount = table.getColumnCount();  //获取列数
                for (int i = 0; i < rowcount; i++) {
                    String cardno = (String) table.getValueAt(i, 0);
                    if (cardno.equals(cardNo)) {
                        table.setRowSelectionInterval(i, i);  //将找到的行高亮显示
                    }
                }

                AccountVo accountVo = AccountVoUtil.findAccountVoByCardNo(cardNo);
                if (accountVo != null) {  //将找到的数据显示到各个文本框中显示
                    jtfCardNo2.setText(accountVo.getCardNo());
                    jtfUserName.setText(accountVo.getUserName());
                    jtfPassword.setText(accountVo.getPassword());
                    jtfCreditTotal.setText(String.valueOf(accountVo.getCreditTotal()));
                    jtfCreditPresent.setText(String.valueOf(accountVo.getCreditPresent()));

                    if (accountVo.getState() == 1) {
                        jrbOpen.setSelected(true);
                    } else {
                        jrbIce.setSelected(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(updateAccount, "该卡号不存在");
                }
            }
        });

    }


}
