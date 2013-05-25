package edu.thu.cslab.footwith.user;

import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 下午9:41
 * To change this template use File | Settings | File Templates.
 */
public class UserManager {
    static Logger logger=LogManager.getLogger("UserManager");
    public UserManager() { }
    public static int addUser(User user) throws TextFormatException, SQLException {
        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        if(user == null)
            throw new TextFormatException();
        SQLCommand = " insert into " + tableName + " ( userName, nickName, passwd, otherInfo, plans, records, sex ) " +
                " values ( '"+ user.getUserName()+"' , '"+ user.getNickName()+ "' , '"+ user.getPasswd()+ "' , " + user.getOtherInfo()+ " , '" + user.getPlans()+ "' , '" + user.getRecords() +"' , "+user.getSex()+ " ) ";
        ResultSet rs=du.executeUpdate(SQLCommand);
        rs.next();
        return rs.getInt(1);
    }

    public static User selectUser(String userName) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User user;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        //if(userName == null)
        if(Util.isEmpty(userName))
            throw new TextFormatException("userName is null");
        SQLCommand  = " select * from " + tableName + " where userName = '" + userName+"'";

        rs=du.executeQuery(SQLCommand);
        // while(rs.next()){
        rs.next();
        //user = new User(rs.getInt("userID"), rs.getString("userName"), rs.getString("nickName"), rs.getString("passwd"), rs.getInt("otherInfo"), rs.getString("plans"), rs.getString("records"));
        user = new User(rs.getInt("userID"));
        user.setUserName(rs.getString("userName"));
        user.setNickName(rs.getString("nickName"));
        user.setPasswd(rs.getString("passwd"));
        user.setOtherInfo(rs.getInt("otherInfo"));
        user.setPlans(rs.getString("plans"));
        user.setRecords(rs.getString("records"));
        user.setSex(rs.getBoolean("sex"));
        user.setLike(rs.getString("like"));
        user.setMarks(rs.getString("marks"));
        user.setSinaWeiboToken(rs.getString("sinaWeiboToken"));
        user.setSinaExpiresIN(rs.getString("sinaExpiresIN"));
        // }

        return user;
    }

    public static String getUserName(int userID) throws SQLException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand="select nickName from " + tableName+" where userID= "+userID;
        ResultSet rs=du.executeQuery(SQLCommand);
        rs.next();
        return rs.getString(1);
    }

    public static User selectUser(int userID) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User user=new User(userID);
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " select * from " + tableName + " where userID = " + userID;
        logger.debug("SQL={}",SQLCommand);
        rs=du.executeQuery(SQLCommand);
        //while(rs.next()){
        rs.next();
        //user = new User(rs.getInt("userID"), rs.getString("userName"), rs.getString("nickName"), rs.getString("passwd"), rs.getInt("otherInfo"), rs.getString("plans"), rs.getString("records"));
        user.setUserName(rs.getString("userName"));
        user.setNickName(rs.getString("nickName"));
        user.setPasswd(rs.getString("passwd"));
        user.setOtherInfo(rs.getInt("otherInfo"));
        user.setPlans(rs.getString("plans"));
        user.setRecords(rs.getString("records"));
        user.setSex(rs.getBoolean("sex"));
        user.setLike(rs.getString("like"));
        user.setMarks(rs.getString("marks"));
        user.setSinaWeiboToken(rs.getString("sinaWeiboToken"));
        user.setSinaExpiresIN(rs.getString("sinaExpiresIN"));
        //}

        return user;
    }
    public static void deleteUser(String userName) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        //if(userName == null)
        if(Util.isEmpty(userName))
            throw new TextFormatException("userName is null");
        SQLCommand  = " delete from " + tableName + " where userName = '" + userName + "'";
        du.executeUpdate(SQLCommand);

    }
    public static void deleteUser(int userID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " delete from " + tableName + " where userID = " + userID;
        du.executeUpdate(SQLCommand);

    }
    public static void editUser(String userName, User new_user) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        //if(userName == null)
        if(Util.isEmpty(userName))
            throw new TextFormatException("userName is null");
        SQLCommand  = " update " + tableName + " set ";
        //if(new_user.getNickName() != null){
        if(!Util.isEmpty(new_user.getNickName())) {
            SQLCommand += " nickName = '" + new_user.getNickName() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getPasswd())){
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
        //if(new_user.getPlans() != null){
        if(!Util.isEmpty(new_user.getPlans())) {
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " plans = '" + new_user.getPlans() + "'";
            isComma = true;
        }
        //if(new_user.getRecords() != null){
        if(!Util.isEmpty(new_user.getRecords())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " records = '" + new_user.getRecords() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getLike())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " `like` = '" + new_user.getLike() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getMarks())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " marks = '" + new_user.getMarks() + "'";
            isComma = true;
        }
        if (!Util.isEmpty(new_user.getSinaWeiboToken())){
            if(isComma)
                SQLCommand+=" , ";
            SQLCommand +=" sinaWeiboToken = '"+new_user.getSinaWeiboToken() +"'";
        }
        if (!Util.isEmpty(new_user.getSinaExpiresIN())){
            if(isComma)
                SQLCommand+=" , ";
            SQLCommand +=" sinaExpiresIN = '"+new_user.getSinaExpiresIN() +"'";
        }
        SQLCommand += " where userName = '" + userName + "'";
        du.executeUpdate(SQLCommand);

    }
    public static void editUser(int userID, User new_user) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        if(userID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " update " + tableName + " set ";
        //if(new_user.getNickName() != null){
        if(!Util.isEmpty(new_user.getNickName())){
            SQLCommand += " nickName = '" + new_user.getNickName() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getPasswd())){
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
        //if(new_user.getPlans() != null){
        if(!Util.isEmpty(new_user.getPlans())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " plans = '" + new_user.getPlans() + "'";
            isComma = true;
        }
        //if(new_user.getRecords() != null){
        if(!Util.isEmpty(new_user.getRecords())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " records = '" + new_user.getRecords() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getLike())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " `like` = '" + new_user.getLike() + "'";
            isComma = true;
        }
        if(!Util.isEmpty(new_user.getMarks())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " marks = '" + new_user.getMarks() + "'";
            isComma = true;
        }
        if (!Util.isEmpty(new_user.getSinaWeiboToken())){
            if(isComma)
                SQLCommand+=" , ";
            SQLCommand +=" sinaWeiboToken = '"+new_user.getSinaWeiboToken() +"'";
        }
        if (!Util.isEmpty(new_user.getSinaExpiresIN())){
            if(isComma)
                SQLCommand+=" , ";
            SQLCommand +=" sinaExpiresIN = '"+new_user.getSinaExpiresIN() +"'";
        }
        SQLCommand += " where userID = " + userID;
        du.executeUpdate(SQLCommand);

    }

    public static void updateSinaToken(int userID,String token,String time) throws SQLException {
        String SQLCommand="update user set sinaWeiboToken = '"+token+"', sinaExpiresIN = '"+time+"' where userID = "+userID;
        DBUtil.getDBUtil().executeUpdate(SQLCommand);
        return ;
    }

    public static void updateUserLike(int userID,String likes) throws SQLException {
        String SQLCommand = "update user set `like` = '"+likes+"' where userID = "+userID;
        DBUtil.getDBUtil().executeUpdate(SQLCommand);
        return ;
    }

    private static final String tableName ="user";


}
