/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class UserManager {
    public UserManager() { }
    public void addUser(User user){
        String SQLCommand;
        DBUtil du = DBUtil.getDBUtil();

    }
    public User selectUser(String userName){
        User user=new User();

        return user;
    }
    private final String tableName ="User";


}
