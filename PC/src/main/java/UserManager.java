import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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

    public User selectUser(String userID) throws Exception{
        User user=new User();
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        ResultSetMetaData rsmd;
        if(userID == null)
            throw new Exception("userName is null");
        SQLCommand  = " select * from " + tableName + "where userID is " + userID;
        rs=du.executeQuery(SQLCommand);
        while(rs.next()){
            rsmd = rs.getMetaData();
            for(int i=1;i<=rsmd.getColumnCount(); i++) {
                if(rsmd.getColumnName(i).equals("userID"))
                   user.setUserID(rs.getString(i));
                else if(rsmd.getColumnName(i).equals("nickName"))
                    user.setNickName(rs.getString(i));
                else if(rsmd.getColumnName(i).equals("passwd"))
                    user.setPasswd(rs.getString(i));
                else if(rsmd.getColumnName(i).equals("otherInfo"))
                    user.setOtherInfo(rs.getInt(i));
            }

        }

        return user;
    }
    private final String tableName ="User";


}
