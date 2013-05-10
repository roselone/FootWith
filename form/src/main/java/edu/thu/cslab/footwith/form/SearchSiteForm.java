package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

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
        JTable table = new JTable(makeTable());
        final JScrollPane jScrollPane = new JScrollPane(table);
        this.add(jsp);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(siteName.getText()==null) {
                    JOptionPane.showMessageDialog(null,"empty");
                } else{
                    jsp.setRightComponent(jScrollPane);
                }

            }
        });
    }

        //JScrollPane  viewPanel = new JScrollPane();

    private DefaultTableModel makeTable(){
        String[] columnNames={"siteName","rate","location","brief","picture"};
        DefaultTableModel model = new DefaultTableModel(columnNames,1);
       String sitesVector=null;
        JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
        try {
            try {
                sitesVector =  Mediator.selectSite(siteName.getText().trim());
            } catch (TextFormatException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //sitesVector = Mediator.selectSite(siteName.getText().toString().trim());

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(sitesVector!=null)
        {

                Vector tmpRow = new Vector();
                HashMap<String,String> siteMap = new HashMap<String, String>();
                siteMap = jsonHelper.convertToMap(sitesVector);
                tmpRow.add(siteMap.get("siteName"));
                tmpRow.add(siteMap.get("rate"));
                tmpRow.add(siteMap.get("location"));
                tmpRow.add(siteMap.get("brief"));
                tmpRow.add(siteMap.get("picture"));

                model.addRow(tmpRow);

        }
        return model;
    }



}
