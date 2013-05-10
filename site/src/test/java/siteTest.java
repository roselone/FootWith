import edu.thu.cslab.footwith.site.SiteManager;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/9/13
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class siteTest {
    @Test
    public void getSiteNameTest() throws SQLException {
        int id=12;
        System.out.println(SiteManager.getSiteName(id));
    }
}
