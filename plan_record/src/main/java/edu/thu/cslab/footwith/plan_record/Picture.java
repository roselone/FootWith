package edu.thu.cslab.footwith.plan_record;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class Picture {
    private int userID;
    private String title;
    private String picturePath; 
    private int comments;
    private Date date;
    private int pictureID;
    private Timestamp timestamp;

    public Picture(){}
    public Picture(int userID,String title, String picturePath, Date date){
        this.userID=userID;
        this.title=title;
        this.picturePath=picturePath;
        this.date=date;
    }

    public void setPictureID(int pictureID){this.pictureID=pictureID;}
    public void setUserID(int userID){this.userID=userID;}
    public void setTitle(String title){this.title = title;}
    public void setPicturePath(String picturePath){this.picturePath = picturePath ;}
    public void setComments(int comments){this.comments = comments;}
    public void setDate(Date date){this.date = date;}
    public Timestamp getTimestamp(){return timestamp;}

    public void setTimestamp(Timestamp ts){this.timestamp=ts;}
    public int getPictureID(){return this.pictureID;}
    public int getUserID(){return this.userID;}
    public String getTitle(){return this.title;}
    public String getPicturePath(){return this.picturePath;}
    public int getComments(){return this.comments;}
    public Date getDate(){return this.date;}
}
