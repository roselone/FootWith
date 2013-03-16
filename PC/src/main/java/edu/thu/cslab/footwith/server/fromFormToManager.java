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
public class fromFormToManager {
    public void addPlanFromForm(String siteName, String startTime, String endTime, String organizer) throws TextFormatException, SQLException, JSONException {
        UserManager um = new UserManager();
        SiteManager sm = new SiteManager();
        PlanManager pm = new PlanManager();
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
}
