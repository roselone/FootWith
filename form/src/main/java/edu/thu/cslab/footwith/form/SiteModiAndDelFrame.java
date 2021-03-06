package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class SiteModiAndDelFrame extends JFrame {

    public  JTable table ;

    private String[] columnNames = new String[]{"名称","等级","位置","介绍","图片"};
    public  SiteModiAndDelFrame(){
        super();

        final BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
       // setIconifiable(true);
       // setClosable(true);
        setTitle("景点删除和修改");
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
        DefaultTableModel model = makeTable();
        table = new JTable(model);

        scrollPane.setViewportView(table);


        final JPanel sitePanel = new JPanel();
        mainPanel.add(sitePanel,"South");
        final  GridLayout gridLayout = new GridLayout(0,4);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        sitePanel.setLayout(gridLayout);

        final JLabel siteNameLabel = new JLabel();
        siteNameLabel.setText("景点名称");
        sitePanel.add(siteNameLabel);

        final JTextField siteName = new JTextField();
        sitePanel.add(siteName);
    //    siteName.setEditable(false);

        final JLabel siteRateLabel = new JLabel();
        siteRateLabel.setText("景点等级");
        sitePanel.add(siteRateLabel);

        final JTextField siteRate = new JTextField();
        sitePanel.add(siteRate);

        final JLabel siteLocationLabel = new JLabel();
        siteLocationLabel.setText("景点位置");
        sitePanel.add(siteLocationLabel);
        final JTextField siteLocation = new JTextField();
        sitePanel.add(siteLocation);

        final JLabel siteBriefLabel = new JLabel("介绍");
        sitePanel.add(siteBriefLabel);
        final JTextField siteBrief = new JTextField();
        sitePanel.add(siteBrief);

        final JLabel sitePictureLabel = new JLabel("景点图片");
        sitePanel.add(sitePictureLabel);
      //  final Image siteImage = new Im
        final  JTextField sitePicture = new JTextField();
        sitePanel.add(sitePicture);

        final JLabel siteIsGoLabel = new JLabel("是否去过");
        sitePanel.add(siteIsGoLabel);
        final JTextField siteIsGo  = new JTextField();
        sitePanel.setEnabled(false);
        sitePanel.add(siteIsGo);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = table.getSelectedRow();
                siteName.setText(table.getValueAt(selRow,0).toString().trim());
                siteRate.setText(table.getValueAt(selRow,1).toString().trim());
                siteLocation.setText(table.getValueAt(selRow,2).toString().trim());
                siteBrief.setText(table.getValueAt(selRow,3).toString().trim());
                sitePicture.setText(table.getValueAt(selRow,4).toString().trim());

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
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(siteName.getText().length() == 0){
                        JOptionPane.showMessageDialog(null,"请先选中删除的景点!");
                    } else{
                        try {
                            Mediator.deleteSite(siteName.getText().toString().trim());
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null,"删除错误!");
                            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        DefaultTableModel model = makeTable();
                        table.setModel(model);
                    }
            }
        });
        final  JButton updateButton = new JButton();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int i =  dataOPeraion
              //  updateSite(siteName,siteRate,siteLocation,siteBrief,sitePicture)
                HashMap<String,String> siteMap = new HashMap<String, String>();
                siteMap.put("siteName",siteName.getText().toString().trim());
                siteMap.put("rate",siteRate.getText().toString().trim());
                siteMap.put("location",siteLocation.getText().toString().trim());
                siteMap.put("brief",siteBrief.getText().toString().trim());
                siteMap.put("picture",siteBrief.getText().toString().trim());
                JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
                String newSite = jsonHelper.convertToString(siteMap);



                try {
                     Mediator.editSite(siteName.getText().toString().trim(), newSite);
                    JOptionPane.showMessageDialog(null,"更新成功");
                } catch (Exception e1) {
                  JOptionPane.showMessageDialog(null,"错误！");
                }

                int flag = 0;
                if(flag == 1) {

                    JOptionPane.showMessageDialog(null,"修改成功");

//                    DefaultTableModel model = new DefaultTableModel(columnNames,4);
//
//                    for(int i=0;i < sitesVector.size();i++)
//                    {
//                       Vector tmpRow = new Vector();
//                       for(int j=1; j<=5;j++){
//                           tmpRow.add(sitesVector.get(j).getSiteName());
//                       }
//                       model.addRow(tmpRow);
//                    }
                    DefaultTableModel model = makeTable();
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
       // closeButton.addActionListener(new CloseActionLister());

        this.setVisible(true);
        final JLabel headLogo = new JLabel();


        }
      //  ImageIcon siteModiandDelIcon = CreatecdIcon
     private DefaultTableModel makeTable(){
         DefaultTableModel model = new DefaultTableModel(columnNames,0);
         Vector<String>   sitesVector=null;
         JSONHelper jsonHelper = JSONHelper.getJSONHelperInstance();
         try {
             sitesVector = Mediator.getAllSite();

         } catch (SQLException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }
         if(sitesVector!=null)
         {
         for(int i=0;i < sitesVector.size();i++)
         {
             Vector tmpRow = new Vector();
                 HashMap<String,String> siteMap = new HashMap<String, String>();
                 siteMap = jsonHelper.convertToMap(sitesVector.get(i));
                 tmpRow.add(siteMap.get("siteName"));
                 tmpRow.add(siteMap.get("rate"));
                 tmpRow.add(siteMap.get("location"));
                 tmpRow.add(siteMap.get("brief"));
                 tmpRow.add(siteMap.get("picture"));

             model.addRow(tmpRow);
         }
         }
         return model;
     }
    }



