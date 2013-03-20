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
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class InsertPlanForm extends JFrame {

    private  JTextField siteName = new JTextField();
    private  JTextField startTime = new JTextField();
    private  JTextField endTime = new JTextField();
    private  JTextField organizer = new JTextField();
    private  JTextField numbers = new JTextField();

    public JTextField getSiteName() {
        return siteName;
    }

    public void setSiteName(JTextField siteName) {
        this.siteName = siteName;
    }

    public JTextField getStartTime() {
        return startTime;
    }

    public void setStartTime(JTextField startTime) {
        this.startTime = startTime;
    }

    public JTextField getEndTime() {
        return endTime;
    }

    public void setEndTime(JTextField endTime) {
        this.endTime = endTime;
    }

    public JTextField getOrganizer() {
        return organizer;
    }

    public void setOrganizer(JTextField organizer) {
        this.organizer = organizer;
    }

    public JTextField getNumbers() {
        return numbers;
    }

    public void setNumbers(JTextField numbers) {
        this.numbers = numbers;
    }

    public  InsertPlanForm() {
        this.setBounds(200,200,300,200);
        init();
        this.setVisible(true);
    }

    private void init() {
       this.setTitle("定制计划");
       JPanel title = new JPanel();
       final JPanel data = new JPanel();
       this.setLayout(new BorderLayout());
       this.add(title,"North");
       this.add(data,"Center");

       data.setLayout(new GridLayout(7, 3));
       JLabel sceneNameLb = new JLabel("景点名");
       final JLabel isSceneOk = new JLabel();
       JLabel startTimeLb = new JLabel("开始时间");
       final JLabel isStartOk = new JLabel();
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
        final JButton btnReset = new JButton("重置");
       
        data.add(btnAdd);
        data.add(btnReset);

 //       MyActionActionLister myBtnAddActionLister = new MyActionActionLister();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isSceneOk.setText("");
                isStartOk.setText("");
               int response =  JOptionPane.showConfirmDialog(null,"确定提交","are you sure",JOptionPane.YES_NO_OPTION);
               if(response == JOptionPane.YES_OPTION){
                   Mediator pm=new Mediator();
                  try{
                      pm.addPlanFromForm(siteName.getText(), startTime.getText(),endTime.getText(),organizer.getText());
                  }catch (TextFormatException e1) {
                      isSceneOk.setText("信息有误");
                      isStartOk.setText("请重新输入");
                      e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                  } catch (SQLException e1) {
                      isSceneOk.setText("信息有误");
                      isStartOk.setText("请重新输入");
                      e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                  } catch (JSONException e1) {
                      isSceneOk.setText("信息有误");
                      isStartOk.setText("请重新输入");
                      e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                  }


               }  else{
                  return;
               }




            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    siteName.setText("");
                    startTime.setText("");
                    endTime.setText("");
                    organizer.setText("");
                    isSceneOk.setText("");
                    isStartOk.setText("");

            }
        });


        
    }


    class MyActionActionLister implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }
}
