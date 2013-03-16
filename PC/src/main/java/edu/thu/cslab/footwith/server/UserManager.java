package edu.thu.cslab.footwith.server;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class UserManager {
    public UserManager() { }
    public void addUser(User user) throws TextFormatException, SQLException {
        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        if(user == null)
            throw new TextFormatException();
        SQLCommand = " insert into " + tableName + " ( userName, nickName, passwd, otherInfo, plans, records ) " +
                " values ( '"+ user.getUserName()+"' , '"+ user.getNickName()+ "' , '"+ user.getPasswd()+ "' , " + user.getOtherInfo()+ " , '" + user.getPlans()+ "' , '" + user.getRecords() + "' ) ";
        du.executeUpdate(SQLCommand);
    }

    public User selectUser(String userName) throws TextFormatException, SQLException {
        User user;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(userName == null)
            throw new TextFormatException("userName is null");
        SQLCommand  = " select * from " + tableName + "where userName = '" + userName+"'";
        rs=du.executeQuery(SQLCommand);
        // while(rs.next()){
        rs.next();
        user = new User(rs.getInt("userID"));
        user.setUserName(rs.getString("userName"));
        user.setNickName(rs.getString("nickName"));
        user.setPasswd(rs.getString("passwd"));
        user.setOtherInfo(rs.getInt("otherInfo"));
        user.setPlans(rs.getString("plans"));
        user.setRecords(rs.getString("records"));
        // }

        return user;
    }
    public User selectUser(int userID) throws TextFormatException, SQLException {
        User user=new User(userID);
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " select * from " + tableName + "where userID = " + userID;
        rs=du.executeQuery(SQLCommand);
        //while(rs.next()){
        rs.next();
        user.setUserName(rs.getString("userName"));
        user.setNickName(rs.getString("nickName"));
        user.setPasswd(rs.getString("passwd"));
        user.setOtherInfo(rs.getInt("otherInfo"));
        user.setPlans(rs.getString("plans"));
        user.setRecords(rs.getString("records"));
        //}

        return user;
    }
    public void deleteUser(String userName) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(userName == null)
            throw new TextFormatException("userName is null");
        SQLCommand  = " delete from " + tableName + "where userName = '" + userName + "'";
        du.executeUpdate(SQLCommand);

    }
    public void deleteUser(int userID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " delete from " + tableName + "where userID = " + userID;
        du.executeUpdate(SQLCommand);

    }
    public void editUser(String userName, User new_user) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        if(userName == null)
            throw new TextFormatException("userName is null");
        SQLCommand  = " update " + tableName + " set ";
        if(new_user.getNickName() != null){
            SQLCommand += " nickName = '" + new_user.getNickName() + "'";
            isComma = true;
        }
        if(new_user.getPasswd() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " passwd = '" + new_user.getPasswd() + "'";
            isComma = true;

        }
        if(new_user.getOtherInfo()!=-1){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " otherInfo = " + new_user.getOtherInfo();
            isComma = true;
        }
        if(new_user.getPlans() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " plans = '" + new_user.getPlans() + "'";
            isComma = true;
        }
        if(new_user.getRecords() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " records = '" + new_user.getRecords() + "'";
            isComma = true;
        }
        SQLCommand += " where userName = '" + userName + "'";
        du.executeUpdate(SQLCommand);

    }
    public void editUser(int userID, User new_user) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " update " + tableName + " set ";
        if(new_user.getNickName() != null){
            SQLCommand += " nickName = '" + new_user.getNickName() + "'";
            isComma = true;
        }
        if(new_user.getPasswd() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " passwd = '" + new_user.getPasswd() + "'";
            isComma = true;

        }
        if(new_user.getOtherInfo() >= 0){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " otherInfo = " + new_user.getOtherInfo();
            isComma = true;
        }
        if(new_user.getPlans() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " plans = '" + new_user.getPlans() + "'";
            isComma = true;
        }
        if(new_user.getRecords() != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " records = '" + new_user.getRecords() + "'";
            isComma = true;
        }
        SQLCommand += " where userID = " + userID;
        du.executeUpdate(SQLCommand);

    }
    private final String tableName ="user";


}
