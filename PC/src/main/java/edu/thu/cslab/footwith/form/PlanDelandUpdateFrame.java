package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.*;
import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-25
 * Time: 上午12:03
 * To change this template use File | Settings | File Templates.
 */
public class PlanDelandUpdateFrame extends JFrame {

    private Mediator dataSource = new Mediator();
    public  JTable table = new JTable();
    String[] columnNames;
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
                Plan tmpPlan = new Plan();
                try {
                    tmpPlan.setSiteIDs(siteSets.getText().toString());
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setStartTime(Date.valueOf(startTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setEndTime(Date.valueOf(endTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setGroupNumMax(Integer.valueOf(groupNumMax.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setOrganizer(Integer.valueOf(organizer.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                RecordManager recordManager = new RecordManager();
                try {
                    boolean flag = recordManager.addRecordFromPlan(tmpPlan);
                    PlanManager planManager = new PlanManager();
                    try {
                        planManager.deletePlan(tmpPlan.getPlanID());
                    } catch (TextFormatException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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

                Plan tmpPlan = new Plan();
                try {
                    tmpPlan.setSiteIDs(siteSets.getText().toString());
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setStartTime(Date.valueOf(startTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setEndTime(Date.valueOf(endTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setGroupNumMax(Integer.valueOf(groupNumMax.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setOrganizer(Integer.valueOf(organizer.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                PlanManager planManager = new PlanManager();
                try {
                    planManager.deletePlan(tmpPlan.getPlanID());
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
                Plan tmpPlan = new Plan();
                try {
                    tmpPlan.setSiteIDs(siteSets.getText().toString());
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setStartTime(Date.valueOf(startTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setEndTime(Date.valueOf(endTime.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setGroupNumMax(Integer.valueOf(groupNumMax.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                try {
                    tmpPlan.setOrganizer(Integer.valueOf(organizer.getText().toString()));
                } catch (TextFormatException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                PlanManager planManager = new PlanManager();
                try {
                    planManager.editPlan(tmpPlan.getPlanID(),tmpPlan);
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
                int flag = 0;
                if(flag == 1) {
                    JOptionPane.showMessageDialog(null,"修改成功");
                    // Object[][] results = getAllSite();
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    //    model.setDataVector(results,columnNames);
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
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        Vector<Plan>   planVector=null;

        try {
            planVector = dataSource.selectPlanFromForm(Global.username,null,"1970-1-1","2100-12-31");
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //"组织者","开始时间","结束时间","人数","参观景点"
        for(int i=0;i < planVector.size();i++)
        {
            Vector tmpRow = new Vector();

            tmpRow.add(planVector.get(i).getOrganizer());
            tmpRow.add(planVector.get(i).getStartTime());
            tmpRow.add(planVector.get(i).getEndTime());
            tmpRow.add(planVector.get(i).getGroupNumMax());
            tmpRow.add(planVector.get(i).getSiteIDs());

            model.addRow(tmpRow);
        }
        return model;
    }

}





