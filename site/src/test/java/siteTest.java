import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.site.SiteManager;
import org.junit.Test;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/9/13
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class siteTest {
//    @Test
//    public void getSiteNameTest() throws SQLException, TextFormatException {
//        int id=2;
//        System.out.println(SiteManager.seleteSite(2).getLocation());
//    }
//    @Test
//    public void getSiteNameWithLocationTest() throws SQLException {
//        String location="北京";
//        System.out.println(SiteManager.selectSiteWithLocation(location).toString());
//    }
//    @Test
//    public void getSiteTest() throws SQLException {
//        int id=2;
//        System.out.println(SiteManager.getSite(id).toString());
//    }
//    @Test
//    public void getSiteIDName() throws SQLException, FileNotFoundException {
//        String SQLCommand="select siteID, siteName, location from site;";
//        ResultSet rs= DBUtil.getDBUtil().executeQuery(SQLCommand);
//        PrintWriter out=new PrintWriter(new FileOutputStream("/home/roselone/siteName.txt"));
//
//        while (rs.next()){
//            out.print(rs.getInt("siteID"));
//            out.println(" "+rs.getString("siteName")+" "+rs.getString("location"));
//        }
//        out.close();
//    }
//    @Test
//    public void importData() throws IOException, SQLException {
//        String fileName="data";
//        FileReader f=new FileReader(fileName);
//        BufferedReader bufferedReader=new BufferedReader(f);
//        String id;
//        String latitude;
//        String longitude;
//        String tmp;
//        DBUtil du=DBUtil.getDBUtil();
//        while ((tmp=bufferedReader.readLine())!=null){
//            String [] siteInfo=tmp.split(" ");
//            id=siteInfo[0];
//            latitude=siteInfo[1];
//            longitude=siteInfo[2];
//            String SQL="update set latitude = '"+latitude+"', longitude ='"+longitude+"' where siteID="+id;
//            du.executeUpdate(SQL);
//        }
//
//    }
}
