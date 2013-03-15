package edu.thu.cslab.footwith.form;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-12
 * Time: 下午11:39
 * To change this template use File | Settings | File Templates.
 */
public class mainWindow {
       private JFrame mainFrame = new JFrame("旅游行程管理系统");
       private Container c = mainFrame.getContentPane();
       private JMenu   mainMemu = new JMenu();
       private JPanel rightPanel = new JPanel();
       private  JPanel leftPanel = new JPanel();
       private  JPanel upPanel = new JPanel();
       private  ButtonListener ourListener = new ButtonListener();

    public mainWindow(){
        mainFrame.setBounds((function.getScreenSize().width-mainFrame.getSize().width)/2,
                (function.getScreenSize().height-mainFrame.getSize().height)/2,300,200);
        initMainFrame();
        mainFrame.setVisible(true);
    }

    private void initMainFrame() {

        ActionListener menuListener=new ActionListener(){
            @Override
            //匿名内部类来创建菜单监听器
            public void actionPerformed(ActionEvent e) {
                String cmd=e.getActionCommand();
                TextArea ta=new TextArea(6,40);//这个文本框6行 40列
                ta.append("单击"+cmd+"菜单"+"\n");
                if(cmd.equals("退出")){
                    ta.append("正在退出\n");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }if(cmd.equals("用户信息")){
                    new UserInfo("wjy");
                }

            }

        } ;
        //To change body of created methods use File | Settings | File Templates.
        c.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JMenuBar jmb = new JMenuBar();
        JMenu menu1 = new JMenu("用户管理");
        JMenu menu2 = new JMenu("景点管理");
        JMenu menu3 = new JMenu("行程管理");
        JMenuItem jmi1 = new JMenuItem("用户信息");
        JMenuItem jmi2 = new JMenuItem("用户管理");
        JMenuItem jmi3 = new JMenuItem("景点信息");
        JMenuItem jmi4 = new JMenuItem("景点管理");
        JMenuItem jmi5 = new JMenuItem("行程信息");
        JMenuItem jmi6 = new JMenuItem("行程管理");
        mainFrame.setJMenuBar(jmb);
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        menu1.add(jmi1);
        menu1.add(jmi2);
        menu2.add(jmi3);
        menu2.add(jmi4);
        menu3.add(jmi5);
        menu3.add(jmi6);

        jmb.setSize(300,50);
        jmb.setVisible(true);

        jmi1.addActionListener(menuListener);
        jmi2.addActionListener(menuListener);
        jmi3.addActionListener(menuListener);
        jmi4.addActionListener(menuListener);
        jmi5.addActionListener(menuListener);
        jmi6.addActionListener(menuListener);


    }
    public static void main(String[] args){
        new mainWindow();
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                // 利用getActionCommand获得按钮名称
                // 也可以利用getSource()来实现
                // if (e.getSource() ==button1)
                String buttonName = e.getActionCommand();
                if (buttonName.equals("按钮1")) {
                    JOptionPane.showMessageDialog(mainFrame, "按钮1 被点击");
                }else if (buttonName.equals("按钮2"))  {
                    JOptionPane.showMessageDialog(mainFrame, "按钮2 被点击");
                }else {
                    JOptionPane.showMessageDialog(mainFrame,"Unknown event" );
                }
        }
    }
    //注册监听器
   private class MyMenuLister implements  MenuListener{



        @Override
        public void menuSelected(MenuEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void menuDeselected(MenuEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void menuCanceled(MenuEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }



}
