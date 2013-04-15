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
        User user=new User("xiao3","yuer3","1234",-1,"","");
        UserManager um=new UserManager();
        um.addUser(user);
    }
    @Test
    public void selectUserTest() throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UserManager um=new UserManager();
        User user= UserManager.selectUser("xiao2");
        System.out.println(user.getNickName());
        System.out.println(user.getOtherInfo());
        System.out.println(user.getPasswd());
        System.out.println(user.getPlans());
        System.out.println(user.getRecords());
        System.out.println(user.getState());
        System.out.println(user.getUserID());
        System.out.println(user.getUserName());
    }

    @Test
    public void validTest() throws TextFormatException, NoSuchAlgorithmException, SQLException, UnsupportedEncodingException {
        Boolean rs= Mediator.isValid("xiao", "1234");
        System.out.println(rs);
    }

}
