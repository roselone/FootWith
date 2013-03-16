package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
* Created with IntelliJ IDEA.
* User: wangjiayu
* Date: 13-3-16
* Time: 上午12:50
* To change this template use File | Settings | File Templates.
*/
public class PlanPanel extends JPanel {
    myDefaultTableModel dt=new myDefaultTableModel();//先定义一个Model
    DBUtil du = DBUtil.getDBUtil();
    String SQLCommand = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    JTable table = null;
    public  PlanPanel() {

        this.setLayout(new BorderLayout());
        setTable();

        table = new JTable(dt);
        table.setPreferredScrollableViewportSize(new Dimension(250,50));
        //table.set
        JScrollPane jspCenter = new JScrollPane(table);
        table.setAutoResizeMode(4);
        this.add(jspCenter, BorderLayout.CENTER);
        this.setVisible(true);

    }
    void setTable(){
        try{
            rs=du.executeQuery("select * from site");//里面写要显示的SQL语句
            rsmd = rs.getMetaData();
            int numberOfColumns=rsmd.getColumnCount();
            //System.out.print("test wjy numberOfColumns");
           // System.out.print(numberOfColumns);
//以下是显示数据库中的列名的代码
           //
            int b=1;
            while(b<=numberOfColumns) {
                dt.addColumn(rsmd.getColumnName(b));
                System.out.print(rsmd.getColumnName(b));
                b++;
            }
//以下是在表格中显示数据中的内容
            while(rs.next()) {
                Vector newRow=new Vector();
                System.out.println(newRow.toString());
                int c=1;
                while(c<=numberOfColumns) {
                    System.out.print(rs.getString(c));
                    newRow.addElement(rs.getString(c));
                    c++;
                }
                dt.addRow(newRow);
                System.out.print(newRow.toString());
            }
        }catch(Exception ex){

        }

    }


}
class myDefaultTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
