package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class ViewTable {

 //   private  ViewTable viewTable = new ViewTable();
    myDefaultTableModel dt=new myDefaultTableModel();//先定义一个Model
    DBUtil du = DBUtil.getDBUtil();
    String SQLCommand = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    JTable table = null;
    public ViewTable(){

    }

    public myDefaultTableModel makeTable(String sql){


            setTable(sql);



        return dt;
    }

   private void setTable(String sql){
        try{

                rs = du.executeQuery(sql);//里面写要显示的SQL语句
                 System.out.println(sql);
         //   rs = du.executeQuery("select * from site where siteName ='wjy'");
            rsmd = rs.getMetaData();
            int numberOfColumns=rsmd.getColumnCount();
            //System.out.print("test wjy numberOfColumns");
            // System.out.print(numberOfColumns);
            //以下是显示数据库中的列名的代码
            //
            int b=1;
            while(b<=numberOfColumns) {
                dt.addColumn(rsmd.getColumnName(b));
              //  dt.getTitles().add(rsmd.getColumnName(b));

                //   System.out.print(rsmd.getColumnName(b));
                b++;
            }
//以下是在表格中显示数据中的内容
            while(rs.next()) {
                Vector newRow=new Vector();
                // System.out.println(newRow.toString());
                int c=1;
                while(c<=numberOfColumns) {
                    // System.out.print(rs.getString(c));
                    newRow.addElement(rs.getString(c));

                    c++;
                }
                dt.addRow(newRow);
               // dt.getData().add(newRow);
                // System.out.print(newRow.toString());
            }
        }catch(Exception ex){
              ex.printStackTrace();
        }

    }
}
class myDefaultTableModel extends DefaultTableModel {

    public boolean isCellEditable(int row, int column) {
        return false;
    }


}