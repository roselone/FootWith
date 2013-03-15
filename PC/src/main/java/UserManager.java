import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class UserManager {
    public UserManager() { }
    public void addUser(User user) throws Exception {
        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        SQLCommand = " insert into " + tableName + " ( userID, nickName, passwd, otherInfo,  plans, records ) " +
                " values ( "+ user.getUserID()+" , "+ user.getNickName()+ " , "+ user.getPasswd()+ " , " + user.getOtherInfo()+ " , " + user.getPlans()+ " , " + user.getRecords() + " ) ";
        du.executeUpdate(SQLCommand);
    }

    public User selectUser(String userName) throws Exception{
        User user=new User();
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(userName == null)
            throw new Exception("userName is null");
        SQLCommand  = " select * from " + tableName + "where userName like %" + userName + "%";
        rs=du.executeQuery(SQLCommand);

        return user;
    }
    private final String tableName ="User";


}
