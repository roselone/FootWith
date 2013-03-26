package edu.thu.cslab.footwith.form;

import com.sun.org.apache.bcel.internal.generic.NEW;
import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.TextFormatException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午10:12
 * To change this template use File | Settings | File Templates.
 */
public class SearchPlanForm extends JFrame {

    private  String siteSet = null;
    private JTextField siteName = new JTextField(20);
    private JTextField  userId = new JTextField(10);
    private JComboBox cityCombBox = null;
    Vector<String> city;
    Vector<String>  siteNames =null;
    Mediator dataSource = new Mediator();
    // private JTextField
    //private  SitePanel  sitePanel = new SitePanel();

    public SearchPlanForm(){

        this.setBounds(200,250,500,500);
        this.setTitle("查询计划");
        init();
        siteSet = null;
        this.setVisible(true);
    }

    public JTextField getSiteName() {
        return siteName;
    }

    public void setSiteName(JTextField siteName) {
        this.siteName = siteName;
    }

    private void init() {

//        final JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        jsp.setDividerLocation(450);
//        jsp.setDividerSize(5);
        final  BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(5);

        getContentPane().setLayout(borderLayout);
        final JPanel viewPanel = new JPanel();
        final JTable viewTable = new JTable();
        viewPanel.setBorder(new EmptyBorder(5,10,5,10));
        JScrollPane jspViewPanel = new JScrollPane();
        viewPanel.add(jspViewPanel);
        jspViewPanel.setViewportView(viewTable);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5,10,5,10)); // need to understand
        final  GridLayout gridLayout = new GridLayout(5,4);
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        mainPanel.setLayout(gridLayout);
        getContentPane().add(mainPanel,"North");
        getContentPane().add(viewPanel,"Center");

        JLabel userIdLb = new JLabel("请输入用户ID");
        mainPanel.add(userIdLb);
        mainPanel.add(userId);
       // userId.addKeyListener(new userIdKeyListener());

        final JLabel userNameLb = new JLabel("用户名称");
        mainPanel.add(userNameLb);
        final JTextField  userName = new JTextField();
      //  userName.addKeyListener(new userNameKeyListener());
        mainPanel.add(userName);

        JLabel cityLb = new JLabel("景点所在城市");
        city = dataSource.getAllLocations();
        city.addElement("");
        city.insertElementAt("",0);
        //UserComboBoxModel comboBoxCreator = new UserComboBoxModel(city);

        cityCombBox = new JComboBox(city);
        mainPanel.add(cityLb);
        mainPanel.add(cityCombBox);

        JLabel siteIdLb1 = new JLabel(("具体景点信息"));
        mainPanel.add(siteIdLb1);
        final  JComboBox siteNameCom1 = new JComboBox();
        mainPanel.add(siteNameCom1);

        JLabel siteIdLb2 = new JLabel("景点2");
        final JComboBox siteNameCom2 = new JComboBox();
        mainPanel.add(siteIdLb2);
        mainPanel.add(siteNameCom2);

        JLabel siteIdOther = new JLabel("其它景点");
        final  JTextField siteNameText = new JTextField();
        siteNameText.setText("请以，间隔");
        siteNameText.setEnabled(false);
        mainPanel.add(siteIdOther);
        mainPanel.add(siteNameText);
        cityCombBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                       // String item = e.getItem().toString();
                String item = cityCombBox.getSelectedItem().toString();
                         if(item.length() ==0 || item == null )
                             JOptionPane.showMessageDialog(null,"请选择城市");
                             else {
                              siteNames = new Vector<String>();
                             try {

                               siteNames = dataSource.selectSiteNameWithLocation(item.substring(0,item.length()-1));
                             } catch (TextFormatException e1) {
                                 e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                             } catch (SQLException e1) {
                                 e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                             }
                             siteNames.addElement("");
                             siteNames.insertElementAt("",0);
                             DefaultComboBoxModel model = new DefaultComboBoxModel(siteNames);
                            // model.addElement("hello");
                             siteNameCom1.setModel(model);

                           //  siteSet = siteSet+e.getItem().toString();
                         }
            }
        } );

         siteNameCom1.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {



                 DefaultComboBoxModel model = new DefaultComboBoxModel(siteNames);
                 siteNameCom2.setModel(model);
             }
         });
         siteNameCom2.addItemListener(new ItemListener() {
             @Override
             public void itemStateChanged(ItemEvent e) {
                 siteSet = siteSet + e.getItem().toString().trim();
                // System.out.println(siteSet);
             }
         });
        JLabel startTimeLb = new JLabel("开始时间");
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
        JFormattedTextField startTime  = new JFormattedTextField(myfmt);
        startTime.setValue(new java.util.Date());
        mainPanel.add(startTimeLb);
        mainPanel.add(startTime);

        JLabel endTimeLb = new JLabel("结束时间");
        JFormattedTextField endTime = new JFormattedTextField(myfmt);
        endTime.setValue(new Date());
        mainPanel.add(endTimeLb);
        mainPanel.add(endTime);

        JButton btnSearch = new JButton("查询");
        mainPanel.add(btnSearch);


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String city = cityCombBox.getSelectedItem().toString();
                String site = siteNameCom1.getSelectedItem().toString()+siteNameCom2.getSelectedItem().toString();

                System.out.println(city);
                System.out.println(site);
                if(userId.getText() == null || userId.getText().length() ==0 )
                {   // viewPanel.add(new SitePanel("select * from plan"));
                 Vector columnNames = new Vector();
                columnNames.add("开始时间") ;
                columnNames.add("结束时间") ;
                columnNames.add("景点信息") ;
                columnNames.add("费用") ;
                  Object[][] dataVector = {
                      {"1","2","3","4"},
                          {"2","3","4","5"},
                  };

                    DefaultTableModel model = new DefaultTableModel(columnNames,0);
                    viewTable.setModel(model);

                    Vector newRow = new Vector();
                    for(int j=0;j<2;j++)
                    {
                    for(int i=0;i<4;i++)
                    {
                        newRow.addElement(dataVector[j][i]);
                    }
                        model.addRow(newRow);

                    }

                  //  model.setDataVector();
                 //   model.setDataVector(dataVector,columnNames);
                  //  model.addColumn("5");
                    //model.addRow();

                }
                else {
                  //  jsp.setRightComponent(new SitePanel("select * from plan where organizer ="+"'"+userId.getText()+"'"));
                }

            }
        });

    }

}
