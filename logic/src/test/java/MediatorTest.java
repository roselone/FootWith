import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.mediator.Mediator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/29/13
 * Time: 1:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class MediatorTest {
    Logger logger= LogManager.getLogger(this.getClass().getName());
    @Test
    public void selectUserTest() throws TextFormatException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException {
        String userinfo= Mediator.selectUser("121@test.com");
        logger.debug(userinfo);
    }
}
