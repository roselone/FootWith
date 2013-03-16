package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.TextFormatException;
import edu.thu.cslab.footwith.server.User;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-15
 * Time: 下午10:29
 * To change this template use File | Settings | File Templates.
 */
public class UserPanel extends JPanel {

    public static   User user = new User();
    private JLabel userInfo = new JLabel();
    private JLabel userLoginTime = new JLabel();

    public UserPanel() {

        initUserPanel();
        this.setSize(100,100);
      //  this.setVisible(true);
    }
//    void setUserInfo(String userName,String pwd) throws TextFormatException {
//        user.setPasswd(userName);
//        user.setUserName(pwd);
//    }
    private void initUserPanel() {

        this.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("用户信息");
        this.add(titleLabel,"North");

        JPanel userInfoPanel = new JPanel();
        this.add(userInfoPanel,"Center");

        userInfoPanel.setLayout(new GridLayout(2,2));

        JLabel userLabel = new JLabel("用户名:");

        JLabel loginTime = new JLabel("登录时间");

        userInfoPanel.add(userLabel);
        userInfoPanel.add(userInfo);
        userInfoPanel.add(loginTime);
        userInfoPanel.add(userLoginTime);


    }
        public void  setUserInfo(String userInfo_1,String loginTime_1) {
        userInfo.setText(user.getUserName());
        userLoginTime.setText(new Date().toString());

    }

}
