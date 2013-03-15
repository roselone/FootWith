package edu.thu.cslab.footwith.server;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
    public void addSite(Site site) throws SQLException {
        String SQLCommand = null;
        DBUtil du = DBUtil.getDBUtil();
        SQLCommand = " insert into " + tableName + " ( siteName, rate, location, brief, picture) " +
                " values ( "+ site.getSiteName()+" , "+ site.getRate()+ " , "+ site.getLocation()+ " , " + site.getBrief()+ " , " + site.getPicture() + " ) ";
        du.executeUpdate(SQLCommand);
    }
    public Site seleteSite(String siteName) throws TextFormatException, SQLException {
        Site site;
        DBUtil du = DBUtil.getDBUtil();
        String SQLCommand = null;
        ResultSet rs;
        ResultSetMetaData rsmd;
        if(siteName == null)
            throw new TextFormatException("siteName is null");
        SQLCommand  = " select * from " + tableName + "where siteName is " + siteName;
        rs=du.executeQuery(SQLCommand);
        //while(rs.next()){
            rsmd = rs.getMetaData();
            site = new Site(rs.getInt("siteID"));
            site.setSiteName(rs.getString("siteName"));
            //site.setNickName(rs.getString("nickName"));
            //site.setPasswd(rs.getString("passwd"));
            //site.setOtherInfo(rs.getInt("otherInfo"));
            //site.setPlans(rs.getString("plans"));
            //site.setRecords(rs.getString("records"));
        //}

        return site;
    }
    private final String tableName ="site";
}
