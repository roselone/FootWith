package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.utility.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class JournalManager {
    private static final String tableName = "journal";

    /**
     * add journal
     * @param journal
     * @return journal ID
     * @throws java.sql.SQLException
     */
    public static int addJournal(Journal journal) throws SQLException {
        String SQLComment = "insert into " + tableName + " ( userID, title, body, time ) values (" + journal.getUserID()
                + " , '" + journal.getTitle() + "' , '"+journal.getBody()+"' , '" + journal.getDate() + "')" ;
        DBUtil du= DBUtil.getDBUtil();
        ResultSet rs=du.executeUpdate(SQLComment);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * edit journal
     * @param journalID
     * @param journalMap
     * @throws java.sql.SQLException
     */
    public static void editJournal(int journalID,HashMap<String,String> journalMap) throws  SQLException {
        String SQLComment = "update " + tableName + " set ";
        boolean flag=false;
        if (!Util.isEmpty(journalMap.get("title"))) { flag=true; SQLComment += " title = '" + journalMap.get("title")+"'";}
        if (!Util.isEmpty(journalMap.get("body"))) {
            if (flag)
                SQLComment += " , ";
            flag=true; SQLComment += " body = '" + journalMap.get("body")+"'";
        }
        if (flag){
            SQLComment+=" where journalID = "+String.valueOf(journalID)+";";
            DBUtil.getDBUtil().executeUpdate(SQLComment);
        }
    }

    /**
     * delete journal
     * @param journalID
     * @throws java.sql.SQLException
     */
    public static void deleteJournal(int journalID) throws SQLException {
        String SQLComment = "delete from " + tableName + " where journalID=" + String.valueOf(journalID) + ";";
        DBUtil.getDBUtil().executeUpdate(SQLComment);
    }

    /**
     * select journal
     * @param journalID
     * @return
     * @throws java.sql.SQLException
     */
    public static Journal selectJournal(int journalID) throws SQLException {
        String SQLComment = "select * from " + tableName + " where journalID = " +String.valueOf(journalID)+";";
        ResultSet rs = DBUtil.getDBUtil().executeQuery(SQLComment);
        rs.next();
        Journal result=new Journal(rs.getInt("userID"),rs.getString("title"),rs.getString("body"),rs.getDate("time"),rs.getTimestamp("timestamp"));
        result.setJournalID(journalID);
        return result;
    }

}
