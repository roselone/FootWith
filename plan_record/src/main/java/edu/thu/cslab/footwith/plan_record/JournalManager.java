package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.dao.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class JournalManager {
    private final String tableName = "journal";

    /**
     * add journal
     * @param journal
     * @return journal ID
     * @throws java.sql.SQLException
     */
    public int addJournal(Journal journal) throws SQLException {
        String SQLComment = "insert into " + tableName + " ( userID, title, body, time ) values (" + journal.getUserID()
                + " , '" + journal.getTitle() + "' , '" + journal.getDate() + "')" ;
        DBUtil du= DBUtil.getDBUtil();
        ResultSet rs=du.executeUpdate(SQLComment);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * edit journal
     * @param journalID
     * @param journal
     * @throws java.sql.SQLException
     */
    public void editJournal(int journalID,Journal journal) throws  SQLException {
        String SQLComment = "update " + tableName + " set ";
        boolean flag=false;
        if (journal.getTitle()!=null) { flag=true; SQLComment += "title = " + journal.getTitle();}
        if (journal.getBody() !=null) { flag=true; SQLComment += " body = " + journal.getBody(); }
        if (flag){
            SQLComment+=";";
            DBUtil.getDBUtil().executeUpdate(SQLComment);
        }
    }

    /**
     * delete journal
     * @param journalID
     * @throws java.sql.SQLException
     */
    public void deleteJournal(int journalID) throws SQLException {
        String SQLComment = "delete from " + tableName + " where journalID=" + String.valueOf(journalID) + ";";
        DBUtil.getDBUtil().executeUpdate(SQLComment);
    }

    /**
     * select journal
     * @param journalID
     * @return
     * @throws java.sql.SQLException
     */
    public Journal selectJournal(int journalID) throws SQLException {
        String SQLComment = "select * from " + tableName + " where journalID = " +String.valueOf(journalID)+";";
        ResultSet rs = DBUtil.getDBUtil().executeQuery(SQLComment);
        rs.next();
        Journal result=new Journal(rs.getInt("userID"),rs.getString("title"),rs.getString("body"),rs.getDate("time"),rs.getTimestamp("timestamp"));
        result.setJournalID(journalID);
        return result;
    }

}
