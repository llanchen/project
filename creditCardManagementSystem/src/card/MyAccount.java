package card;

import entity.AccountVo;
import utils.AccountVoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyAccount {
    public static JFrame myAccount;

    public MyAccount(AccountVo accountVo){
        myAccount = new JFrame();
        myAccount.setTitle("我的账户");
        myAccount.setSize(600,300);
        myAccount.setLocationRelativeTo(null);
        myAccount.setVisible(true);
        String[] columnNames = {"卡号","密码","姓名","总信用额","可用信用额","可取现额","欠款额","预存金额",
                "开通状态"};

        List<AccountVo> searchResult = new ArrayList<AccountVo>();
        searchResult.add(accountVo);  //将当前账户保存到searchResult集合中来
        Object[][] cells = AccountVoUtil.AccountListToArray(searchResult);  //将集合转换为二维数组
        TableModel model = new DefaultTableModel(cells, columnNames);
        JTable table = new JTable(model);
        JScrollPane jScrollPane = new JScrollPane(table);
        myAccount.add(jScrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardManage.cardManage.setVisible(true);
                myAccount.dispose();
            }
        });

        JPanel jpl = new JPanel();
        jpl.add(backButton);
        myAccount.add(jpl,BorderLayout.SOUTH);
    }
}
