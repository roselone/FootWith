package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.TextFormatException;
import edu.thu.cslab.footwith.server.User;
import edu.thu.cslab.footwith.server.UserManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-13
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoFrame extends JFrame{

        UserManager userManager = new UserManager();



    public UserInfoFrame() throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        this.setBounds(200,200,300,200);

        initInfoFrame();
        this.setVisible(true);
    }

    private void initInfoFrame() throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

                User userInfo = UserManager.selectUser(Global.username);
                BorderLayout borderLayout = new BorderLayout();
                borderLayout.setVgap(5);
                getContentPane().setLayout(borderLayout);
                final  JPanel topPanel = new JPanel();
                GridLayout gridLayout = new GridLayout(4,2);
                gridLayout.setHgap(5);
                gridLayout.setVgap(5);
                topPanel.setLayout(gridLayout);
                this.add(topPanel,"North");

                final  JLabel usernameLabel = new JLabel("用户姓名");
                final  JTextField username = new JTextField();
                username.setText(userInfo.getUserName());
                username.setEnabled(false);

                final JLabel userPwdLabel = new JLabel("密码");
                final  JPasswordField userPwd = new JPasswordField();
                final JLabel userPwdLabel2 = new JLabel("确认密码");
                final  JPasswordField userPwd2 = new JPasswordField();
                topPanel.add(userPwdLabel);
                topPanel.add(userPwd);
                topPanel.add(userPwdLabel2);
                topPanel.add(userPwd2);
                userPwd2.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                       if(e.getKeyChar() != userPwdLabel.getText().charAt(e.getKeyLocation())) {
                           JOptionPane.showConfirmDialog(null,"密码不一致");
                       }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
                final  JLabel userOtherInfoLabel = new JLabel("用户其它信息");
                final JTextField userOtherInfo = new JTextField();
                userOtherInfo.setText(new Integer(userInfo.getOtherInfo()).toString());
                topPanel.add(usernameLabel);
                topPanel.add(username);
                topPanel.add(userOtherInfoLabel);
                topPanel.add(userOtherInfo);

        final  JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
        getContentPane().add(bottomPanel,"South") ;
        final  FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        bottomPanel.setLayout(flowLayout);

        final  JButton updateButton = new JButton();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //   User user_info = new User(username.getText(),userPwd)
                 //

            }
        });
        updateButton.setText("更新");
        bottomPanel.add(updateButton);
        final  JButton closeButton = new JButton();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  doDefaultCloseAction();

            }
        });
        closeButton.setText("关闭");
        bottomPanel.add(closeButton);




    }


}
