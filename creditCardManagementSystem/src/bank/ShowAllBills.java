package bank;

import entity.Data;
import utils.BillVoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class ShowAllBills {
    public static JFrame showAllBills;
    public ShowAllBills(){
        showAllBills = new JFrame();
        showAllBills.setTitle("账单列表");
        showAllBills.setSize(600,300);
        showAllBills.setLocationRelativeTo(null);
        showAllBills.setVisible(true);

        String[] columnNames = {"账单号", "用户名", "卡号", "金额", "类型"};
        Object[][] cells = {};
        if (Data.billList.size() > 0){
            cells = BillVoUtil.formBillListToArray(Data.billList);
        }

        TableModel model = new DefaultTableModel(cells,columnNames);
        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        showAllBills.add(jScrollPane, BorderLayout.CENTER);

        JButton printButton = new JButton("打印");
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

        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankManage.bankManage.setVisible(true);
                showAllBills.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(backButton);
        showAllBills.add(buttonPanel, BorderLayout.SOUTH);
    }
}
