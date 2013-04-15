import edu.thu.cslab.footwith.server.Plan;
import edu.thu.cslab.footwith.server.PlanManager;
import edu.thu.cslab.footwith.server.RecordManager;
import edu.thu.cslab.footwith.server.TextFormatException;
import org.json.JSONException;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/5/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecordManagerTest {
    @Test
    public void convertRecordTest() throws TextFormatException, SQLException, JSONException {
        Plan plan=new PlanManager().selectPlan(1);
        new RecordManager().addRecordFromPlan(plan);
    }
}
