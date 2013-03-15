package edu.thu.cslab.footwith.form;
import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-13
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class Memu {
        private  JMenu userInfo = new JMenu("user info");
        private  JMenu travelInfo = new JMenu("travel info");
        private MenuShortcut viewShortcut = new MenuShortcut('v');
        private  JMenuItem viewItem = new JMenuItem("view");

        private  MenuItemListener itemListener = new MenuItemListener();
        private  JMenuItem sceneView = new JMenuItem("view");

    /**
     * Created with IntelliJ IDEA.
     * User: wangjiayu
     * Date: 13-3-13
     * Time: 上午11:14
     * To change this template use File | Settings | File Templates.
     */
    public static class MenuItemListener {
    }
}
