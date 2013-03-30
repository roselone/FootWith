package edu.thu.cslab.footwith.server;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-16
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class Mediator {

      public  Mediator(){

      }
    public void addPlanFromForm(String title,int organizer, int groupNumMax, String siteName1,String siteName2, String startTime, String endTime) throws TextFormatException, SQLException, JSONException {
        UserManager um = new UserManager();
        SiteManager sm = new SiteManager();
        PlanManager pm = new PlanManager();
        //System.out.println(siteName);
        Site site1 = sm.seleteSite(siteName1);
        Vector<Integer> vector =  new Vector<Integer>();
        vector.add(site1.getSiteID());
        String siteIDs = new JSONHelper().convertToString(vector);
        User user=um.selectUser(organizer);
        Date date_startTime = Date.valueOf(startTime);
        Date date_endTime = Date.valueOf(endTime);
        int int_organizer = user.getUserID();
        Plan plan = new Plan(title,siteIDs, date_startTime,date_endTime,int_organizer,0,0 );
        pm.addPlan(plan);
    }
    public void addSiteFromForm(String siteName, String rate, String location) throws SQLException {
        SiteManager sm = new SiteManager();
        int int_rate = Integer.parseInt(rate);
        Site site = new Site(siteName, location ,int_rate);
        sm.addSite(site);
    }
    public static boolean isValid(String username, String passwd) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User user=UserManager.selectUser(username);
        if (user==null) return false;
        System.out.println(user.getUserID());
        if (user.checkPasswd(passwd))
            return true;
        else
            return false;
    }
    public static Vector<String> getAllLocations(){
        Vector<String> allLocations = new Vector<String>();
        allLocations.add("北京");
        allLocations.add("浙江");
        allLocations.add("天津");
        allLocations.add("安徽");
        allLocations.add("上海");
        allLocations.add("福建");
        allLocations.add("重庆");
        allLocations.add("江西");
        allLocations.add("香港");
        allLocations.add("山东");
        allLocations.add("澳门");
        allLocations.add("河南");
        allLocations.add("内蒙古");
        allLocations.add("湖北");
        allLocations.add("新疆");
        allLocations.add("湖南");
        allLocations.add("宁夏");
        allLocations.add("广东");
        allLocations.add("西藏");
        allLocations.add("海南");
        allLocations.add("广西");
        allLocations.add("四川");
        allLocations.add("河北");
        allLocations.add("贵州");
        allLocations.add("山西");
        allLocations.add("云南");
        allLocations.add("辽宁");
        allLocations.add("陕西");
        allLocations.add("吉林");
        allLocations.add("甘肃");
        allLocations.add("黑龙江");
        allLocations.add("青海");
        allLocations.add("江苏");
        allLocations.add("台湾");
        return allLocations;
    }
    public  Vector<String> selectSiteNameWithLocation(String location) throws TextFormatException, SQLException {
        Site site = new Site();
        SiteManager sm = new SiteManager();
        site.setLocation(location);
        Vector<String> siteNames = new Vector<String>();
        Vector<Site> sites = sm.selectSite(site);
        for(int i=0;i<sites.size();i++){
            siteNames.add(sites.get(i).getSiteName());
        }
        return siteNames;
    }
}
