package edu.thu.cslab.footwith.form;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-15
 * Time: 下午11:31
 * To change this template use File | Settings | File Templates.
 */
public class SitePanel extends Panel {

    private  ViewTable  viewTable = new ViewTable();
    private  JTable table = new JTable();
    private DefaultTableModel  dt;

    public  SitePanel(String sql){

        this.setLayout(new BorderLayout());
        JLabel titleLb = new JLabel("风景表");
        this.add(titleLb,"North");
        dt =  viewTable.makeTable(sql);
        table = new JTable(dt);
        table.setPreferredScrollableViewportSize(new Dimension(250,50));
        //table.set
        JScrollPane jspCenter = new JScrollPane(table);
        table.setAutoResizeMode(4);
        this.add(jspCenter, BorderLayout.CENTER);
        this.setVisible(true);

    }
    public SitePanel() {

        this.setLayout(new BorderLayout());
        JLabel titleLb = new JLabel("风景表");
        this.add(titleLb,"North");
        dt =  viewTable.makeTable("select * from site");
        table = new JTable(dt);
        table.setPreferredScrollableViewportSize(new Dimension(250,50));
        //table.set
        JScrollPane jspCenter = new JScrollPane(table);
        table.setAutoResizeMode(4);
        this.add(jspCenter, BorderLayout.CENTER);
        this.setVisible(true);


//为表格添加监听器
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //实现双击
                    SiteManage siteManage = new SiteManage();
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());  //获得行位置
                    int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
                    Vector<String> rowVector = new Vector<String>();
                    for(int i=0;i<4;i++){
                      rowVector.add( (dt.getValueAt(row, i)).toString());
                   }
                    siteManage.setValue(rowVector);
                    siteManage.setVisible(true);
                } else {
                    return;
                }
            }
        });

    this.setVisible(true);

}
}

class SelectionListener implements ListSelectionListener {
    private  JTable table;

   public SelectionListener(JTable table) {
        this.table = table;
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == table.getColumnModel().getSelectionModel()
                && table.getColumnSelectionAllowed() ){
            int firstRow = e.getFirstIndex();
            int lastRow = e.getLastIndex();
            if(lastRow == 2) {
                System.out.print(lastRow);
                System.exit(0);
            }
            // 事件处理...
        }
    }
}
