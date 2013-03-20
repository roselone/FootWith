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
    public void addPlanFromForm(String siteName, String startTime, String endTime, String organizer) throws TextFormatException, SQLException, JSONException {
        UserManager um = new UserManager();
        SiteManager sm = new SiteManager();
        PlanManager pm = new PlanManager();
        System.out.println(siteName);
        Site site = sm.seleteSite(siteName);
        Vector<Integer> vector =  new Vector<Integer>();
        vector.add(site.getSiteID());
        String siteIDs = new JSONHelper().convertToString(vector);
        User user=um.selectUser(organizer);
        Date date_startTime = Date.valueOf(startTime);
        Date date_endTime = Date.valueOf(endTime);
        int int_organizer = user.getUserID();
        Plan plan = new Plan(siteIDs, date_startTime,date_endTime,int_organizer,0,0 );
        pm.addPlan(plan);
    }
    public void addSiteFromForm(String siteName, String rate, String location) throws SQLException {
        SiteManager sm = new SiteManager();
        int int_rate = Integer.parseInt(rate);
        Site site = new Site(siteName, location ,int_rate);
        sm.addSite(site);
    }
    public static boolean idValid(String username,String passwd) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User user=UserManager.selectUser(username);
        if (user==null) return false;
        System.out.println(user.getUserID());
        if (user.checkPasswd(passwd))
            return true;
        else
            return false;
    }
    public Vector<String> getAllLocations(){
        Vector<String> allLocations = new Vector<String>();
        allLocations.add("北京市");
        allLocations.add("浙江省");
        allLocations.add("天津市");
        allLocations.add("安徽省");
        allLocations.add("上海市");
        allLocations.add("福建省");
        allLocations.add("重庆市");
        allLocations.add("江西省");
        allLocations.add("香港特别行政区");
        allLocations.add("山东省");
        allLocations.add("澳门特别行政区");
        allLocations.add("河南省");
        allLocations.add("内蒙古自治区");
        allLocations.add("湖北省");
        allLocations.add("新疆维吾尔自治区");
        allLocations.add("湖南省");
        allLocations.add("宁夏回族自治区");
        allLocations.add("广东省");
        allLocations.add("西藏自治区");
        allLocations.add("海南省");
        allLocations.add("广西壮族自治区");
        allLocations.add("四川省");
        allLocations.add("河北省");
        allLocations.add("贵州省");
        allLocations.add("山西省");
        allLocations.add("云南省");
        allLocations.add("辽宁省");
        allLocations.add("陕西省");
        allLocations.add("吉林省");
        allLocations.add("甘肃省");
        allLocations.add("黑龙江省");
        allLocations.add("青海省");
        allLocations.add("江苏省");
        allLocations.add("台湾省");
        return allLocations;
    }
    public  Vector<String>selectSiteNameWithLocation(String location) throws TextFormatException, SQLException {
        Site site = new Site();
        SiteManager sm = new SiteManager();
        site.setLocation(location);
        Vector <String> siteNames = new Vector<String>();
        Vector<Site> sites = sm.selectSite(site);
        for(int i=0;i<sites.size();i++){
            siteNames.add(sites.get(i).getSiteName());
        }
        return siteNames;
    }
}
