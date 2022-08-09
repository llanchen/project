package bank;

import start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BankLogin extends JFrame {
    //事先定义要用到的各个组件
    JPanel jp1, jp2, jp3;
    JLabel jlb1,jlb2;
    JTextField jtf1;
    JPasswordField jpf1;
    JButton jb1, jb2;


    public JFrame bankLogin = new JFrame();

    public BankLogin(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jlb1 = new JLabel("用户名");
        jlb2 = new JLabel("密  码");

        jb1 = new JButton("登  录");
        jb2 = new JButton("取  消");

        jtf1 = new JTextField(10);  //用户名框列数

        jpf1 = new JPasswordField(10);  //密码框列数
        bankLogin.setLayout(new GridLayout(3,1));  //设置网格布局

        //向中间容器加入各个组件
        jp1.add(jlb1);
        jp1.add(jtf1);

        jp2.add(jlb2);
        jp2.add(jpf1);

        jp3.add(jb1);
        jp3.add(jb2);

        //加入到JFrame
        bankLogin.add(jp1);
        bankLogin.add(jp2);
        bankLogin.add(jp3);

        bankLogin.setSize(360,200);
        bankLogin.setLocationRelativeTo(null);
        bankLogin.setVisible(true);
        bankLogin.setTitle("银行管理员登录");

        //为登录按钮点击事件添加监听器
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = jtf1.getText().trim();
                String password = jpf1.getText().trim();

                if ("".equals(username) || username.isEmpty()){
                    JOptionPane.showMessageDialog(bankLogin, "用户名不能为空", "",JOptionPane.WARNING_MESSAGE);
                }else if ("".equals(password) || password.isEmpty()){
                    JOptionPane.showMessageDialog(bankLogin,"密码不能为空", "",JOptionPane.WARNING_MESSAGE);
                }else if ("admin".equals(username) && "admin".equals(password)){
                    new BankManage();
                    bankLogin.dispose();
                }else{
                    JOptionPane.showMessageDialog(bankLogin,"用户名或密码不正确！","",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bankLogin.dispose();
                Start.start.setVisible(true);
            }
        });

        bankLogin.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                bankLogin.dispose();
                Start.start.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
