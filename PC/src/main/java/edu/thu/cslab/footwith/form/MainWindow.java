package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-12
 * Time: 下午11:39
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow {
       private JFrame mainFrame = new JFrame("旅游行程管理系统");
       private Container c = mainFrame.getContentPane();

       private  MemuPanel menuPanel = new MemuPanel();
       private  UserPanel userPanel = new UserPanel();

       private  JPanel centerPanel = new JPanel();
       private  JPanel rightPanel = new JPanel();

       private SitePanel sitePanel = new SitePanel();
       private  PlanPanel  PlanPanel = new PlanPanel(); // test whether it get the data

//       private JMenu   mainMemu = new JMenu();
//       private JPanel rightPanel = new JPanel();
//       private  JPanel leftJsp = new JPanel();
//       private  JPanel leftJsp = new JPanel();
//       private  ButtonListener ourListener = new ButtonListener();

    public MainWindow(){
        mainFrame.setBounds(100,5,800,700);
        initMainFrame();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {
        //To change body of created methods use File | Settings | File Templates.
        c.setLayout(new BorderLayout());
      //  mainFrame.setContentPane(menuPanel);
        mainFrame.add(menuPanel,"North");
        JButton b1 = new JButton("确定");
        JButton b2 = new JButton("取消");


        JSplitPane panel_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);     // horizontal
        panel_1.setDividerLocation(250);
        panel_1.setDividerSize(5);

        JSplitPane leftJsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);          //vertical
        leftJsp.setDividerLocation(200);
        leftJsp.setDividerSize(5);
        //leftJsp.setOneTouchExpandable(true);

        JSplitPane centerJsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);           //vertical     centerJsp
        centerJsp.setDividerLocation(400);
        centerJsp.setDividerSize(5);
        centerJsp.setLeftComponent(sitePanel);
        centerJsp.setRightComponent(b1);

        JSplitPane rightJsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);            //horizontal
        centerJsp.setDividerLocation(400);
        centerJsp.setDividerSize(5);
        

        panel_1.setLeftComponent(leftJsp);
        panel_1.setRightComponent(rightJsp);

        rightJsp.setLeftComponent(centerJsp);
        rightJsp.setRightComponent(b2);
        rightJsp.setDividerLocation(400);
        rightJsp.setDividerSize(5);

        leftJsp.setLeftComponent(userPanel);
        leftJsp.setRightComponent(PlanPanel); // test

        mainFrame.add(panel_1,"Center");

        userPanel.setUserInfo("username","time");
       // userPanel.add(userPanel,"North");


    }
    public static void main(String[] args){
        new MainWindow();
    }

}
