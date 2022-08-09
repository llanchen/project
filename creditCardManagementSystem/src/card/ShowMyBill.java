package card;

import entity.AccountVo;
import entity.BillVo;
import entity.Data;
import utils.BillVoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class ShowMyBill {
    public static JFrame showMyBill;

    public ShowMyBill(AccountVo accountVo){
        showMyBill = new JFrame();
        showMyBill.setTitle("账单列表");
        showMyBill.setSize(600,300);
        showMyBill.setLocationRelativeTo(null);
        showMyBill.setVisible(true);

        String[] columnNames = {"账单号","用户名","卡号","金额","类型"};
        Object[][] cells = {};

        List<BillVo> myBillList = new ArrayList<BillVo>();
        if (Data.billList.size() > 0){
            //查找属于本卡号的账单
            for (BillVo billVo : Data.billList){
                if (billVo.getCardNo().equals(accountVo.getCardNo())){
                    myBillList.add(billVo);
                }
            }
            if (myBillList.size() > 0){
                cells = BillVoUtil.formBillListToArray(myBillList);
            }
        }

        TableModel model = new DefaultTableModel(cells,columnNames);
        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        showMyBill.add(jScrollPane, BorderLayout.CENTER);

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
                CardManage.cardManage.setVisible(true);
                showMyBill.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(backButton);
        showMyBill.add(buttonPanel,BorderLayout.SOUTH);
    }
}
