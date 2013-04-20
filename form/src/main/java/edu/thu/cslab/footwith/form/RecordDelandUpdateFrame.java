package edu.thu.cslab.footwith.form;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
 * Date: 13-3-31
 * Time: 上午12:05
 * To change this template use File | Settings | File Templates.
 */
    public class RecordDelandUpdateFrame extends JFrame {


        public  JTable table = new JTable();

        public  RecordDelandUpdateFrame(){
            super();
            final BorderLayout borderLayout = new BorderLayout();
            getContentPane().setLayout(borderLayout);
            // setIconifiable(true);
            // setClosable(true);
            setTitle("我的行程管理");
            setBounds(100,100,700,406);
            final  JPanel mainPanel = new JPanel();
            final  BorderLayout borderLayout1 = new BorderLayout();
            borderLayout1.setVgap(5);
            mainPanel.setLayout(borderLayout1);
            mainPanel.setBorder(new EmptyBorder(5,10,5,10));
            getContentPane().add(mainPanel);
            final JScrollPane scrollPane = new JScrollPane();
            mainPanel.add(scrollPane);

            // Object[][] results = dataSource.getAllLocations();  //get all site info

            String[] columnNames = new String[]{"旅行名称","开始时间","结束时间","参与人数","参与人","参观景点","行程状态","日志","图片"};

            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            scrollPane.setViewportView(table);
            setTable();

            final JPanel recordPanel = new JPanel();
            mainPanel.add(recordPanel,"South");
            final  GridLayout gridLayout = new GridLayout(0,4);
            gridLayout.setHgap(5);
            gridLayout.setVgap(5);
            recordPanel.setLayout(gridLayout);

            JLabel organizerLabel = new JLabel("旅行名称");
            recordPanel.add(organizerLabel);
            final JTextField organizer = new JTextField();
            recordPanel.add(organizer);

            final JLabel startTimeLabel = new JLabel("开始时间");
            final JTextField startTime = new JTextField();
            recordPanel.add(startTimeLabel);
            recordPanel.add(startTime);


            final JLabel endTimeLabel = new JLabel("结束时间");
            final JTextField endTime = new JTextField();
            recordPanel.add(endTimeLabel);
            recordPanel.add(endTime);

            JLabel groupNumLabel = new JLabel("参与人数");
            recordPanel.add(groupNumLabel);
            final JTextField groupNum = new JTextField();
            recordPanel.add(groupNum);

            JLabel groupsLabel = new JLabel("参与者");
            recordPanel.add(groupsLabel);
            final JTextField groups = new JTextField();
            recordPanel.add(groups);

            final JLabel siteSetsLabel = new JLabel();
            siteSetsLabel.setText("景点");
            final JTextField siteSets = new JTextField();
            recordPanel.add(siteSetsLabel);
            recordPanel.add(siteSets);

            final JLabel statusLabel = new JLabel("旅行状态");

            Vector status = new Vector();
            status.addElement("旅行中");
            status.addElement("旅行结束");
            final JComboBox statusCombo = new JComboBox(status);
            recordPanel.add(statusLabel);
            recordPanel.add(statusCombo);

            JLabel pictureLabel = new JLabel("图片信息");
            JTextField picture = new JTextField();
            recordPanel.add(pictureLabel);
            recordPanel.add(picture);

            JLabel journalsLabel = new JLabel("日志信息");
            JTextField journals = new JTextField();
            recordPanel.add(journalsLabel);
            recordPanel.add(journals);


            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selRow = table.getSelectedRow();
                    organizer.setText(table.getValueAt(selRow,4).toString().trim());
                    startTime.setText(table.getValueAt(selRow,2).toString().trim());
                    endTime.setText(table.getValueAt(selRow,3).toString().trim());
                    groupNum.setText(table.getValueAt(selRow,7).toString().trim());
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

        void setTable(){
//            DefaultTableModel model = new ViewTable().makeTable("select * from record");
//            table.setModel(model);
        }
        //  ImageIcon siteModiandDelIcon = CreatecdIcon
    }

