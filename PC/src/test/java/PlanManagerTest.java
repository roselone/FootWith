import edu.thu.cslab.footwith.server.TextFormatException;
import edu.thu.cslab.footwith.server.User;
import org.json.JSONException;
import org.junit.Test;
import edu.thu.cslab.footwith.server.Plan;
import edu.thu.cslab.footwith.server.PlanManager;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlanManagerTest {
    @Test
    public void addPlanTest() throws TextFormatException, SQLException, JSONException {
        Plan plan=new Plan("I want to blablabla","[213,231]",java.sql.Date.valueOf("1996-05-03"), java.sql.Date.valueOf("1997-05-04"),1,2,3);
        //plan.setParticipants("[321,324]");
        PlanManager pm=new PlanManager();
        pm.addPlan(plan);
    }
}
