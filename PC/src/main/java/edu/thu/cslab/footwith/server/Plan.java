package edu.thu.cslab.footwith.server;

import java.sql.Date;

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
        this.startTime = null;
        this.endTime = null;
        this.organizer = -1;
        this.participants = "";
        this.budget = -1;
        this.groupNum = -1;
        this.groupNumMax = -1;
        this.talkStreamID = -1;
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
    }

    public Plan( String siteIDs, Date startTime, Date endTime, int organizer, int groupNum, int groupNumMax) {
        this.planID = -1;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.groupNum = groupNum;
        this.groupNumMax = groupNumMax;
        this.participants = "";
        this.budget = -1;
        this.talkStreamID = -1;
    }

    public Plan(int planID, String siteIDs, Date startTime, Date endTime, int organizer, String participants, int budget, int groupNum, int groupNumMax, int talkStreamID) {
        this.planID = planID;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.organizer = organizer;
        this.participants = participants;
        this.budget = budget;
        this.groupNum = groupNum;
        this.groupNumMax = groupNumMax;
        this.talkStreamID = talkStreamID;
    }

    public int getPlanID() {
        return planID;
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

    public void setSiteIDs(String siteIDs) throws TextFormatException{
        if(siteIDs == null || siteIDs.length()==0||siteIDs.length()>100)
            throw new TextFormatException();
        this.siteIDs = siteIDs;
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

    private int planID;
    private String siteIDs;
    private Date startTime;
    private Date endTime;
    private int organizer;
    private String participants;
    private int budget;
    private int groupNum;
    private int groupNumMax;
    private int talkStreamID;

}
