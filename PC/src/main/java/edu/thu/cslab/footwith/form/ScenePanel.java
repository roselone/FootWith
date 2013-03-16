package edu.thu.cslab.footwith.form;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
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
public class ScenePanel extends Panel {

    MyTableModel tbModel=new MyTableModel();
    Object[][] tableContent=
            {
                    {"wang",100,20,3,true},
                    {"li",29,33,true},
                    {"zhang",222,33,false},
            };
    String[] Names = {"name","location","level","introduction"};

    JTextField txtRow=new JTextField(10);

    JTextField txtCol=new JTextField(10);

    JTextField txtContent=new JTextField(10);

    JPanel pnlSouth=new JPanel(new GridLayout(1,6));

    public ScenePanel() {
//       JTable sceneTable = new JTable(playerInfo,Names);
//       sceneTable.setPreferredScrollableViewportSize(new Dimension(550,300));
//       JScrollPane scrollPane = new JScrollPane(sceneTable);
//       this.add(sceneTable);
//       this.setVisible(true);
//       SelectionListener listener = new SelectionListener(sceneTable);
//       sceneTable.getSelectionModel().addListSelectionListener(listener);
        this.setLayout(new BorderLayout());
        tbModel.data=new Vector(1,1);
        for(int i=0;i<3;i++)
            for(int j=0;j<4;j++)
            {
                tbModel.data.add(tableContent[i][j]);
            }

        tbModel.titles=new Vector(1,1);

        for(int i=0;i<4;i++)
        {
            tbModel.titles.add(Names[i]);
        } //使用表模型对象生成表

        JTable myTable=new JTable(tbModel);

//窗体组件布局

        myTable.setAutoResizeMode(4);
        JScrollPane jspCenter=new JScrollPane(myTable);
        this.add(jspCenter, BorderLayout.CENTER);
        pnlSouth.add(new JLabel("Row:"),BorderLayout.SOUTH);
        pnlSouth.add(txtRow,BorderLayout.SOUTH);
        pnlSouth.add(new JLabel("Col:"),BorderLayout.SOUTH);
        pnlSouth.add(txtCol,BorderLayout.SOUTH);
        pnlSouth.add(new JLabel("Content:"),BorderLayout.SOUTH);
        pnlSouth.add(txtContent,BorderLayout.SOUTH);
        this.add(pnlSouth, BorderLayout.SOUTH);

//        SelectionListener listener = new SelectionListener(myTable);
//        myTable.getSelectionModel().addListSelectionListener(listener);


//为表格添加监听器
        myTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //实现双击
                    SceneManage sceneManage = new SceneManage();

                    System.out.println("ddddddddddddddd");
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());  //获得行位置
                    int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); //获得列位置
                    String cellVal = (tbModel.getValueAt(row, col)).toString(); //获得点击单元格数据
                    txtRow.setText((row+1)+"");                // jdk 1.6  integer convert to string using the function toString() instead of strong convertion
                    txtCol.setText((col+1)+"");
                    txtContent.setText(cellVal);
                    Vector<String> rowVector = new Vector<String>();
                    for(int i=0;i<4;i++){
                        rowVector.add( (tbModel.getValueAt(row, i)).toString());
                    }
                    sceneManage.setValue(rowVector);
                    sceneManage.setVisible(true);
                     // comment show the scene info        response the first cell
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
class MyTableModel extends AbstractTableModel {

    public Vector data;

    public Vector titles;

    public int getRowCount() {

        return data.size()/getColumnCount();

    }

    public int getColumnCount() {

        return titles.size();

    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        return data.get((rowIndex*getColumnCount())+columnIndex);

    }
}

