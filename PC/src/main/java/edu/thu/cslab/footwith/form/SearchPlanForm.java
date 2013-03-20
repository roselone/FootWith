package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午10:12
 * To change this template use File | Settings | File Templates.
 */
public class SearchPlanForm extends JFrame {
  //  private  String siteSet = null;
    private JTextField siteName = new JTextField(20);
    private JTextField  userId = new JTextField(10);
    private  JLabel showLb = new JLabel();
   // private static JEditorPane siteName_1;
    public static  String ChoiceName = "";
    // private JTextField
    //private  SitePanel  sitePanel = new SitePanel();

    public SearchPlanForm(){

        this.setBounds(200,250,500,500);
        this.setTitle("查询计划");
        init();

        this.setVisible(true);
    }

    public JTextField getSiteName() {
        return siteName;
    }

    public void setSiteName(JTextField siteName) {
        this.siteName = siteName;
    }

    private void init() {

        final JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerLocation(250);
        jsp.setDividerSize(5);

        JPanel searchPanel = new JPanel();
        JLabel userIdLb = new JLabel("请输入用户ID");
        JLabel siteIdLb = new JLabel(("请输入景点信息"));
        JButton btnChoice = new JButton("请选择景点");
        JButton btnSearch = new JButton("查询");
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(userIdLb);
        searchPanel.add(userId);

        searchPanel.add(showLb);
        searchPanel.add(siteIdLb);
        searchPanel.add(btnChoice);
        searchPanel.add(siteName);
        btnChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // showLb.setText("");
                siteName.setText(Global.cityInfo);
                SiteSearch frame = new SiteSearch();

                siteName.setText(Global.cityInfo);
            //    System.out.println(frame.cityInfo);
            }
        });
        searchPanel.add(btnSearch);

        jsp.setLeftComponent(searchPanel);
        // jsp.setRightComponent(sitePanel);

        this.add(jsp);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(userId.getText() == null || userId.getText().length() ==0 )
                    jsp.setRightComponent(new SitePanel("select * from plan"));
                else {
                    jsp.setRightComponent(new SitePanel("select * from plan where organizer ="+"'"+userId.getText()+"'"));
                }
                //    System.out.println("where siteName ="+"'"+siteName.getText()+"'");
            }
        });

        //JScrollPane  viewPanel = new JScrollPane();




    }

}
