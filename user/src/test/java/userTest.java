import edu.thu.cslab.footwith.user.UserManager;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/9/13
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class userTest {
    @Test
    public void getUserNameTest() throws SQLException {
        System.out.println(UserManager.getUserName(4));
    }

}
