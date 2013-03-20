package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.TextFormatException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-19
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class SiteSearch  extends JFrame {
  // public static String cityInfo = "111" ;
    JList cityList = null;
    JScrollPane cityShowPane = null;
    JPanel titlePane = new JPanel();
    JTextField choice = new JTextField();
    JPanel choicePane = new JPanel();
    JComboBox cityCombBox = new JComboBox();
    Mediator dataSource = new Mediator();
    String cityChooser = "";
    ListModel modeList;
    Vector<String> city;
    public JTextField getChoice() {
        return choice;
    }

    public void setChoice(JTextField choice) {
        this.choice = choice;
    }

    public  SiteSearch()  {
       // cityInfo = "";
        this.setBounds(200,200,200,200);
        try{
            init();
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.setVisible(true);
    }

    private void init() throws TextFormatException, SQLException {

//        final Vector city = new Vector();
//        city.addElement("河南");                         //前段应该有一个得到所有数据的类和功能含糊 getAllData & function
//        city.addElement("山东");
//        city.addElement("北京");
//        city.addElement("河北");
//        city.addElement("黑龙江");
//        city.addElement("吉林");
//        city.addElement("沈阳");
         city = dataSource.getAllLocations();

       /// city.add()
        //
       // city = getCity();
        // siteList = getSiteofCity(cityInfo);
        // sketch layout

        this.setLayout(new BorderLayout());

    //    JLabel titleLabel = new JLabel("景点查询");
         UserComboBoxModel comboBoxCreator = new UserComboBoxModel(city);

          cityCombBox = new JComboBox(comboBoxCreator);
           cityCombBox.setBorder(BorderFactory.createTitledBorder("想去的省市"));
       //    this.add(titleLabel,BorderLayout.NORTH);
         this.add(cityCombBox,BorderLayout.NORTH);
          cityCombBox.addItemListener(new ItemListener() {
              @Override
              public void itemStateChanged(ItemEvent e) {
                  if(e.getStateChange() == ItemEvent.SELECTED){
                    //  choice.setText(choice.getText()+ e.getItem().toString());
                  //    Global.cityInfo = e.getItem().toString();
                      cityChooser = e.getItem().toString();
                   //   System.out.println(cityInfo);
                  }
              }
          });

         Vector<String> siteListData = dataSource.selectSiteNameWithLocation(cityChooser);
    //    final ListModel  modeList = new DataMode(city);          // it will be better when data is show by city
        modeList = new DataMode(siteListData);
        cityList = new JList(modeList);
        JScrollPane cityShowPane = new JScrollPane(cityList);
        this.add(cityShowPane, BorderLayout.CENTER);
        cityList.setBorder(BorderFactory.createTitledBorder("城市"));

        this.add(choicePane,BorderLayout.SOUTH);
        choicePane.setLayout(new GridLayout(2,2));
        choicePane.add(new JLabel("你的选择是:"));
        choicePane.add(choice);
        final JButton btnSubmit = new JButton("确定");
        final JButton btnCancel = new JButton("取消");
        choicePane.add(btnSubmit);
        choicePane.add(btnCancel);

        // event   handing
     //  myListSelectionListener   myListSelectionList = new myListSelectionListener();

      //  ButtonLister siteSearch_btnSubmit = new ButtonLister();
        cityList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int tmp = 0;
                String choiceStr = new String();
                int[] index = cityList.getSelectedIndices();
                if(index.length == 0)  {
                    return;
                }
                for(int i = 0; i< index.length;i++){
                       tmp = index[i];
                      if(i != index.length -1) {
                          choiceStr = choiceStr + city.get(tmp) + "," ;
                      }else{
                          choiceStr = choiceStr + city.get(tmp);
            }
                    Global.cityInfo = choiceStr;
        }

             //   setChoice(new JTextField(choice));

                       choice.setText(choiceStr);

        }
        });
        cityList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index;
                if(e.getSource()==cityList){
                      if(e.getClickCount() == 2){
                          index = cityList.locationToIndex(e.getPoint());
                          String tmp =(String)modeList.getElementAt(index);
                          new SearchPlanForm();
                      }
                }
            }
        });
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                      System.exit(0);

               //
                try{
                //  SearchPlanForm.setSiteName(choice.getText());
                    SearchPlanForm.ChoiceName = choice.getText();
                }catch (Exception e1){

                }
                setVisible(false);


            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                 choice.setText("");
            }
        });


    }



}
 class UserComboBoxModel extends  AbstractListModel implements ComboBoxModel {
     private  Vector vector = new Vector();
     private  String item = null;
     public UserComboBoxModel(Vector city) {

               this.vector = city;
    }

     @Override
     public void setSelectedItem(Object anItem) {

         item = anItem.toString();

     }

     @Override
     public Object getSelectedItem() {
         return item;  //To change body of implemented methods use File | Settings | File Templates.
     }

     @Override
     public int getSize() {
         return vector.size();  //To change body of implemented methods use File | Settings | File Templates.
     }

     @Override
     public Object getElementAt(int index) {
         return vector.get(index);  //To change body of implemented methods use File | Settings | File Templates.
     }
 }
class DataMode extends AbstractListModel {
     private  Vector dataV = null;

    DataMode(Vector dataV) {
        this.dataV = dataV;
    }

    @Override
    public int getSize() {
        return dataV.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getElementAt(int index) {
        return dataV.get(index);  //To change body of implemented methods use File | Settings | File Templates.
    }
}

// class myListSelectionListener implements ListSelectionListener{
//
//     @Override
//     public void valueChanged(ListSelectionEvent e) {
//             int tmp = 0;
//             int[] index =
//     }
// }