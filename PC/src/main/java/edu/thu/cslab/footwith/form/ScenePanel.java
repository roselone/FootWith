package edu.thu.cslab.footwith.form;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-15
 * Time: 下午11:31
 * To change this template use File | Settings | File Templates.
 */
public class ScenePanel extends Panel {

   public ScenePanel() {

       Object[][] playerInfo=
               {
                       {"wang",100,20,3,true},
                       {"li",29,33,true},
                       {"zhang",222,33,false},
               };
       String[] Names = {"name","location","level","introduction"};
       JTable sceneTable = new JTable(playerInfo,Names);
       sceneTable.setPreferredScrollableViewportSize(new Dimension(550,300));
       JScrollPane scrollPane = new JScrollPane(sceneTable);
       this.add(sceneTable);
       this.setVisible(true);
       SelectionListener listener = new SelectionListener(sceneTable);
       sceneTable.getSelectionModel().addListSelectionListener(listener);


    }



}
 class SelectionListener implements ListSelectionListener {
    JTable table;

    SelectionListener(JTable table) {
        this.table = table;
    }
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == table.getColumnModel().getSelectionModel()
                && table.getColumnSelectionAllowed() ){
            int firstRow = e.getFirstIndex();
            int lastRow = e.getLastIndex();
            if(lastRow == 3) {
                System.exit(0);
            }
            // 事件处理...
        }
    }
}