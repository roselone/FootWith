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
       private  ScenePanel scenePanel = new ScenePanel();

//       private JMenu   mainMemu = new JMenu();
//       private JPanel rightPanel = new JPanel();
//       private  JPanel leftPanel = new JPanel();
//       private  JPanel upPanel = new JPanel();
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

        JSplitPane panel_1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel_1.setDividerLocation(250);
        panel_1.setDividerSize(5);

        JSplitPane upPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        upPanel.setDividerLocation(250);
        upPanel.setDividerSize(5);
        //upPanel.setOneTouchExpandable(true);

        panel_1.setLeftComponent(upPanel);
        panel_1.setRightComponent(scenePanel);

        upPanel.setLeftComponent(userPanel);
        upPanel.setRightComponent(b1);

        mainFrame.add(panel_1,"Center");

        userPanel.setUserInfo("username","time");
       // userPanel.add(userPanel,"North");


    }
    public static void main(String[] args){
        new MainWindow();
    }

//    private class ButtonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//                // 利用getActionCommand获得按钮名称
//                // 也可以利用getSource()来实现
//                // if (e.getSource() ==button1)
//                String buttonName = e.getActionCommand();
//                if (buttonName.equals("按钮1")) {
//                    JOptionPane.showMessageDialog(mainFrame, "按钮1 被点击");
//                }else if (buttonName.equals("按钮2"))  {
//                    JOptionPane.showMessageDialog(mainFrame, "按钮2 被点击");
//                }else {
//                    JOptionPane.showMessageDialog(mainFrame,"Unknown event" );
//                }
//        }
//    }

}
