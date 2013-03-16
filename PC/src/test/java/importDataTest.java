import edu.thu.cslab.footwith.server.DBUtil;
import org.junit.Test;
import edu.thu.cslab.footwith.server.importData;
import edu.thu.cslab.footwith.server.constant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/15/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class importDataTest {
//    @Test
//    public void import4A() throws IOException, SQLException {
//        String fileName="4A.txt";
//        importData im=new importData();
//        im.importSite(new constant().PROJECT_PATH + "data/" + fileName);
//    }
//    @Test
//    public void import5A()throws IOException, SQLException {
//        String fileName="5A.txt";
//        importData im=new importData();
//        im.importSite2(new constant().PROJECT_PATH + "data/" + fileName);
//    }
    @Test
    public void readSite() throws SQLException {
        String SQLselect="Select * from site;";
        ResultSet rs = DBUtil.getDBUtil().executeQuery(SQLselect);
        while (rs.next()){
            System.out.println(rs.getString("siteName"));
        }
    }

}
