package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class InsertPlanForm extends JFrame {

       private  JTextField siteName = new JTextField();
    private  JTextField startTime = new JTextField();
    private  JTextField endTime = new JTextField();
    private  JTextField organizer = new JTextField();
    private  JTextField numbers = new JTextField();

    public  InsertPlanForm() {
        this.setBounds(200,200,300,200);
        init();
        this.setVisible(true);
    }

    private void init() {
       this.setTitle("定制计划");
       JPanel title = new JPanel();
       JPanel data = new JPanel();
       this.setLayout(new BorderLayout());
       this.add(title,"North");
       this.add(data,"Center");

       data.setLayout(new GridLayout(7, 3));
       JLabel sceneNameLb = new JLabel("景点名");
       JLabel isSceneOk = new JLabel();
       JLabel startTimeLb = new JLabel("开始时间");
       JLabel isStartOk = new JLabel();
       JLabel endTimeLb = new JLabel("结束时间");
       JLabel isEndOk = new JLabel();
       JLabel organizerLb = new JLabel("组织者");
       JLabel isOrgOk = new JLabel();
      // JLabel numberLb = new JLabel("参与人数");
      // JLabel isNumOk = new JLabel();

        data.add(sceneNameLb);
        data.add(siteName);
        data.add(isSceneOk);
        data.add(startTimeLb);
        data.add(startTime);
        data.add(isStartOk);
        data.add(endTimeLb);
        data.add(endTime);
        data.add(isEndOk);
        data.add(organizerLb);
        data.add(organizer);
        data.add(isOrgOk);
       // data.add(numberLb);
      //  data.add(numbers);
      //  data.add(isNumOk);

        data.add(new JLabel());
        data.add(new JLabel());
        data.add(new JLabel());
        JButton btnAdd = new JButton("增加");
        JButton btnReset = new JButton("重置");
       
        data.add(btnAdd);
        data.add(btnReset);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    siteName.setText("");
                    startTime.setText("");
                    endTime.setText("");
                    organizer.setText("");
            }
        });


        
    }


}
