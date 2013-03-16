package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-13
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo extends JFrame{

         Container c = this.getContentPane();
         private JTextField username = new JTextField();
         private JPasswordField password = new JPasswordField();

     /*   JLabel username  = new JLabel("用户名为:");
        JLabel password = new JLabel("密码为:");
        JTextField jtf = new JTextField("",20);
        JTextField jpf = new JTextField("",20);
        JLabel jbPhone = new JLabel("照片为:");
        JLabel jlk = new JLabel("              ");     */

    public  UserInfo(String name){

        this.setBounds(200,200,300,200);
        c.setLayout(new BorderLayout());
        initInfoFrame();
        this.setVisible(true);
    }
    private void setUserInfo(){
             username.setText(UserPanel.user.getUserName());

    }
    private void initInfoFrame() {

        this.setTitle("用户信息查看信息界面");
       // this.setLayout(new FlowLayout());
        JPanel topPane = new JPanel();
        topPane.setLayout(new FlowLayout());
        topPane.add(new JLabel("用户信息"));
        c.add(topPane,"North");

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        JLabel l1 = new JLabel("用户名:");
        l1.setBounds(50, 20, 50, 20);
        JLabel l2 = new JLabel("密    码:");

        l2.setBounds(50, 60, 50, 20);
    //    JLabel l3 = new JLabel("图片");
     //   l3.setBounds(50,100,50,20);
        fieldPanel.add(l1);
        fieldPanel.add(l2);
    //    fieldPanel.add(l3);
        username.setBounds(110,20,120,20);
        password.setBounds(110,60,120,20);
        fieldPanel.add(username);
        fieldPanel.add(password);

        c.add(fieldPanel,"Center");

        //this.add(username);
       /* this.add(jtf);
        this.add(password);
        this.add(jpf);
        this.add(jbPhone);
        this.add(jlk);
        this.pack();
        */
    }


}
