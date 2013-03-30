package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.Site;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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

        String[] columnNames = new String[]{"组织者","开始时间","结束时间","人数","参观景点"};

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new TableListener());
        scrollPane.setViewportView(table);


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








        final  JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
        getContentPane().add(bottomPanel,"South") ;
        final  FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        bottomPanel.setLayout(flowLayout);
        final JButton delButton = new JButton();
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //return 0,1 delSite(siteName);

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
    //  ImageIcon siteModiandDelIcon = CreatecdIcon
}





