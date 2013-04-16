package edu.thu.cslab.footwith.server;

import edu.thu.cslab.footwith.utility.Util;
import org.json.JSONException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-15
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public class PlanManager {
    private Logger logger= LogManager.getLogger(this.getClass().getName());
    public PlanManager() {
    }
    public boolean addPlan(Plan plan) throws SQLException, TextFormatException, JSONException {

        if(plan == null) {
            logger.error("Can't add empty plan");
            return false;
        }

        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        ResultSet rs;

        String title = plan.getTitle();
        String siteIDs = plan.getSiteIDs();
        int organizer = plan.getOrganizer();
        Date startTime =  plan.getStartTime();
        Date endTime = plan.getEndTime();
        if(siteIDs == null || organizer<0 || startTime==null || endTime==null || startTime.after(endTime)){
            logger.error("illegal plan");
            return false;
        }
        SQLCommand = " insert into " + tableName + " ( title, siteIDs, startTime, endTime, organizer, participants, budget, groupNum, groupNumMax, talkStreamID, isDone ) " +
                " values ( '"+title+"' , '"+ siteIDs + "' , '"+ startTime + "' , '" + endTime+ "' , " + organizer + " , '" + plan.getParticipants() + "' , " + plan.getBudget() + " , " + plan.getGroupNum()+ " , " + plan.getGroupNumMax() + " , " + plan.getTalkStreamID() +" , false ) ";
        rs = du.executeUpdate(SQLCommand);
        rs.next();
        int planID = rs.getInt(1);

        User user = new User();
        String orig_plans =new UserManager().selectUser(organizer).getPlans();
        user.setPlans(new JSONHelper().addToArray(orig_plans, planID));
        new UserManager().editUser(organizer, user);

        Vector<Integer> siteIDVector =new JSONHelper().convertToArray(siteIDs);
        for(int j=0;j<siteIDVector.size();j++){
            int siteID = siteIDVector.get(j);
            SQLCommand = " insert into " + relationTableName +"( userID, siteID, startTime, endTime, planID )" +
                        " values ( " + organizer + " , " + siteID + " , '" + startTime + "' , '" + endTime + "' , " + planID + ")";
            du.executeUpdate(SQLCommand);
        }

        return true;
    }
    public Plan selectPlan(int planID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(planID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " select * from " + tableName + " where planID = " + planID;
        rs=du.executeQuery(SQLCommand);
        rs.next();
        return new Plan(rs.getInt("planID"),rs.getString("title"), rs.getString("siteIDs"), rs.getDate("startTime"), rs.getDate("endTime"), rs.getInt("organizer"),
                rs.getString("participants"), rs.getInt("budget"), rs.getInt("groupNum"), rs.getInt("groupNumMax"), rs.getInt("talkStreamID"),rs.getBoolean("isDone"));

    }

    public Vector<Plan> selectPlan(Plan plan) throws TextFormatException, SQLException {
        assert plan.getTitle().length()<80;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        Plan result_plan;
        boolean isAnd = false;
        if(plan==null)
            throw new TextFormatException();
        String siteIDs = plan.getSiteIDs();
        Date startTime = plan.getStartTime();
        Date endTime = plan.getEndTime();
        int organizer = plan.getOrganizer();
        String participants = plan.getParticipants();
        int budget = plan.getBudget();
        int groupNum = plan.getGroupNum();
        int groupNumMax = plan.getGroupNumMax();
        int talkStreamID = plan.getTalkStreamID();
        SQLCommand  = " select * from " + tableName + " where ";
        if(budget >= 0){
            SQLCommand += " budget = '" +budget + "'";
            isAnd = true;
        }
        if(groupNum >=0 ){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " groupNum = " +groupNum ;
            isAnd = true;
        }
        if(groupNumMax >=0 ){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " groupNumMax = " +groupNumMax;
            isAnd = true;
        }
        if(talkStreamID >=0 ){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " talkStreamID = " +talkStreamID ;
            isAnd = true;
        }
        if(organizer >=0 ){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " organizer = " +organizer;
            isAnd = true;
        }
        if(startTime != null){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " startTime >= '" + startTime + "'";
            isAnd = true;
        }
        if(endTime != null){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " endTime <= '" +endTime + "'";
            isAnd = true;
        }
        rs = du.executeQuery(SQLCommand);
        Vector<Plan> vector = new Vector<Plan>();
        while(rs.next()){
            result_plan = new Plan(rs.getInt("planID"), rs.getString("title"),rs.getString("siteIDs"), rs.getDate("startTime"), rs.getDate("endTime"), rs.getInt("organizer"),
                    rs.getString("participants"), rs.getInt("budget"), rs.getInt("groupNum"), rs.getInt("groupNumMax"), rs.getInt("talkStreamID"),rs.getBoolean("isDOne"));
            vector.add(result_plan);
        }


        return vector;
    }
    public void deletePlan(int planID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(planID < 0)
            throw new TextFormatException("planID is null");
        SQLCommand  = " delete from " + tableName + " where planID = " + planID;
        du.executeUpdate(SQLCommand);
        SQLCommand  = " delete from " + relationTableName + " where planID = " + planID;
        du.executeUpdate(SQLCommand);
    }

    //TODO : if isDone=true can't modify it
    public void editPlan(int planID, Plan new_plan) throws TextFormatException, SQLException, JSONException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null, subSQLcommand=null;
        boolean isComma = false;
        if(planID < 0)
            throw new TextFormatException("userID is null");
        SQLCommand  = " update " + tableName + " set ";

        String siteIDs = new_plan.getSiteIDs();
        String title = new_plan.getTitle();
        Date startTime = new_plan.getStartTime();
        Date endTime = new_plan.getEndTime();
        int organizer = new_plan.getOrganizer();
        String participants = new_plan.getParticipants();
        int budget = new_plan.getBudget();
        int groupNum = new_plan.getGroupNum();
        int groupNumMax = new_plan.getGroupNumMax();
        int talkStreamID = new_plan.getTalkStreamID();
        boolean isDone = new_plan.getIsDone();

        Plan orig_plan = selectPlan(planID);
        String orig_siteIDs = orig_plan.getSiteIDs();
        int orig_organizer = orig_plan.getOrganizer();
        String orig_participants = orig_plan.getParticipants();

        Vector<Integer> orig_siteIDVector=null;
        Vector<Integer> orig_userIDVector=null;
        Vector<Integer> new_siteIDVector=null;
        Vector<Integer> new_userIDVector=null;

        if(orig_siteIDs!=null)
            orig_siteIDVector = new JSONHelper().convertToArray(orig_siteIDs);
        if(orig_participants!=null)
            orig_userIDVector = new JSONHelper().convertToArray(orig_participants);


        if(budget >= 0){
            SQLCommand += " budget = '" +budget + "'";
            isComma = true;
        }
        if(groupNum >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " groupNum = '" +groupNum + "'";
            isComma = true;
        }
        if(groupNumMax >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " groupNumMax = '" +groupNumMax + "'";
            isComma = true;
        }
        if(talkStreamID >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " talkStreamID = '" +talkStreamID + "'";
            isComma = true;
        }
        if (title != null){
            if (isComma)
                SQLCommand += " , ";
            SQLCommand += "title = '"+title+"'";
            isComma = true;
        }
        if (isDone){
            if (isComma)
                SQLCommand += " , ";
            SQLCommand += "isDone = true";
        }
        if(siteIDs != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " siteIDs = '" +siteIDs + "'";
            isComma = true;
            new_siteIDVector =new JSONHelper().convertToArray(siteIDs);
            for(int i=0;i<new_siteIDVector.size();i++){
               if(!orig_siteIDVector.contains(new_siteIDVector.get(i))){
                   for(int j=0;j<orig_userIDVector.size();j++){
                       subSQLcommand = " insert into " + relationTableName + "(userID, siteID, startTime, endTime, planID) values "+
                               orig_userIDVector.get(j)+ " , "+ new_siteIDVector.get(i)+ " , '"+ orig_plan.getStartTime()+ "' , '"+ orig_plan.getEndTime()+ "' , " + planID +")";
                       du.executeUpdate(subSQLcommand);
                   }
               }
            }
            for(int i=0;i<orig_siteIDVector.size();i++){
                if(!new_siteIDVector.contains(orig_siteIDVector.get(i))){
                        subSQLcommand = "delete from " + relationTableName + " where planID = "+ planID +" and siteID = " + orig_siteIDVector.get(i);
                        du.executeUpdate(subSQLcommand);
                }
            }
            orig_siteIDVector = new_siteIDVector;
         }

        if(participants != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " participants = '" +participants + "'";
            isComma = true;
            new_userIDVector =new JSONHelper().convertToArray(participants);

            for(int i=0;i<new_userIDVector.size();i++){
                if(!orig_userIDVector.contains(new_userIDVector.get(i))){
                    for(int j=0;j<orig_siteIDVector.size();j++){
                        subSQLcommand = " insert into " + relationTableName + "(userID, siteID, startTime, endTime, planID) values "+
                                new_userIDVector.get(i)+ " , "+ orig_siteIDVector.get(j)+ " , '"+ orig_plan.getStartTime()+ "' , '"+ orig_plan.getEndTime()+ "' , " + planID +")";
                        du.executeUpdate(subSQLcommand);
                    }
                }
            }
            for(int i=0;i<orig_userIDVector.size();i++){
                if(!new_userIDVector.contains(orig_userIDVector.get(i))){
                    subSQLcommand = "delete from " + relationTableName + " where planID = "+ planID +" and userID = " + orig_userIDVector.get(i);
                    du.executeUpdate(subSQLcommand);
                }
            }
            orig_userIDVector = new_userIDVector;
        }
        if(organizer >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " organizer = '" +organizer + "'";
            isComma = true;
            subSQLcommand = "update " + relationTableName + " set userID = " + organizer + " where planID = " + planID + " and userID = "+orig_organizer;
            du.executeUpdate(subSQLcommand);
        }
        if(startTime != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " startTime = '" + startTime + "'";
            isComma = true;
            subSQLcommand = "update " + relationTableName + " set startTime = '" + startTime + "' where planID = " + planID ;
            du.executeUpdate(subSQLcommand);
        }
        if(endTime != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " endTime = '" +endTime + "'";
            isComma = true;
            subSQLcommand = "update " + relationTableName + " set endTime = '" + endTime + "' where planID = " + planID ;
            du.executeUpdate(subSQLcommand);
        }
        SQLCommand += " where planID = " + planID;
        du.executeUpdate(SQLCommand);


    }

    /**
     * new participant joins the plan
     * @param userID
     * @param planID
     * @return
     * @throws TextFormatException
     * @throws SQLException
     * @throws JSONException
     */
    public boolean joinPlan(int userID,int planID) throws TextFormatException, SQLException, JSONException {
        Plan plan=new PlanManager().selectPlan(planID);
        if (plan.getGroupNum()==plan.getGroupNumMax()) {
            logger.warn("Plan is fulled");
            return false;
        }
        plan.setGroupNum(plan.getGroupNum()+1);
        plan.setParticipants(new JSONHelper().addToArray(plan.getParticipants(),userID));
        new PlanManager().editPlan(plan.getPlanID(),plan);
        return true;
    }

    private final String tableName ="plan";
    private final String relationTableName = "userplan";
}
