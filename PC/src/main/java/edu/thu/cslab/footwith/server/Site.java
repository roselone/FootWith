package edu.thu.cslab.footwith.server;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 3/15/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Site {
    public Site(int siteID) {
        this.siteID = siteID;
        this.siteName = null;
        this.rate = -1;
        this.location = null;
        this.brief = null;
        this.picture = -1;
    }

    public Site() {
        this.siteID = -1;
        this.siteName = null;
        this.rate = -1;
        this.location = null;
        this.brief = null;
        this.picture = -1;

    }

    public int getSiteID() {
        return siteID;
    }

    public String getSiteName() {
        return siteName;
    }

    public int getRate() {
        return rate;
    }

    public String getLocation() {
        return location;
    }

    public String getBrief() {
        return brief;
    }

    public int getPicture() {
        return picture;
    }

    public void setSiteName(String siteName) throws TextFormatException{
        this.siteName = siteName;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    private int siteID;
    private String siteName;
    private int rate;
    private String location;
    private String brief;   // type text in sql database
    private int picture;
}
