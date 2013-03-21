import edu.thu.cslab.footwith.server.DBUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/15/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBUtilTest {
    @Test
    public void testDB() throws SQLException, UnsupportedEncodingException {
        DBUtil DB=DBUtil.getDBUtil();
        String siteName="fuck小玉1";
        String location="玉儿";

        String SQLCMD1="insert into site ( siteName, rate, location ) values ( '"+siteName+"',5,'"+location+"' )";
        DB.executeUpdate(SQLCMD1);
        location = new String(location.getBytes(),"UTF-8");
        String SQLCOM2="select * from site where location = '"+location+"' ";
        ResultSet rs=DB.executeQuery(SQLCOM2);
        while(rs.next()){
            System.out.println(rs.getString("siteName"));
        }
        return;
    }
}
