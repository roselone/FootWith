package edu.thu.cslab.footwith.client.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/26/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constant {
    public static String URL="http://166.111.70.115:12580/web-1.0/";
    private Properties properties = new Properties();
         private static final Constant constantInstantce=new Constant();
         private Constant(){
             try {
                 properties.load(new FileInputStream("/client.properties"));
             } catch (IOException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File     Templates.
             }
         }

         /**
          * get key-value
          * @param key
          * @return value
          */
         public String getProperty(String key){
             return this.properties.getProperty(key,"NULL");
         }

         /**
          *
          * @return constant instance
          */
         public static Constant getInstantce(){
             return constantInstantce;
         }

}
