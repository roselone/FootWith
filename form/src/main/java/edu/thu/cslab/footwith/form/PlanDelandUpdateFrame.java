package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-25
 * Time: 上午12:03
 * To change this template use File | Settings | File Templates.
 */
public class PlanDelandUpdateFrame extends JFrame {


    public  JTable table = new JTable();
    String[] columnNames;
    int planId = -1;
    public  PlanDelandUpdateFrame(){
        super();
        final BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        // setIconifiable(true);
        // setClosable(true);
        setTitle("我的计划管理");
        setBounds(100,100,593,406);
        final  JPanel mainPanel = new JPanel();
        final  BorderLayout borderLayout1 = new BorderLayout();
        borderLayout1.setVgap(5);
        mainPanel.setLayout(borderLayout1);
        mainPanel.setBorder(new EmptyBorder(5,10,5,10));
        getContentPane().add(mainPanel);
        final JScrollPane scrollPane = new JScrollPane();
        mainPanel.add(scrollPane);

        // Object[][] results = dataSource.getAllLocations();  //get all site info

        columnNames = new String[]{"组织者","开始时间","结束时间","人数","参观景点"};

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        scrollPane.setViewportView(table);
        setTable();

        final JPanel planPanel = new JPanel();
        mainPanel.add(planPanel,"South");
        final  GridLayout gridLayout = new GridLayout(0,4);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        planPanel.setLayout(gridLayout);

        JLabel organizerLabel = new JLabel("组织者");
        planPanel.add(organizerLabel);
        final JTextField organizer = new JTextField();
        planPanel.add(organizer);

        final JLabel startTimeLabel = new JLabel();
        startTimeLabel.setText("开始时间");
        planPanel.add(startTimeLabel);
        final JTextField startTime = new JTextField();
        planPanel.add(startTime);

        final JLabel endTimeLabel = new JLabel("结束时间");
        final JTextField endTime = new JTextField();
        planPanel.add(endTimeLabel);
        planPanel.add(endTime);

        JLabel groupNumMaxLabel = new JLabel("人数");
        planPanel.add(groupNumMaxLabel);
        final JTextField groupNumMax = new JTextField();
        planPanel.add(groupNumMax);

        final JLabel siteSetsLabel = new JLabel();
        siteSetsLabel.setText("景点");
        final JTextField siteSets = new JTextField();
        planPanel.add(siteSetsLabel);
        planPanel.add(siteSets);

        final JLabel statusLabel = new JLabel("计划是否开始");

        Vector status = new Vector();
        status.addElement("");
        status.addElement("开始");
        final JComboBox statusCombo = new JComboBox(status);
        planPanel.add(statusLabel);
        planPanel.add(statusCombo);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = table.getSelectedRow();
                organizer.setText(table.getValueAt(selRow,4).toString().trim());
                startTime.setText(table.getValueAt(selRow,2).toString().trim());
                endTime.setText(table.getValueAt(selRow,3).toString().trim());
                groupNumMax.setText(table.getValueAt(selRow,7).toString().trim());
                siteSets.setText(table.getValueAt(selRow,1).toString().trim());
                planId = Integer.valueOf(table.getValueAt(selRow,0).toString().trim());
            }

        });

        final  JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
        getContentPane().add(bottomPanel,"South") ;
        final  FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        bottomPanel.setLayout(flowLayout);
        final  JButton setoff = new JButton("出发");
        setoff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String,String> new_plan_map = new HashMap<String, String>();
                new_plan_map.put("title",null) ;
                new_plan_map.put("siteIDs",siteSets.getText().toString());
                new_plan_map.put("startTime", Date.valueOf(startTime.getText()).toString());
                new_plan_map.put("endTime",Date.valueOf(endTime.getText()).toString());
                new_plan_map.put("organizer",organizer.getText().toString());
                new_plan_map.put("participants",null);
                new_plan_map.put("budget",String.valueOf(-1));
                new_plan_map.put("groupNum",String.valueOf(-1));
                new_plan_map.put("groupNumMax",groupNumMax.getText().toString());
                new_plan_map.put("talkStreamID",String.valueOf(-1));
                new_plan_map.put("isDone", String.valueOf(false));
                JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
                String newPlan = jsonHelper.convertToString(new_plan_map);


                try {
                    boolean flag = false;
                    try {

                        flag = Mediator.addRecordFromPlan(newPlan);
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    try {
                        try {
                            Mediator.deletePlan(planId);
                        } catch (TextFormatException e1) {
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    if(flag == true) {
                        JOptionPane.showMessageDialog(null,"出发成功");
                        // Object[][] results = getAllSite();
                       setTable();
                        //    model.setDataVector(results,columnNames);
                    }

                } catch (JSONException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        });
        final JButton delButton = new JButton();
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String,String> new_plan_map = new HashMap<String, String>();
                new_plan_map.put("title",null) ;
                new_plan_map.put("siteIDs",siteSets.getText().toString());
                new_plan_map.put("startTime", Date.valueOf(startTime.getText()).toString());
                new_plan_map.put("endTime",Date.valueOf(endTime.getText()).toString());
                new_plan_map.put("organizer",organizer.getText().toString());
                new_plan_map.put("participants",null);
                new_plan_map.put("budget",String.valueOf(-1));
                new_plan_map.put("groupNum",String.valueOf(-1));
                new_plan_map.put("groupNumMax",groupNumMax.getText().toString());
                new_plan_map.put("talkStreamID",String.valueOf(-1));
                new_plan_map.put("isDone", String.valueOf(false));
                JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
                String tmpPlan = jsonHelper.convertToString(new_plan_map);

                try {
                    Mediator.deletePlan(planId);
                    JOptionPane.showMessageDialog(null,"删除成功");
                    // Object[][] results = getAllSite();
                    setTable();
                } catch (TextFormatException e3) {
                    e3.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        delButton.setText("删除");
        bottomPanel.add(delButton);
        final  JButton updateButton = new JButton();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int i =  dataOPeraion
                //  updateSite(siteName,siteRate,siteLocation,siteBrief,sitePicture)


                HashMap<String,String> new_plan_map = new HashMap<String, String>();
                new_plan_map.put("title",null) ;
                new_plan_map.put("siteIDs",siteSets.getText().toString());
                new_plan_map.put("startTime", Date.valueOf(startTime.getText()).toString());
                new_plan_map.put("endTime",Date.valueOf(endTime.getText()).toString());
                new_plan_map.put("organizer",organizer.getText().toString());
                new_plan_map.put("participants",null);
                new_plan_map.put("budget",String.valueOf(-1));
                new_plan_map.put("groupNum",String.valueOf(-1));
                new_plan_map.put("groupNumMax",groupNumMax.getText().toString());
                new_plan_map.put("talkStreamID",String.valueOf(-1));
                new_plan_map.put("isDone", String.valueOf(false));
                JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
                String new_plan = jsonHelper.convertToString(new_plan_map);
                try {
                Mediator.editPlan(planId,new_plan);
                    JOptionPane.showMessageDialog(null,"修改成功");
                    // Object[][] results = getAllSite();
                   setTable();
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (SQLException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (JSONException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        });
        updateButton.setText("更新");
        bottomPanel.add(updateButton);
        final  JButton closeButton = new JButton();
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  doDefaultCloseAction();

            }
        });
        closeButton.setText("关闭");
        bottomPanel.add(closeButton);


        this.setVisible(true);
        final JLabel headLogo = new JLabel();


    }

    void setTable(){
       // DefaultTableModel model = new ViewTable().makeTable("select * from plan");
        DefaultTableModel model = makeTable();
        table.setModel(model);
    }
    private DefaultTableModel makeTable(){
        DefaultTableModel model = new DefaultTableModel(columnNames,3);
        Vector<String>   planVector=null;
        JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();


        try {
            try {
                planVector = Mediator.selectPlanFromForm(Global.username,null,"1970-1-1","2100-12-31");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //"组织者","开始时间","结束时间","人数","参观景点"
        if(planVector!=null)
        {
        for(int i=0;i < planVector.size();i++)
        {
            Vector tmpRow = new Vector();
            HashMap<String,String> planMap = new HashMap<String, String>();
            planMap = jsonHelper.convertToMap(planVector.get(i));
            tmpRow.add(planMap.get("organizer"));
            tmpRow.add(planMap.get("startTime"));
            tmpRow.add(planMap.get("endTime"));
            tmpRow.add(planMap.get("groupNumMax"));
            tmpRow.add(planMap.get("siteIDs"));

            model.addRow(tmpRow);
        }
        }
        return model;
    }

}





