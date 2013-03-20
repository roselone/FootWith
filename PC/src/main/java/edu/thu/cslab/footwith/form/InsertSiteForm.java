package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.TextFormatException;

import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class InsertSiteForm extends JFrame {
    private  JTextField siteName = new JTextField();
    private  JTextField siteRate = new JTextField();
    private  JTextField siteLocation =new JTextField();
    
    public InsertSiteForm(){

        this.setTitle("增加景点");
        init();
        this.setBounds(200,200,250,200);
        this.setVisible(true);
    }

    private void init() {

        JLabel siteNameLb = new JLabel("景点名称");
        final JLabel isName = new JLabel();
        JLabel siteRateLb = new JLabel("景点级别");
        final JLabel isRate = new JLabel();
        JLabel siteLocationLb = new JLabel("景点位置");
        JLabel isLocation = new JLabel();

        this.setLayout(new GridLayout(5,3));
        this.add(siteNameLb);
        this.add(siteName);
        this.add(isName);

        this.add(siteRateLb);
        this.add(siteRate);
        this.add(isRate);

        this.add(siteLocationLb);
        this.add(siteLocation);
        this.add(isLocation);


        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());

        JButton btnAdd = new JButton("增加");
        final JButton btnReset = new JButton("重置");
        this.add(btnAdd);
        this.add(btnReset);

         btnAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 isName.setText("");
                 isRate.setText("");
                int response = JOptionPane.showConfirmDialog(null,"确定？","are you sure?",JOptionPane.YES_NO_CANCEL_OPTION);
                 if(response == JOptionPane.YES_OPTION){
                     Mediator pm=new Mediator();
                     try{
                         pm.addSiteFromForm(siteName.getText(), siteRate.getText(),siteLocation.getText());
                     } catch (SQLException e1) {

                         isName.setText("信息有误");
                         isRate.setText("请重新输入");

                     }

                 }else {
                     return;
                 }
             }
         });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siteLocation.setText("");
                siteName.setText("");
                siteRate.setText("");
            }
        });


    }

    public JTextField getSiteName() {
        return siteName;
    }

    public void setSiteName(JTextField siteName) {
        this.siteName = siteName;
    }

    public JTextField getSiteRate() {
        return siteRate;
    }

    public void setSiteRate(JTextField siteRate) {
        this.siteRate = siteRate;
    }

    public JTextField getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(JTextField siteLocation) {
        this.siteLocation = siteLocation;
    }

   
}
