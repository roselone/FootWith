import edu.thu.cslab.footwith.server.*;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {
    @Test
    public void addUserTest() throws NoSuchAlgorithmException, UnsupportedEncodingException, TextFormatException, SQLException {
        User user=new User("xiao","yuer","1234",-1,null,null,0);
        UserManager um=new UserManager();
        um.addUser(user);
    }
    @Test
    public void selectUserTest() throws TextFormatException, SQLException {
        UserManager um=new UserManager();
        User user=UserManager.selectUser("xiao");
        System.out.println(user.getNickName());
    }

    @Test
    public void validTest() throws TextFormatException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException {
        Boolean rs= Mediator.idValid("xiao", "1234");
        System.out.println(rs);
    }

}
