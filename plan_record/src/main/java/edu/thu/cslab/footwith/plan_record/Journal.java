package edu.thu.cslab.footwith.plan_record;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Journal {
    private int userID;
    private String title;
    private String body;
    private int comments;
    private Date date;
    private int journalID;
    private Timestamp timestamp;

    public Journal(){}
    public Journal(int userID,String title, String body, Date date,Timestamp timestamp){
        this.userID=userID;         // -1
        this.title=title;           // null
        this.body=body;             // null
        this.date=date;             // none
        this.timestamp=timestamp;
    }

    public void setJournalID(int journalID){this.journalID=journalID;}
    public void setUserID(int userID){this.userID=userID;}
    public void setTitle(String title){this.title = title;}
    public void setBody(String body){this.body = body ;}
    public void setComments(int comments){this.comments = comments;}
    public void setDate(Date date){this.date = date;}
    public Timestamp getTimestamp(){return timestamp;}

    public void setTimestamp(Timestamp ts){this.timestamp=ts;}
    public int getJournalID(){return  this.journalID;}
    public int getUserID(){return this.userID;}
    public String getTitle(){return this.title;}
    public String getBody(){return this.body;}
    public int getComments(){return this.comments;}
    public Date getDate(){return this.date;}

}
