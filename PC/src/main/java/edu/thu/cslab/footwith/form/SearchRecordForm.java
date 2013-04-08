package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.DBUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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
    JTable table = null;
    // private JTextField
    //private  SitePanel  sitePanel = new SitePanel();
    public SearchRecordForm(){

        this.setBounds(200,250,700,500);
        this.setTitle("查询行程");
        init();
        this.setVisible(true);
    }

    private void init() {

              BorderLayout borderLayout = new BorderLayout();
              borderLayout.setVgap(5);
              FlowLayout flowLayout = new FlowLayout();
              flowLayout.setHgap(5);

             // GridLayout gridLayout = new GridLayout(2,1);

              JPanel topPanel = new JPanel();
             // topPanel.setLayout(gridLayout);

              JPanel leftPanel = new JPanel();
              JPanel centerPanel = new JPanel();
              JPanel rightPanel = new JPanel();
              JButton btnByUserId = new JButton("查询");

              JButton btnByDate = new JButton("查询");
              JButton btnBySite = new JButton("查询");

              topPanel.add(leftPanel);
              topPanel.add(centerPanel);
              topPanel.add(rightPanel);

              leftPanel.setBorder(new TitledBorder("按用户Id查询"));
              centerPanel.setBorder(new TitledBorder("按时间查询"));
              rightPanel.setBorder(new TitledBorder("按景点位置查询"));

              final JTextField userId = new JTextField(10);
              leftPanel.add(userId);
              leftPanel.add(btnByUserId);

              final JTextField date = new JTextField(10) ;
              centerPanel.add(date);
              centerPanel.add(btnByDate);

              JTextField location = new JTextField(10);
              rightPanel.add(location);
              rightPanel.add(btnBySite);

              getContentPane().setLayout(borderLayout);
              getContentPane().add(topPanel,"North");

             table = new JTable();
              JScrollPane tableView = new JScrollPane(table);
              getContentPane().add(tableView,"Center");

              btnByUserId.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                        //
                      if(userId.getText().toString().length() == 0) {
                          JOptionPane.showMessageDialog(null,"用户ID不能为空");
                      } else{
                            setTable();
                      }
                  }
              });
              btnByDate.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if(date.getText().toString().length() == 0){
                          JOptionPane.showMessageDialog(null,"日期不能为空");
                      } else {

                                setTable();
                               // function: get all the record whose start time is after the given time.
                      }
                  }
              });
    }
    void  setTable() {
        DefaultTableModel model = new ViewTable().makeTable("select * from record ");
        table.setModel(model);
    }

}
