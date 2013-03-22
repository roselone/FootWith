package edu.thu.cslab.footwith.form;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.TextFormatException;


/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-12
 * Time: 下午11:09
 * To change this template use File | Settings | File Templates.
 */
public class loginForm implements  ActionListener{

    private JFrame frame = new JFrame("登录");
    private Container c = frame.getContentPane();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton ok = new JButton("确定");
    private JButton cancel = new JButton("取消");
    public loginForm(){
     //   frame.setSize(300,200);
        frame.setBounds(300,200,300,200);
        c.setLayout(new BorderLayout());
        initFrame();
        frame.setVisible(true);
    }

    private void initFrame() {

        //顶部
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("系统管理员登录"));
        c.add(titlePanel,"North");

        //中部表单
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        JLabel l1 = new JLabel("用户名:");
        l1.setBounds(50, 20, 50, 20);
        JLabel l2 = new JLabel("密    码:");
        l2.setBounds(50, 60, 50, 20);
        fieldPanel.add(l1);
        fieldPanel.add(l2);
        username.setBounds(110,20,120,20);
        password.setBounds(110,60,120,20);
        fieldPanel.add(username);
        fieldPanel.add(password);
        c.add(fieldPanel,"Center");

        //底部按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        c.add(buttonPanel,"South");
        ok.addActionListener( this);
       cancel.addActionListener(this);
    }

    public static void main(String[] args){
        new loginForm();
    }

    public  void actionPerformed(ActionEvent e){
            if(e.getSource() == ok){
                String uname = username.getText();
                String pwd =new String(password.getPassword());
                // Validation matchExample = new Validation();
                // mathExample.valida(uname, pwd);
                try {
                    if(Mediator.isValid(uname, pwd)){

                         //UserPanel.user.setPasswd(pwd);
                         //UserPanel.user.setUserName(uname);
                         new MainWindow();

                    } else{

                    }
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } if(e.getSource() == cancel){

        }  else{

        }
    }
}