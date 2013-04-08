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

       private MenuPanel menuPanel = new MenuPanel();
//       private toolBarPanel toolBar = new toolBarPanel();
       private  UserPanel userPanel = new UserPanel();

       private  JPanel centerPanel = new JPanel();
       private  JPanel rightPanel = new JPanel();

       private SitePanel sitePanel = new SitePanel();
       private  PlanPanel  PlanPanel = new PlanPanel(); // test whether it get the data
       private  RecordPanel recordPanle = new RecordPanel();

//       private JMenu   mainMemu = new JMenu();
//       private JPanel rightPanel = new JPanel();
//       private  JPanel leftJsp = new JPanel();
//       private  JPanel leftJsp = new JPanel();
//       private  ButtonListener ourListener = new ButtonListener();

    public MainWindow(){
        mainFrame.setBounds(100,50,800,700);
        initMainFrame();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {
        //To change body of created methods use File | Settings | File Templates.

      //  mainFrame.setContentPane(menuPanel);
     //   mainFrame.add(menuPanel,"North");

        BorderLayout borderLayout =new BorderLayout();
        borderLayout.setVgap(5);

        mainFrame.getContentPane().setLayout(borderLayout);

        mainFrame.add(menuPanel,"North");

       // topPanel.add(toolBar,"South");

        JSplitPane panel_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);     // horizontal
        panel_1.setDividerLocation(300);
        panel_1.setDividerSize(5);

        JSplitPane leftJsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);          //vertical
        leftJsp.setDividerLocation(100);
        leftJsp.setDividerSize(5);
        //leftJsp.setOneTouchExpandable(true);

        JSplitPane centerJsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);           //vertical     centerJsp
        centerJsp.setDividerLocation(400);
        centerJsp.setDividerSize(5);
        centerJsp.setLeftComponent(sitePanel);
        centerJsp.setRightComponent(recordPanle);

      //  JSplitPane rightJsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);            //horizontal
      //  centerJsp.setDividerLocation(400);
       // centerJsp.setDividerSize(5);
        

        panel_1.setLeftComponent(leftJsp);
        panel_1.setRightComponent(centerJsp);

     //   rightJsp.setLeftComponent(centerJsp);
     //   rightJsp.setRightComponent(new JPanel());
      //  rightJsp.setDividerLocation(400);
      //  rightJsp.setDividerSize(5);

        leftJsp.setLeftComponent(userPanel);
        leftJsp.setRightComponent(PlanPanel); // test

        mainFrame.add(panel_1,"Center");

        userPanel.setUserInfo("username","time");
       // userPanel.add(userPanel,"North");


    }
    public static void main(String[] args){
       try {
           new MainWindow();
    } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
