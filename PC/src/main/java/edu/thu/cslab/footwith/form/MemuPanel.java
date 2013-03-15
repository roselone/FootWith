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
 * Date: 13-3-13
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class MemuPanel extends JRootPane {

    public  MemuPanel(){

        initMenu();
    }

    private void initMenu() {

        MyMenuLister myMenuListener = new MyMenuLister();

        // JPanel topPanel = new JPanel();
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

        menu1.add(jmi1);
        menu1.add(jmi2);
        menu2.add(jmi3) ;
        menu2.add(jmi4);
        menu3.add(jmi5);
        menu3.add(jmi6);

        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);

        jmb.setSize(300,50);
      //  jmb.setVisible(true);

        jmi1.addActionListener(myMenuListener);
        jmi2.addActionListener(myMenuListener);
        jmi3.addActionListener(myMenuListener);
        jmi4.addActionListener(myMenuListener);
        jmi5.addActionListener(myMenuListener);
        jmi6.addActionListener(myMenuListener);

        this.setJMenuBar(jmb);
        this.setVisible(true);
    }
}
    //注册监听器
    class MyMenuLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
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
    }

