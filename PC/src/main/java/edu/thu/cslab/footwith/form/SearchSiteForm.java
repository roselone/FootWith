package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午6:58
 * To change this template use File | Settings | File Templates.
 */
public class SearchSiteForm extends JFrame {
     private  JTextField siteName = new JTextField();
     private  SitePanel  sitePanel = new SitePanel();
     public SearchSiteForm(){
         this.setBounds(200,250,500,500);
         this.setTitle("查询景点信息");
         init();
         this.setVisible(true);
     }

    private void init() {

        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerLocation(50);
        jsp.setDividerSize(5);

        JPanel searchPanel = new JPanel();
        JLabel siteNameLb = new JLabel("请输入景点名字");
        JButton btnSearch = new JButton("查询");
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(siteNameLb);
        searchPanel.add(siteName);
        searchPanel.add(btnSearch);

        JPanel viewPanel = new JPanel();


    }
}
