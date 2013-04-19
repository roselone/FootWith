package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-16
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class Record {
    public Record() {
        recordID = -1;
        title = null;
        siteIDs = null;
        startTime = null;
        endTime = null;
        userIDs = null;
        groupNum = -1;
        journals = null;
        pictures = null;
        talkStreamID = -1;
        isDone=false;
    }
    public Record(int recordID) {
        this.recordID = recordID;
        title = null;
        siteIDs = null;
        startTime = null;
        endTime = null;
        userIDs = null;
        groupNum = -1;
        journals = null;
        pictures = null;
        talkStreamID = -1;
        isDone=false;
    }

    public Record(Plan plan) throws JSONException {
        this.title=plan.getTitle();
        this.siteIDs=plan.getSiteIDs();
        this.startTime=plan.getStartTime();
        this.userIDs=new JSONHelper().addToArray(plan.getParticipants(),plan.getOrganizer());
        this.groupNum=plan.getGroupNum();
        this.isDone=false;
    }

    public Record(String title,String siteIDs, Date startTime, String userIDs, int groupNum) {
         recordID = -1;
        endTime = null;
        journals = null;
        pictures = null;
        talkStreamID = -1;
        this.title = title;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.userIDs = userIDs;
        this.groupNum = groupNum;
        isDone=false;

    }

    public Record(int recordID, String title,String siteIDs, Date startTime, Date endTime, String userIDs, int groupNum, String journals, String pictures, int talkStreamID,boolean isDone) {
        this.recordID = recordID;
        this.title=title;
        this.siteIDs = siteIDs;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userIDs = userIDs;
        this.groupNum = groupNum;
        this.journals = journals;
        this.pictures = pictures;
        this.talkStreamID = talkStreamID;
        this.isDone=isDone;
    }

    public int getRecordID() {
        return recordID;
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

    public String getUserIDs() {
        return userIDs;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public String getJournals() {
        return journals;
    }

    public String getPictures() {
        return pictures;
    }

    public int getTalkStreamID() {
        return talkStreamID;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setSiteIDs(String siteIDs) {
        this.siteIDs = siteIDs;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setUserIDs(String userIDs) {
        this.userIDs = userIDs;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public void setJournals(String journals) {
        this.journals = journals;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public void setTalkStreamID(int talkStreamID) {
        this.talkStreamID = talkStreamID;
    }

    public boolean isDone() {
        return isDone;
    }

    private int recordID;
    private String title;
    private String siteIDs;
    private Date startTime;
    private Date endTime;
    private String userIDs;
    private int groupNum;
    private String journals;
    private String pictures;
    private int talkStreamID;
    private boolean isDone;

}
