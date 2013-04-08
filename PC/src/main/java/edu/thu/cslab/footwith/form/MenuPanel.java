package edu.thu.cslab.footwith.form;
import edu.thu.cslab.footwith.server.TextFormatException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-13
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class MenuPanel extends JRootPane {

    public MenuPanel(){

        initMenu();
    }

    private void initMenu() {

        MyMenuLister myMenuListener = new MyMenuLister();

        // JPanel topPanel = new JPanel();
        JMenuBar jmb = new JMenuBar();
        JMenu menu1 = new JMenu("用户管理");
        JMenu menu2 = new JMenu("景点管理");
        JMenu menu3 = new JMenu("行程管理");
        JMenu menu4 = new JMenu("计划管理");

      //  JMenuItem jmi1 = new JMenuItem("用户信息");
        JMenuItem jmi11 = new JMenuItem("用户管理");

        JMenuItem jmi21 = new JMenuItem("增加景点");
        JMenuItem jmi22 = new JMenuItem("景点修改");
        JMenuItem jmi23 = new JMenuItem("景点查询");

        JMenuItem jmi41 = new JMenuItem("定制计划");
        JMenuItem jmi42 = new JMenuItem("计划查询");
        JMenuItem jmi43 = new JMenuItem("我的计划");

        JMenuItem jmi31 = new JMenuItem("我的行程");
        JMenuItem jmi32  = new JMenuItem("行程查询");

       // menu1.add(jmi1);
        menu1.add(jmi11);
        menu2.add(jmi21) ;
        menu2.add(jmi22);
        menu2.add(jmi23);

        menu3.add(jmi31);
        menu3.add(jmi32);


        menu4.add(jmi41);
        menu4.add(jmi42);
        menu4.add(jmi43);


        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        jmb.add(menu4);

       jmb.setSize(300,50);
      //  jmb.setVisible(true);

        //jmi1.addActionListener(myMenuListener);
        jmi11.addActionListener(myMenuListener);
        jmi21.addActionListener(myMenuListener);
        jmi22.addActionListener((myMenuListener));
        jmi23.addActionListener(myMenuListener);
        jmi31.addActionListener(myMenuListener);
        jmi32.addActionListener(myMenuListener);
        jmi41.addActionListener(myMenuListener);
        jmi42.addActionListener(myMenuListener);
        jmi43.addActionListener(myMenuListener);

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
//            TextArea ta=new TextArea(6,40);//这个文本框6行 40列
//            ta.append("单击"+cmd+"菜单"+"\n");
            if(cmd.equals("退出")){
//                ta.append("正在退出\n");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.exit(0);
            }if(cmd.equals("用户管理")){
                try {
                    new UserInfoFrame();
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } if(cmd.equals("景点管理")){

                new SiteManage();

            }  if(cmd.equals("增加景点")){
                new InsertSiteForm();
            }  if(cmd.equals("景点查询")){
                new SearchSiteForm();
            } if(cmd.equals("景点修改")) {
                new SiteModiAndDelFrame();
            } if(cmd.equals("行程查询")){
              new SearchRecordForm();
            } if(cmd.equals("我的行程")){
                new RecordDelandUpdateFrame();
            }
            if(cmd.equals("行程修改")){

            } if(cmd.equals("我的计划")){
                new PlanDelandUpdateFrame();
            }  if(cmd.equals("定制计划")){
                // new PlanManage();
                new InsertPlanForm();
            }  if(cmd.equals("计划查询")){
                new SearchPlanForm();
            }

        }
    }

