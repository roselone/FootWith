import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/29/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class MediatorTest {
    Logger logger= LogManager.getLogger(this.getClass().getName());
//    @Test
//    public void selectUserTest() throws TextFormatException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException, JSONException {
//        String userinfo= Mediator.selectUser("hel");
//        logger.debug(userinfo);
//    }
//    @Test
//    public void getUserPlanTest() throws SQLException, JSONException, TextFormatException {
//        String planIDs="[15,16]";
//        String result= Mediator.getUserPlans(planIDs).toString();
//        logger.debug(result);
//    }
//    @Test
//    public void getNamesTest() throws JSONException, SQLException {
//        String IDs="[1,2,3,6]";
//        String result=Mediator.getUserNames(IDs);
//        logger.debug(result);
//    }
//    @Test
//    public void addJournalTest() throws SQLException, JSONException, TextFormatException {
//        int recordID=13;
//        HashMap<String,String> journalMap=new HashMap<String, String>();
//        journalMap.put("title","wuliaoderizhi");
//        journalMap.put("body","deadline is near, move");
//        journalMap.put("userID","16");
//        journalMap.put("time", "2013-05-10");
//        //Mediator.addJournal(recordID,journalMap);
//    }
//    @Test
//    public void editJournalTest() throws SQLException {
//        int journalID=3;
//        HashMap<String,String> journalMap = new HashMap<String, String>();
//        journalMap.put("title","sss");
//        journalMap.put("body","houhouhou");
//        Mediator.editJournal(journalID,journalMap);
//    }
//    @Test
//    public void getSiteInfoTest() throws IOException, SQLException {
//        int id=2;
//        HashMap<String,String> siteMap=Mediator.getSite(id);
//        System.out.println(siteMap.toString());
//        String result=JSONHelper.getJSONHelperInstance().convertToString(siteMap);
//        siteMap=JSONHelper.getJSONHelperInstance().convertToMap(result);
//        System.out.println(siteMap.get("picture"));
//    }
    @Test
    public void getSiteNameWithLocationTest() throws SQLException {
        System.out.println(Mediator.getSiteNameWithLocation("北京").toString());
    }
}
