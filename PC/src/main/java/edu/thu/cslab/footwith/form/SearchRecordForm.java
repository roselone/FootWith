package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午10:21
 * To change this template use File | Settings | File Templates.
 */
public class SearchRecordForm extends  JFrame {

    private JTextField siteName = new JTextField(10);
    private JTextField  userId = new JTextField(10);
    // private JTextField
    //private  SitePanel  sitePanel = new SitePanel();
    public SearchRecordForm(){

        this.setBounds(200,250,500,500);
        this.setTitle("查询行程");
        init();
        this.setVisible(true);
    }

    private void init() {

        final JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerLocation(150);
        jsp.setDividerSize(5);

        JPanel searchPanel = new JPanel();
        JLabel userIdLb = new JLabel("请输入用户ID");

        JButton btnSearch = new JButton("查询");
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(userIdLb);
        searchPanel.add(userId);
        searchPanel.add(btnSearch);

        jsp.setLeftComponent(searchPanel);
        // jsp.setRightComponent(sitePanel);

        this.add(jsp);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(userId.getText() == null || userId.getText().length() ==0 )
                    jsp.setRightComponent(new SitePanel("select * from record"));
                else {
                    jsp.setRightComponent(new SitePanel("select * from record where userIDs ="+"'"+userId.getText()+"'"));
                }
                //    System.out.println("where siteName ="+"'"+siteName.getText()+"'");
            }
        });

        //JScrollPane  viewPanel = new JScrollPane();




    }
}
