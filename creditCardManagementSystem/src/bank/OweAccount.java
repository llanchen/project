package bank;

import entity.AccountVo;
import entity.Data;
import utils.AccountVoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OweAccount {
    public static JFrame oweAccount;
    JTable table;
    DefaultTableModel model;
    public OweAccount(){
        oweAccount = new JFrame();
        oweAccount.setTitle("查询欠款账户");
        oweAccount.setSize(600,300);
        oweAccount.setLocationRelativeTo(null);
        oweAccount.setVisible(true);

        String[] columnNames = {"卡号","密码","姓名","总信用额","可用信用额",
                "可取现额","欠款额","预存金额","开通状态"};
        Object[][] cells = {};
        List<AccountVo> searchResult = new ArrayList<AccountVo>();

        if (Data.accountList != null){
            for (AccountVo accountVo:Data.accountList){//遍历查询欠款的账户对象
                if (accountVo.getCreditOwer() > 0){
                    searchResult.add(accountVo);  //凡欠费的都保存到哦searchResult集合中来
                }
            }
            cells = AccountVoUtil.AccountListToArray(searchResult); //将集合转为二维数组
        }

        model = new DefaultTableModel(cells,columnNames);
        table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        oweAccount.add(jScrollPane, BorderLayout.CENTER);

        JButton backButton =  new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankManage.bankManage.setVisible(true);
                oweAccount.dispose();
            }
        });

        JPanel jpl =  new JPanel();
        jpl.add(backButton);
        oweAccount.add(jpl, BorderLayout.SOUTH);

    }

}
