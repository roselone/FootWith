package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public class RecordPanel extends  JPanel{
    private ViewTable viewTable = new ViewTable();
    private JTable table = new JTable();
    public  RecordPanel() {

        this.setLayout(new BorderLayout());
        JLabel titleLb = new JLabel("行程表");
        this.add(titleLb,"North");
        table = new JTable(viewTable.makeTable("select * from record"));
        table.setPreferredScrollableViewportSize(new Dimension(250,50));
        //table.set
        JScrollPane jspCenter = new JScrollPane(table);
        table.setAutoResizeMode(4);
        this.add(jspCenter, BorderLayout.CENTER);
        this.setVisible(true);
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    //实现双击
//                    SiteManage siteManage = new SiteManage();
//                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());  //获得行位置
//                    int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
//                    Vector<String> rowVector = new Vector<String>();
//                    for(int i=0;i<4;i++){
//                        rowVector.add( (viewTable.makeTable("select * from record").getValueAt(row, i)).toString());
//                    }
//                    siteManage.setValue(rowVector);
//                    siteManage.setVisible(true);
//                } else {
//                    return;
//                }
//            }
//        });


    }
}
