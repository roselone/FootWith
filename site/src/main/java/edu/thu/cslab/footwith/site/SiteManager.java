package edu.thu.cslab.footwith.site;

import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.utility.Util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 3/15/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class SiteManager {
    public SiteManager() {
    }
    public static int addSite(Site site) throws SQLException {
        assert site.getSiteName().length()<40;
//        assert !Mediator.getAllLocations().contains(site.getLocation());

        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        SQLCommand = " insert into " + tableName + " ( siteName, rate, location, brief, picture) " +
                " values ( '"+ site.getSiteName()+"' , "+ site.getRate()+ " ,'"+ site.getLocation()+ "' , " + site.getBrief()+ " , " + site.getPicture() + " ) ";
        ResultSet rs=du.executeUpdate(SQLCommand);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * get all site information
     * @param
     * @return site information vector
     * @throws java.sql.SQLException
     */
    public static Vector<Site> getAllSite() throws SQLException {
        Vector<Site> sites=new Vector<Site>();
        String SQLCommand="select * from "+tableName+";";
        ResultSet rs=DBUtil.getDBUtil().executeQuery(SQLCommand);
        while(rs.next()){
            sites.add(new Site(rs.getInt("siteID"), rs.getString("siteName"), rs.getInt("rate"),rs.getString("location"), rs.getString("brief"), rs.getInt("picture"),
                    rs.getInt("latitude"),rs.getInt("longitude")));
        }
        return sites;
    }
    public static Site seleteSite(String siteName) throws TextFormatException, SQLException {
        assert siteName.length()<=40;
        Site site;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        //if(siteName == null)
        if(Util.isEmpty(siteName))
            throw new TextFormatException("siteName is null");
        SQLCommand  = " select * from " + tableName + " where siteName = '" + siteName + "';";
        System.out.println(SQLCommand);
        rs=du.executeQuery(SQLCommand);
        //while(rs.next()){
        rs.next();
        site = new Site(rs.getInt("siteID"),rs.getString("siteName"), rs.getInt("rate"),rs.getString("location"), rs.getString("brief"),rs.getInt("picture"),
                rs.getInt("latitude"),rs.getInt("longitude")) ;
        /*
        site = new Site();
        site.setSiteID(rs.getInt("siteID"));
        site.setSiteName(rs.getString("siteName"));
        site.setRate(rs.getInt("rate"));
        site.setLocation(rs.getString("location"));
        site.setBrief(rs.getString("brief"));
        site.setPicture(rs.getInt("picture"));
        */
        //}
        return site;
    }
    public static Site seleteSite(int siteID) throws TextFormatException, SQLException {
        Site site;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        if(siteID < 0)
            throw new TextFormatException("siteID is null");
        SQLCommand  = " select * from " + tableName + " where siteID = " + siteID;
        rs=du.executeQuery(SQLCommand);
        //while(rs.next()){
        rs.next();
        site = new Site(rs.getInt("siteID"),rs.getString("siteName"), rs.getInt("rate"),rs.getString("location"), rs.getString("brief"),rs.getInt("picture"), rs.getInt("latitude"),rs.getInt("longitude")) ;
        //}
        return site;
    }

    public static String getSiteName(int siteID) throws SQLException {
        DBUtil du =DBUtil.getDBUtil();
        String SQLCommand="select siteName from " +tableName+" where siteID = "+siteID;
        ResultSet rs=du.executeQuery(SQLCommand);
        rs.next();
        return rs.getString(1);
    }

    public static HashMap<String,String> getSitePos(int siteID) throws SQLException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand="select siteName,latitude,longitude from "+ tableName+" where siteID = "+siteID;
        ResultSet rs=du.executeQuery(SQLCommand);
        rs.next();
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("siteName",rs.getString("siteName"));
        map.put("latitude",String.valueOf(rs.getInt("latitude")));
        map.put("longitude",String.valueOf(rs.getInt("longitude")));
        return map;
    }

    public static HashMap<String,String> getSite(int siteID) throws SQLException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand="select * from "+tableName+" where siteID=" +siteID;
        ResultSet rs=du.executeQuery(SQLCommand);
        HashMap<String,String> result=new HashMap<String, String>();
        rs.next();
        result.put("siteID",String.valueOf(rs.getInt("siteID")));
        result.put("siteName",rs.getString("siteName"));
        result.put("rate",String.valueOf(rs.getInt("rate")));
        result.put("location",rs.getString("location"));
        result.put("brief",rs.getString("brief"));
        result.put("pictureID",String.valueOf(rs.getInt("picture")));
        result.put("latitude",String.valueOf(rs.getInt("latitude")));
        result.put("longitude",String.valueOf(rs.getInt("longitude")));
        return result;
    }

    public static Vector<Site> selectSite(Site site) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        Vector <Site> sites=new Vector<Site>();
        boolean isAnd = false;
        if(site==null)
            throw new TextFormatException();
        SQLCommand  = " select * from " + tableName + " where ";
        //if(site.getLocation() != null){
        if(Util.isEmpty(site.getLocation())){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " location = '" + site.getLocation() + "'";
            isAnd = true;
        }
        //if(site.getBrief() != null){
        if(Util.isEmpty(site.getBrief())){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " brief = '" + site.getBrief() + "'";
            isAnd = true;

        }
        if(site.getRate()!=-1){
            if(isAnd)
                SQLCommand += " and ";
            SQLCommand += " rate = " + site.getRate();
            isAnd = true;
        }
        System.out.println(SQLCommand);
        rs=du.executeQuery(SQLCommand);
        while (rs.next()){
             sites.add(new Site(rs.getInt("siteID"), rs.getString("siteName"), rs.getInt("rate"),rs.getString("location"), rs.getString("brief"), rs.getInt("picture"), rs.getInt("latitude"),rs.getInt("longitude")));
        }
        return sites;
    }

    public static Vector<String> selectSiteWithLocation(String location) throws SQLException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand = "select siteID,siteName from site where location = '"+location+"';";
        Vector<String> result=new Vector<String>();
        ResultSet rs=du.executeQuery(SQLCommand);
        while (rs.next()){
            result.add(String.valueOf(rs.getInt("siteID"))+":"+rs.getString("siteName"));
        }
        return result;
    }

    public static void deleteSite(String siteName) throws TextFormatException, SQLException {
        Site site=new Site();
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        //if(siteName == null)
        if(Util.isEmpty(siteName))
            throw new TextFormatException("siteName is null");
        SQLCommand  = " delete from " + tableName + " where siteName = '" + siteName + "'";
        du.executeUpdate(SQLCommand);

    }
    public static void deleteSite(int siteID) throws TextFormatException, SQLException {
        Site site=new Site();
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        if(siteID < 0)
            throw new TextFormatException("siteID is null");
        SQLCommand  = " delete from " + tableName + " where siteID = " + siteID;
        du.executeUpdate(SQLCommand);
    }

    public static void editSite(String siteName, Site new_site) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        //if(siteName == null)
        if(Util.isEmpty(siteName))
            throw new TextFormatException("siteName is null");
        SQLCommand  = " update " + tableName + " set ";
        //if(new_site.getLocation() != null){
        if(!Util.isEmpty(new_site.getLocation())){
            SQLCommand += " location = '" + new_site.getLocation()+"'";
            isComma = true;
        }
        //if(new_site.getBrief() != null){
        if(!Util.isEmpty(new_site.getBrief())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " brief = '" + new_site.getBrief() + "'";
            isComma = true;

        }
        if(new_site.getRate()!=-1){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " rate = " + new_site.getRate();
            isComma = true;
        }
        if(new_site.getPicture() != -1){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " picture = '" + new_site.getPicture() +"'";
            isComma = true;
        }

        SQLCommand += " where siteName = '" + siteName +"'";
        du.executeUpdate(SQLCommand);

    }
    public static void editSite(int siteID, Site new_site) throws TextFormatException, SQLException {
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        boolean isComma = false;
        if(siteID < 0)
            throw new TextFormatException("siteName is null");
        SQLCommand  = " update " + tableName + " set ";
        //if(new_site.getLocation() != null){
        if(!Util.isEmpty(new_site.getLocation())){
            SQLCommand += " location = '" + new_site.getLocation() + "'";
            isComma = true;
        }
        //if(new_site.getBrief() != null){
        if(!Util.isEmpty(new_site.getBrief())){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " brief = '" + new_site.getBrief() + "'";
            isComma = true;

        }
        if(new_site.getRate()!=-1){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " rate = " + new_site.getRate();
            isComma = true;
        }
        if(new_site.getPicture() != -1){
            if(isComma)
                SQLCommand += " , ";
            SQLCommand += " picture = '" + new_site.getPicture() + "'";
            isComma = true;
        }

        SQLCommand += " where siteID = " + siteID;
        du.executeUpdate(SQLCommand);

    }

    private static final String tableName ="site";
}
