package edu.thu.cslab.footwith.server;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-15
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public class PlanManager {
    public PlanManager() {
    }
    public void addPlan(Plan plan) throws SQLException, TextFormatException {
        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        ResultSet rs;
        if(plan==null)
            throw new TextFormatException();
        String siteIDs = plan.getSiteIDs();
        int organizer = plan.getOrganizer();
        String participants = plan.getParticipants();
        Date startTime =  plan.getStartTime();
        Date endTime = plan.getEndTime();
        if(siteIDs == null || organizer<0 || participants==null){
            throw new TextFormatException();
        }
        SQLCommand = " insert into " + tableName + " (  siteIDs, startTime, endTime, organizer, participants, budget, groupNum, groupNumMax, talkStreamID ) " +
                " values ( '"+  plan.getSiteIDs()+ "' , '"+ plan.getStartTime()+ "' , '" + plan.getEndTime()+ "' , " + plan.getOrganizer()+ " , '" + plan.getParticipants() + "' , " + plan.getBudget() + " , " + plan.getGroupNum()+ " , " + plan.getGroupNumMax() + " , " + plan.getTalkStreamID() +" ) ";
        rs = du.executeQuery(SQLCommand);
        int planID = rs.getInt("planID"); // maybe wrong
        /*
        Vector<Integer> siteIDVector = JSONHelper.convertToArray(siteIDs);
        Vector<Integer> userIDVector = JSONHelper.convertToArray(participants);
        for(int i=0;i<userIDVector.size(); i++){
            for(int j=0;j<siteIDVector.size();j++){
                int userID = userIDVector.get(i);
                int siteID = siteIDVector.get(i);
                SQLCommand = " insert into " + relationTableName +"( userID, siteID, startTime, endTime, planID )" +
                        " values ( " + userID + " , " + siteID + " , '" + startTime + "' , '" + endTime + "' , " + planID + ")";
                du.executeUpdate(SQLCommand);
            }
        }
        */
    }
    public Plan selectPlan(int planID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(planID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " select * from " + tableName + "where planID = " + planID;
        rs=du.executeQuery(SQLCommand);
        return new Plan(rs.getInt("planID"), rs.getString("siteIDs"), rs.getDate("startTime"), rs.getDate("endTime"), rs.getInt("organizer"),
                rs.getString("participants"), rs.getInt("budget"), rs.getInt("groupNum"), rs.getInt("groupNumMax"), rs.getInt("talkStreamID"));

    }
    public void deletePlan(int planID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(planID < 0)
            throw new TextFormatException("planID is null");
        SQLCommand  = " delete from " + tableName + "where planID is " + planID;
        rs = du.executeQuery(SQLCommand);
        SQLCommand  = " delete from " + relationTableName + "where planID is " + planID;
        rs = du.executeQuery(SQLCommand);
    }

    public void editPlan(int planID, Plan new_plan) throws TextFormatException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null, subSQLcommand=null;
        boolean isComma = false;
        if(planID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " update " + tableName + " set ";


    }

    private final String tableName ="plan";
    private final String relationTableName = "userplan";
}
