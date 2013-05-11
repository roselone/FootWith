import edu.thu.cslab.footwith.exception.TextFormatException;
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
    public void getSiteNameTest() throws SQLException, TextFormatException {
        int id=2;
        System.out.println(SiteManager.seleteSite(2).getLocation());
    }
    @Test
    public void getSiteNameWithLocationTest() throws SQLException {
        String location="北京";
        System.out.println(SiteManager.selectSiteWithLocation(location).toString());
    }
    @Test
    public void getSiteTest() throws SQLException {
        int id=2;
        System.out.println(SiteManager.getSite(id).toString());
    }
}
