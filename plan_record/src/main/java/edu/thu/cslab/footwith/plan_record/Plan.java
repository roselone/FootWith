package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-15
 * Time: 下午9:28
 * To change this template use File | Settings | File Templates.
 */
public class Plan {
    public Plan() {
        this.planID = -1;
        this.siteIDs = "";
        this.title="";
        this.startTime = null;
        this.endTime = null;
        this.organizer = -1;
        this.participants = "";
        this.budget = -1;
        this.groupNum = -1;
        this.groupNumMax = -1;
        this.talkStreamID = -1;
        this.isDone=false;
    }

    public Plan(int planID) {
        this.planID = planID;
        this.siteIDs = "";
        this.startTime = null;
        this.endTime = null;
        this.organizer = -1;
        this.participants = "";
        this.budget = -1;
        this.groupNum = -1;
        this.groupNumMax = -1;
        this.talkStreamID = -1;
        this.isDone=false;
    }

    /**
     *
     * @param title
     * @param siteIDs
     * @param startTime
     * @param endTime
     * @param organizer
     * @param groupNum
     * @param groupNumMax
     */
    public Plan(String title, String siteIDs, Date startTime, Date endTime, int organizer, int groupNum, int groupNumMax,String describe) {
        this.planID = -1;
        this.title = title;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.groupNum = groupNum;
        this.groupNumMax = groupNumMax;
        this.participants = "";
        this.budget = -1;
        this.talkStreamID = -1;
        this.describe=describe;
    }

    /**
     *
     * @param planID
     * @param title
     * @param siteIDs
     * @param startTime
     * @param endTime
     * @param organizer
     * @param participants
     * @param budget
     * @param groupNum
     * @param groupNumMax
     * @param talkStreamID
     * @param isDone
     */
    public Plan(int planID,String title, String siteIDs, Date startTime, Date endTime, int organizer, String participants,
                int budget, int groupNum, int groupNumMax, int talkStreamID,boolean isDone,Timestamp timestamp,String describe) {
        this.planID = planID;
        this.title = title;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.participants = participants;
        this.budget = budget;
        this.groupNum = groupNum;
        this.groupNumMax = groupNumMax;
        this.talkStreamID = talkStreamID;
        this.isDone=isDone;
        this.timestamp=timestamp;
        this.describe=describe;
    }

    public int getPlanID() {
        return planID;
    }

    public String getTitle(){
        return title;
    }

    public String getSiteIDs() {
        return siteIDs;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getOrganizer() {
        return organizer;
    }

    public String getParticipants() {
        return participants;
    }

    public int getBudget() {
        return budget;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public int getGroupNumMax() {
        return groupNumMax;
    }

    public int getTalkStreamID() {
        return talkStreamID;
    }

    public boolean getIsDone(){
        return isDone;
    }

    public Timestamp getTimestamp(){
        return timestamp;
    }

    public String getDescribe(){
        return describe;
    }

    public void setDescribe(String describe){
        this.describe=describe;
    }

    public void setTimestamp(Timestamp ts){
        this.timestamp=ts;
    }

    public void setSiteIDs(String siteIDs) throws TextFormatException{
        if(siteIDs == null || siteIDs.length()==0||siteIDs.length()>100)
            throw new TextFormatException();
        this.siteIDs = siteIDs;
    }

    public void setTitle(String title) throws TextFormatException{
        if (title==null || title.length()==0 || title.length()>80)
            throw new TextFormatException();
        this.title=title;
    }

    public void setStartTime(Date startTime) throws TextFormatException{
        if(startTime==null )
            throw new TextFormatException();
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) throws TextFormatException{
        if(endTime==null )
            throw new TextFormatException();
        this.endTime = endTime;
    }

    public void setOrganizer(int organizer) throws TextFormatException{
        if(organizer<0)
            throw new TextFormatException();
        this.organizer = organizer;
    }

    public void setParticipants(String participants) throws TextFormatException{

        this.participants = participants;
    }

    public void addParticipant(int userID) throws TextFormatException, JSONException {
        setParticipants(JSONHelper.getJSONHelperInstance().addToArray(this.getParticipants(), userID));
    }

    public void setBudget(int budget) {

        this.budget = budget;
    }

    public void setGroupNum(int groupNum) throws TextFormatException{
        if(groupNum<0)
            throw new TextFormatException();
        this.groupNum = groupNum;
    }

    public void setGroupNumMax(int groupNumMax) throws TextFormatException{
        if(groupNumMax<0)
            throw new TextFormatException();
        this.groupNumMax = groupNumMax;
    }

    public void setTalkStreamID(int talkStreamID) throws TextFormatException{
        if(talkStreamID<0)
            throw new TextFormatException();
        this.talkStreamID = talkStreamID;
    }

    public void setIsDone(boolean status){
        this.isDone=status;
    }

    private int planID;
    private String title;
    private String siteIDs;
    private Date startTime;
    private Date endTime;
    private int organizer;
    private String participants;
    private int budget;
    private int groupNum;
    private int groupNumMax;
    private int talkStreamID;
    private Timestamp timestamp;
    private boolean isDone;
    private String describe;

}
