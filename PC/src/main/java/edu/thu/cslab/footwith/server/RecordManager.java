package edu.thu.cslab.footwith.server;

import org.json.JSONException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-16
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class RecordManager {
    private Logger logger=LogManager.getLogger(this.getClass().getName());
    public RecordManager() {
    }
    public boolean addRecord(Record  record) throws SQLException, TextFormatException, JSONException {
        String SQLCommand;
        DBUtil du = DBUtil.getDBUtil();
        ResultSet rs;
        if(record==null) {
            logger.error("Can't add empty record");
            return false;
        }
        String title = record.getTitle();
        String siteIDs = record.getSiteIDs();
        String userIDs = record.getUserIDs();
        Date startTime =  record.getStartTime();
        Date endTime = record.getEndTime();
        if(siteIDs == null || userIDs==null || startTime==null){
            logger.error("illegal record");
            return false;
        }

        SQLCommand = " insert into " + tableName + " (title,  siteIDs, startTime, userIDs, groupNum, journals, pictures, talkStreamID, isDone ) " +
                " values ( '"+ title+"' , '"+ siteIDs+ "' , '"+ startTime + "' , '" + userIDs + "' , " + record.getGroupNum()+ " , '" + record.getJournals() + "' , '" + record.getPictures() + "' , " + record.getTalkStreamID() +", false ) ";
        rs = du.executeUpdate(SQLCommand);
        rs.next();
        int recordID = rs.getInt(1); // maybe wrong

        if (endTime!=null){
            SQLCommand="update "+tableName+" set endTime = '"+endTime+"' where recordID = " +recordID+";";
            du.executeUpdate(SQLCommand);
        }

        Vector<Integer> siteIDVector =new JSONHelper().convertToArray(siteIDs);
        Vector<Integer> userIDVector =new JSONHelper().convertToArray(userIDs);
        for(int i=0;i<userIDVector.size(); i++){
            for(int j=0;j<siteIDVector.size();j++){
                if (endTime!=null){
                    SQLCommand = " insert into " + relationTableName +" ( userID, siteID, startTime, endTime, recordID )" +
                            " values ( " + userIDVector.get(i) + " , " + siteIDVector.get(j) + " , '" + startTime + "' , '" + endTime + "' , " + recordID + ")";
                }else{
                    SQLCommand = " insert into " + relationTableName +" ( userID, siteID, startTime, recordID )" +
                            " values ( " + userIDVector.get(i) + " , " + siteIDVector.get(j) + " , '" + startTime + "' , " + recordID + ")";
                }
                du.executeUpdate(SQLCommand);
            }
        }
        UserManager um = new UserManager();
        User user;
        JSONHelper jh = new JSONHelper();
        String orig_records;
        for(int i=0;i<userIDVector.size(); i++){
            user = um.selectUser(userIDVector.get(i));
            orig_records = user.getRecords();
            user.setRecords(jh.addToArray(orig_records, recordID));
            um.editUser(userIDVector.get(i), user);
        }
        return true;
    }
    public Record selectRecord(int recordID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(recordID < 0)
            throw new TextFormatException();
        SQLCommand  = " select * from " + tableName + " where recordID = " + recordID;
        rs=du.executeQuery(SQLCommand);
        rs.next();
        return new Record(rs.getInt("recordID"),rs.getString("title"), rs.getString("siteIDs"), rs.getDate("startTime"), rs.getDate("endTime"),
                rs.getString("userIDs"), rs.getInt("groupNum"), rs.getString("journals"), rs.getString("pictures"), rs.getInt("talkStreamID"),rs.getBoolean("isDone"));

    }

    /**
     * convert plan to record, update userinfo,set plan.isDone=true
     * update userrecord table
     * @param plan start time need to be changed
     * @return success or not
     * @throws JSONException
     */
    public boolean addRecordFromPlan(Plan plan) throws JSONException {
        Record record=new Record(plan);
        try {
            if(!addRecord(record)){
                logger.error("add Record failed!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        plan.setIsDone(true);
        try {
            new PlanManager().editPlan(plan.getPlanID(),plan);
        } catch (TextFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return true;
    }

    public void deleteRecord(int recordID) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(recordID < 0)
            throw new TextFormatException("recordID is null");
        SQLCommand  = " delete from " + tableName + " where recordID = " + recordID;
        du.executeUpdate(SQLCommand);
        SQLCommand  = " delete from " + relationTableName + " where recordID = " + recordID;
        du.executeUpdate(SQLCommand);
    }

    public void addJournal(int recordID, Journal journal) throws SQLException, TextFormatException, JSONException {
        JournalManager jm=new JournalManager();
        int journalID=jm.addJournal(journal);
        Record record=selectRecord(recordID);
        String journals=JSONHelper.getJSONHelperInstance().addToArray(record.getJournals(),journalID);
        String SQLCommand = "update " + tableName + " set journals=" + journals + " where recordID=" + recordID +";";
        DBUtil.getDBUtil().executeUpdate(SQLCommand);
    }

    public void addPicture(int recordID, Picture picture) throws SQLException, TextFormatException, JSONException {
        PictureManager pm=new PictureManager();
        int pictureID=pm.addPicture(picture);
        Record record=selectRecord(recordID);
        String pictures=JSONHelper.getJSONHelperInstance().addToArray(record.getPictures(),pictureID);
        String SQLCommand = "update " + tableName + " set pictures=" + pictures + " where recordID=" + recordID +";";
        DBUtil.getDBUtil().executeUpdate(SQLCommand);
    }

    /**
     * update record's endTime
     * @param recordID
     * @param date
     */
    public void endRecord(int recordID,Date date){
        String SQLCommand="update " + tableName + " set date=" + date.toString() +" where recordID=" + recordID +";";
    }

   /* public void editRecord(int recordID, Record new_record) throws TextFormatException, SQLException, JSONException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null, subSQLcommand=null;
        boolean isComma = false;
        if(recordID < 0)
            throw new TextFormatException("recordID is null");
        SQLCommand  = " update " + tableName + " set ";

        String siteIDs = new_record.getSiteIDs();
        Date startTime = new_record.getStartTime();
        Date endTime = new_record.getEndTime();
        String userIDs = new_record.getUserIDs();
        int groupNum = new_record.getGroupNum();
        String journals = new_record.getJournals();
        String pictures = new_record.getPictures();
        int talkStreamID = new_record.getTalkStreamID();

        Record orig_record = selectRecord(recordID);
        String orig_siteIDs = orig_record.getSiteIDs();
        String orig_userIDs = orig_record.getUserIDs();
        String orig_journals = orig_record.getJournals();
        String orig_pictures = orig_record.getPictures();

        Vector<Integer> orig_siteIDVector=null;
        Vector<Integer> orig_userIDVector=null;
        Vector<Integer> new_siteIDVector=null;
        Vector<Integer> new_userIDVector=null;

        Vector<Integer> orig_journalsVector = null;
        Vector<Integer> orig_picturesVector = null;
        Vector<Integer> new_journalsVector = null;
        Vector<Integer> new_picturesVector = null;

        if(orig_siteIDs!=null)
            orig_siteIDVector = new JSONHelper().convertToArray(orig_siteIDs);
        if(orig_userIDs!=null)
            orig_userIDVector = new JSONHelper().convertToArray(orig_userIDs);
        if(orig_journals!=null)
            orig_journalsVector = new JSONHelper().convertToArray(orig_journals);
        if(orig_pictures!=null)
            orig_picturesVector = new JSONHelper().convertToArray(orig_pictures);

        if(groupNum >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " groupNum = '" +groupNum + "'";
            isComma = true;
        }
        if(talkStreamID >=0 ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " talkStreamID = '" +talkStreamID + "'";
            isComma = true;
        }
        if(journals != null ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " journals = '" +journals + "'";  // only handle records' journals, note the journal table
            isComma = true;
        }
        if(pictures != null ){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " pictures = '" +pictures + "'";  //// only handle records' pictures, not the picture table
            isComma = true;
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
                        subSQLcommand = " insert into " + relationTableName + "(userID, siteID, startTime, endTime, recordID) values "+
                                orig_userIDVector.get(j)+ " , "+ new_siteIDVector.get(i)+ " , '"+ orig_record.getStartTime()+ "' , '"+ orig_record.getEndTime()+ "' , " + recordID +")";
                        du.executeUpdate(subSQLcommand);
                    }
                }
            }
            for(int i=0;i<orig_siteIDVector.size();i++){
                if(!new_siteIDVector.contains(orig_siteIDVector.get(i))){
                    subSQLcommand = "delete from " + relationTableName + " where recordID = "+ recordID +" and siteID = " + orig_siteIDVector.get(i);
                    du.executeUpdate(subSQLcommand);
                }
            }
            orig_siteIDVector = new_siteIDVector;
        }
        if(userIDs != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " userIDs = '" + userIDs + "'";
            isComma = true;
            new_userIDVector =new JSONHelper().convertToArray(userIDs);

            for(int i=0;i<new_userIDVector.size();i++){
                if(!orig_userIDVector.contains(new_userIDVector.get(i))){
                    for(int j=0;j<orig_siteIDVector.size();j++){
                        subSQLcommand = " insert into " + relationTableName + "(userID, siteID, startTime, endTime, recordID) values "+
                                new_userIDVector.get(i)+ " , "+ orig_siteIDVector.get(j)+ " , '"+ orig_record.getStartTime()+ "' , '"+ orig_record.getEndTime()+ "' , " + recordID +")";
                        du.executeUpdate(subSQLcommand);
                    }
                }
            }
            for(int i=0;i<orig_userIDVector.size();i++){
                if(!new_userIDVector.contains(orig_userIDVector.get(i))){
                    subSQLcommand = "delete from " + relationTableName + " where recordID = "+ recordID +" and userID = " + orig_userIDVector.get(i);
                    du.executeUpdate(subSQLcommand);
                }
            }
            orig_userIDVector = new_userIDVector;
        }
        if(startTime != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " startTime = '" + startTime + "'";
            isComma = true;
            subSQLcommand = "update " + relationTableName + " set startTime = '" + startTime + "' where recordID = " + recordID ;
            du.executeUpdate(subSQLcommand);
        }
        if(endTime != null){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " endTime = '" +endTime + "'";
            isComma = true;
            subSQLcommand = "update " + relationTableName + " set endTime = '" + endTime + "' where recordID = " + recordID ;
            du.executeUpdate(subSQLcommand);
        }
        SQLCommand += " where recordID = " + recordID;
        du.executeUpdate(SQLCommand);
    }*/

    private final String tableName ="record";
    private final String relationTableName = "userrecord";
}
