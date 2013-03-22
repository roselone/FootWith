package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.Mediator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
public class SiteManage extends JFrame  {
   // private  MainWindow frame = null;
    private  JButton btnAdd = new JButton("添加");
   // private  JButton btnDelete = new JButton("删除");
    private  JButton btnUpdate  = new JButton("更新");
  //  private  JButton btnQuery = new JButton("查找");

   // private  JButton btnReset = new JButton("重置");
  //  private  JButton btnHelp = new JButton("帮助");
    private  JTextField  siteName = new JTextField();
   // private  JTextField  siteName = new JTextField();
    private  JTextField  siteLocation = new JTextField();
    private  JTextField  siteRate = new JTextField();

    // picture
    public SiteManage(){
      //  super(frame,title);
     init();
     this.setBounds(200,200,300,300);
   //  this.setVisible(true);
    }

    private void init() {

       //MyButtonLister myButtonLister = new MyButtonLister();
       this.setTitle("景点信息管理界面" );
        // this.setLayout(new FlowLayout());
      //  JPanel topPane = new JPanel();
       this.setLayout(new GridLayout(4, 2));
//       this.add(new JLabel("景点ID"));
//       this.add(siteId);
       this.add(new JLabel("景点名称"));
        this.add(siteName);
        this.add(new JLabel("景点等级"));
        this.add(siteRate);
        this.add(new JLabel("景点位置"));
        this.add(siteLocation);


        this.add(btnAdd);
     //  this.add(btnDelete);
        this.add(btnUpdate);
     //   this.add(btnQuery);

     //   btnAdd.addActionListener(myButtonLister);
      //  btnUpdate.addActionListener(myButtonLister);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                        int response = JOptionPane.showConfirmDialog(null,"确定？","are you sure?",JOptionPane.YES_NO_CANCEL_OPTION);
                        if(response == JOptionPane.YES_OPTION){
                            Mediator pm=new Mediator();
                            try{
                                pm.addSiteFromForm(siteName.getText(), siteRate.getText(),siteLocation.getText());
                            } catch (SQLException e1) {
                            //    JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.ERROR_MESSAGE);
                                JOptionPane.showMessageDialog(null,"输入格式有问题","warning",JOptionPane.ERROR_MESSAGE);
                            //    isName.setText("信息有误");
                           //     isRate.setText("请重新输入");

                            }

                        }else {
                            return;
                        }
            }
        });
            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(null,"确定？","are you sure?",JOptionPane.YES_NO_CANCEL_OPTION);
                    if(response == JOptionPane.YES_OPTION){
                        Mediator pm=new Mediator();
                        try{
                            pm.addSiteFromForm(siteName.getText(), siteRate.getText(),siteLocation.getText());
                        } catch (SQLException e1) {
                            //    JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.ERROR_MESSAGE);
                            JOptionPane.showMessageDialog(null,"输入格式有问题","warning",JOptionPane.ERROR_MESSAGE);
                            //    isName.setText("信息有误");
                            //     isRate.setText("请重新输入");

                        }

                    }else {
                        return;
                    }
                }
            });
    }
      void setValue(Vector<String> vector){
           //  siteId.setText(vector.get(0));
             siteName.setText(vector.get(1));
             siteLocation.setText(vector.get(2));
             siteRate.setText(vector.get(3));
      }

}
//class MyButtonLister implements ActionListener{
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String cmd = e.getActionCommand();
//        try{
//               if(cmd.equals("添加")){
//                   int response = JOptionPane.showConfirmDialog(null,"确定？","are you sure?",JOptionPane.YES_NO_CANCEL_OPTION);
//                   if(response == JOptionPane.YES_OPTION){
//                       fromFormToManager pm=new fromFormToManager();
//                       try{
//                           pm.addSiteFromForm(siteName.getText(), siteRate.getText(),siteLocation.getText());
//                       } catch (SQLException e1) {
//                           //    JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.ERROR_MESSAGE);
//                           JOptionPane.showMessageDialog(null,"输入格式有问题","warning",JOptionPane.ERROR_MESSAGE);
//                           //    isName.setText("信息有误");
//                           //     isRate.setText("请重新输入");
//
//                       }
//
//                   }else {
//                       return;
//                   }
//
//               }  if(cmd.equals("删除")){
//
//            } if(cmd.equals("更新")){
//
//            } if(cmd.equals("查找")){
//
//                int response = JOptionPane.showConfirmDialog(null,"确定？","are you sure?",JOptionPane.YES_NO_CANCEL_OPTION);
//                if(response == JOptionPane.YES_OPTION){
//                    fromFormToManager pm=new fromFormToManager();
//                    try{
//                        pm.addSiteFromForm(siteName.getText(), siteRate.getText(),siteLocation.getText());
//                    } catch (SQLException e1) {
//                        //    JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条文字串", JOptionPane.ERROR_MESSAGE);
//                        JOptionPane.showMessageDialog(null,"输入格式有问题","warning",JOptionPane.ERROR_MESSAGE);
//                        //    isName.setText("信息有误");
//                        //     isRate.setText("请重新输入");
//
//                    }
//
//                }else {
//                    return;
//                }
//            }
//        }catch (Exception e1){
//
//        }
//    }
//}