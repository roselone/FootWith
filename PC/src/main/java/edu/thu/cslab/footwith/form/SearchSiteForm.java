package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午6:58
 * To change this template use File | Settings | File Templates.
 */
public class SearchSiteForm extends JFrame {
     private  JTextField siteName = new JTextField(10);
     //private  SitePanel  sitePanel = new SitePanel();
     public SearchSiteForm(){
         this.setBounds(200,250,500,500);
         this.setTitle("查询景点信息");
         init();
         this.setVisible(true);
     }

    private void init() {

        final JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerLocation(150);
        jsp.setDividerSize(5);

        JPanel mainPanel = new JPanel();
        JLabel siteNameLb = new JLabel("请输入景点名字");
        JButton btnSearch = new JButton("查询");
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(siteNameLb);
        mainPanel.add(siteName);
        mainPanel.add(btnSearch);

        jsp.setLeftComponent(mainPanel);
       // jsp.setRightComponent(sitePanel);

        this.add(jsp);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(siteName.getText() == null || siteName.getText().length() ==0 )
                    jsp.setRightComponent(new SitePanel("select * from site"));
                else {
                       jsp.setRightComponent(new SitePanel("select * from site where siteName ="+"'"+siteName.getText()+"'"));
                }
                   //    System.out.println("where siteName ="+"'"+siteName.getText()+"'");
            }
        });

        //JScrollPane  viewPanel = new JScrollPane();




    }
}
