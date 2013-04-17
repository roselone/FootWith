import edu.thu.cslab.footwith.server.*;
import org.json.JSONException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 4/15/13
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BXLTest {
    @Test
    public void BXLTest() throws NoSuchAlgorithmException, UnsupportedEncodingException, TextFormatException, SQLException, JSONException {
        UserManager um = new UserManager();
        PlanManager pm = new PlanManager();
        SiteManager sm = new SiteManager();

        //Test Sequence: add select edit delete
        //Test User

        User user;
        try{
            user = UserManager.selectUser("bxl");
        }catch (SQLException e){
            user= new User("bxl", "bxl", "helloworld", 1, "", "");
            UserManager.addUser(user);
        }


        //Test Site
        Site site;

        try{
            site = sm.seleteSite("tsinghua");
        }catch (SQLException e){
            site = new Site(0, "tsinghua", 4, "beijing");
             sm.addSite(site);
        }
        int userID = user.getUserID();

        int siteID = site.getSiteID();

        //Test Plan
        Vector<Integer> siteIDs_v = new Vector<Integer>();
        String siteIDs_s;
        siteIDs_v.add(siteID);


//        Plan plan = new Plan("my1", JSONHelper.getJSONHelperInstance().convertToString(siteIDs_v), Date.valueOf("2000-01-01"), Date.valueOf("2000-02-03"), userID, 10, 10);
//        pm.addPlan(plan);
//        Vector<Plan> plans = pm.selectPlan(plan);
//        plan.setBudget(10);
//        pm.editPlan(plans.get(0).getPlanID(), plan);
//        pm.deletePlan(plans.get(0).getPlanID());


    }
}
