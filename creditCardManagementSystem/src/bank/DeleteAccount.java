package bank;

import entity.AccountVo;
import entity.Data;
import jdk.nashorn.internal.scripts.JO;
import utils.AccountVoUtil;
import utils.ObjectStreamUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class DeleteAccount {


    public static JFrame deleteAccount;
    JScrollPane jScrollPane;
    JTable table;
    DefaultTableModel model;

    Object[][] cells = {};
    String [] columnNames = {"卡号","密码","姓名","总信用额","可用信用额","可取现额","欠款额","预存金额","开通状态"};

    public DeleteAccount(){
        deleteAccount = new JFrame();
        deleteAccount.setTitle("删除信用卡");
        deleteAccount.setSize(600,300);
        deleteAccount.setLocationRelativeTo(null);
        deleteAccount.setVisible(true);

        //北部
        JPanel pnlSearch = new JPanel();
        JLabel jlbCardNo = new JLabel("从列表选择或输入卡号");
        JTextField jtfCardNo = new JTextField(10);
        JPanel buttonPanel = new JPanel();
        JButton btnSearch = new JButton("查询");
        pnlSearch.add(jlbCardNo);
        pnlSearch.add(jtfCardNo);
        pnlSearch.add(btnSearch);
        deleteAccount.add(pnlSearch, BorderLayout.NORTH);

    //中部
        if (Data.accountList != null){//将泛型集合转化为二维数组，以便给表格作为数据
            cells = AccountVoUtil.AccountListToArray(Data.accountList);
        }
        model = new DefaultTableModel(cells, columnNames);
        table = new JTable(model);
        jScrollPane = new JScrollPane(table);
        deleteAccount.add(jScrollPane,BorderLayout.CENTER);

    //南部
        JButton deleteButton = new JButton("删除");
        buttonPanel.add(deleteButton);
        JButton backButton = new JButton("返回");
        buttonPanel.add(backButton);
        deleteAccount.add(buttonPanel,BorderLayout.SOUTH);

     //搜索按钮的监听器
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNo = jtfCardNo.getText().trim();
                if ("".equals(cardNo)){
                    JOptionPane.showMessageDialog(deleteAccount,"请选择或输入卡号");
                    return;
                }

                AccountVo vo = AccountVoUtil.findAccountVoByCardNo(cardNo);
                if (vo != null){
                    int rowcount = table.getRowCount();

                    for (int i = 0; i < rowcount; i++){
                        String cardno = (String) table.getValueAt(i,0);
                        if (cardno.equals(cardNo)){
                            table.setRowSelectionInterval(i,i);
                            break;
                        }
                    }
                }
                /*if (voa != null){
                    //先清空表格模型，再一一添加符合查询条件的新行
                    model.setRowCount(0);  //清空表格模型
                    for (AccountVo vo:Data.accountList){//筛选符合条件的结果
                        if (!"".equals(cardNo)&vo.getCardNo().contains(cardNo)) {
                            model.addRow(new Object[]{vo.getCardNo(),//将符合条件的对象创建为一维数组添加到表格模型
                                    vo.getPassword(),vo.getUserName(),vo.getCreditTotal(),
                                    vo.getCreditAble(),vo.getCreditCash(),vo.getCreditOwer(),
                                    vo.getCreditPresent(),vo.getState() == 1?"开通":"冻结"
                            });
                        }

                    }
                }*/


                else {
                    JOptionPane.showMessageDialog(deleteAccount,"该卡号不存在");
                }
            }
        });

        //删除按钮的监听器
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();  //获取点击的行索引
                String cardNo = jtfCardNo.getText().trim();
                if ("".equals(cardNo)){
                    JOptionPane.showMessageDialog(deleteAccount,"请选择或输入卡号");
                    return;
                }
                //查找卡号对应的信用卡对象
                AccountVo accountVo = AccountVoUtil.findAccountVoByCardNo(cardNo);
                if (accountVo == null){
                    JOptionPane.showMessageDialog(deleteAccount,"该卡号不存在");
                    return;
                }
                row = Data.accountList.indexOf(accountVo); //查找accountVo在集合中的索引
                if (row == -1){
                    JOptionPane.showMessageDialog(deleteAccount,"该卡号不存在");
                    return;
                }

                int okCancel = JOptionPane.showConfirmDialog(deleteAccount,"你确定要删除吗？","确定删除",
                        JOptionPane.OK_CANCEL_OPTION);

                if (okCancel == 0){  //表示点击了对话框的确定按钮
                    //每个信用卡都可能有若干下销账单记录，要先删除这些记录，再删除信用卡账户
                    for (int i = 0; i < Data.billList.size(); i++) {  //遍历所有账单
                        if (Data.billList.get(i).getCardNo().equals(cardNo)) { //查找该账号相关账单
                            Data.billList.remove(i);
                        }
                    }
                        ObjectStreamUtil.saveBillList(Data.billList);  //将最新的账单数据持久化到文本文件

                        model.removeRow(row);
                        Data.accountList.remove(accountVo);  //从集合中删除信用卡
                        //调用方法用对象流将包含最新数据的Data.accountList保存到文本文件
                        ObjectStreamUtil.saveAccountList(Data.accountList);
                        JOptionPane.showMessageDialog(deleteAccount,"删除成功！");
                    }
                }
            });

            //返回按钮的监听器
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BankAccount.bankAccount.setVisible(true);
                    deleteAccount.dispose();
                }
            });

            //表格的点击事件
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String cardNo = (String) table.getValueAt(row,0);
                    jtfCardNo.setText(cardNo);
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
            });
    }
}
