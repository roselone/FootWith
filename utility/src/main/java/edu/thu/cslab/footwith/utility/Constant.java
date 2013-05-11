package edu.thu.cslab.footwith.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/5/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constant {
    private Properties properties = new Properties();
    private static final Constant constantInstantce=new Constant();
    public static final String IMAGE_PATH="/usr/share/tomcat7/webapps/web-1.0/WEB-INF/classes/images";
    private Constant(){
        try {
            properties.load(new FileInputStream("target/classes/footwith.properties"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
