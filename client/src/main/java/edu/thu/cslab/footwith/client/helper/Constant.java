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

    public static String URL="http://footwith.tk:12580/web-1.0/";
    public static final String WeiboAppKey = "3761414352";
    public static final String WeiboAppSecret = "31ef7ea1da6f1f06c455e0273a27f344";
    public static final String WeiboRedirectURL = "https://api.weibo.com/oauth2/default.html";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write," +
            "friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
            "follow_app_official_microblog";

    public static final String CLIENT_ID = "client_id";

    public static final String RESPONSE_TYPE = "response_type";

    public static final String USER_REDIRECT_URL = "redirect_uri";

    public static final String DISPLAY = "display";

    public static final String USER_SCOPE = "scope";

    public static final String PACKAGE_NAME = "packagename";

    public static final String KEY_HASH = "key_hash";

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
