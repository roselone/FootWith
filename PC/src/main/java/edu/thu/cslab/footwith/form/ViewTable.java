package edu.thu.cslab.footwith.form;

import javax.swing.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class ViewTable {
    public  static JTable makeTable(Vector obj,Vector title){
        JTable table = new JTable(obj,title);
        return table;
    }
}
