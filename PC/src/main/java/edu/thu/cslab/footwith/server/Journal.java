package edu.thu.cslab.footwith.server;

import java.sql.Date;

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

    public Journal(){}
    public Journal(int userID,String title, String body, Date date){
        this.userID=userID;         // -1
        this.title=title;           // null
        this.body=body;             // null
        this.date=date;             // none
    }

    public void setJournalID(int journalID){this.journalID=journalID;}
    public void setUserID(int userID){this.userID=userID;}
    public void setTitle(String title){this.title = title;}
    public void setBody(String body){this.body = body ;}
    public void setComments(int comments){this.comments = comments;}
    public void setDate(Date date){this.date = date;}

    public int getJournalID(){return  this.journalID;}
    public int getUserID(){return this.userID;}
    public String getTitle(){return this.title;}
    public String getBody(){return this.body;}
    public int getComments(){return this.comments;}
    public Date getDate(){return this.date;}

}
