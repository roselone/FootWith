import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.plan_record.Record;
import edu.thu.cslab.footwith.plan_record.RecordManager;
import org.json.JSONException;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/10/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class recordTest {
    @Test
    public void selectRecordTest() throws TextFormatException, SQLException, JSONException {
        Record record=RecordManager.selectRecord(13);
        String journals= JSONHelper.getJSONHelperInstance().addToArray(record.getJournals(),13);
        System.out.println(journals);
        String SQLCommand = "update record set journals= '" + journals + "' where recordID=13;";
        //DBUtil.getDBUtil().executeUpdate(SQLCommand);
    }
}
