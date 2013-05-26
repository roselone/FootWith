package edu.thu.cslab.footwith.site;

import edu.thu.cslab.footwith.exception.TextFormatException;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 3/15/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Site {
    public Site(String siteName,String location, int rate ) {
        this.siteName = siteName;
        this.rate = rate;
        this.location = location;
        this.siteID = -1;
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

    /**
     *
     * @param siteName
     * @param rate
     * @param location
     */
    public Site(String siteName, int rate, String location) {
        this.siteName = siteName;
        this.rate = rate;
        this.location = location;
    }

    /**
     *
     * @param siteID
     * @param siteName
     * @param rate
     * @param location
     */
    public Site(int siteID, String siteName, int rate, String location) {
        this.siteID = siteID;
        this.siteName = siteName;
        this.location = location;
        this.rate = rate;
    }

    /**
     *
     * @param siteID
     * @param siteName
     * @param rate
     * @param location
     * @param brief
     * @param picture
     */
    public Site(int siteID, String siteName, int rate, String location, String brief, int picture,int latitude,int longitude) {
        this.siteID = siteID;
        this.siteName = siteName;
        this.rate = rate;
        this.location = location;
        this.brief = brief;
        this.picture = picture;
        this.latitude=latitude;
        this.longitude=longitude;
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

    public int getLatitude(){
        return latitude;
    }

    public int getLongitude(){
        return longitude;
    }

    public void setLatitude(int v){
        this.latitude=v;
    }

    public void setLongitude(int v){
        this.longitude=v;
    }

    public void setSiteID(int ID){
        this.siteID=ID;
    }
    public void setSiteName(String siteName) throws TextFormatException{
        if(siteName == null || siteName.length()==0 || siteName.length()>20)
            throw new TextFormatException();
        this.siteName = siteName;
    }

    public void setRate(int rate) throws TextFormatException{
        if(rate<0)
            throw new TextFormatException();
        this.rate = rate;
    }

    public void setLocation(String location) throws TextFormatException{
        if(location == null || location.length()==0 || location.length()>32)
            throw new TextFormatException();
        this.location = location;
    }

    public void setBrief(String brief) throws TextFormatException {
               this.brief = brief;
    }

    public void setPicture(int picture) throws TextFormatException{
                this.picture = picture;
    }

    private int siteID;
    private String siteName;
    private int rate;
    private String location;
    private String brief;   // type text in sql database
    private int picture;
    private int latitude;
    private int longitude;
}
