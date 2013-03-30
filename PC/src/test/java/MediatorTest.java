import edu.thu.cslab.footwith.server.TextFormatException;
import org.junit.Test;
import edu.thu.cslab.footwith.server.Mediator;

import javax.print.attribute.standard.Media;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/20/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MediatorTest {
    @Test
    public void selectSiteNameWithLocationTest() throws TextFormatException, SQLException, UnsupportedEncodingException {
        String location="宁夏";
        System.out.println(new Mediator().selectSiteNameWithLocation(location).toString());
        return ;
    }
}
