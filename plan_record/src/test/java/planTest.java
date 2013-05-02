import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.plan_record.Plan;
import edu.thu.cslab.footwith.plan_record.PlanManager;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/2/13
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class planTest {
    @Test
    public void selectPlanTest() throws TextFormatException, SQLException {
        Plan plan= PlanManager.selectPlan(15);
        System.out.println(plan.getTimestamp());
    }
}
