import edu.thu.cslab.footwith.utility.Constant;
import edu.thu.cslab.footwith.server.SiteManager;
import edu.thu.cslab.footwith.server.Site;
import edu.thu.cslab.footwith.server.ImportData;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/15/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImportDataTest {
    private String projectPath=Constant.getInstantce().getProperty("PROJECT_PATH");
//    @Test
//    public void import4A() throws IOException, SQLException {
//        String fileName="4A.txt";
//        ImportData im=new ImportData();
//        im.importSite(projectPath + "/data/" + fileName);
//    }
//    @Test
//    public void import5A()throws IOException, SQLException {
//        String fileName="5A.txt";
//        ImportData im=new ImportData();
//        im.importSite2(projectPath + "/data/" + fileName);
//    }
//    @Test
//    public void importTest(){
//        Site site=new Site("中文1","尼玛",3);
//        SiteManager sm=new SiteManager();
//        try {
//            sm.addSite(site);
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
    @Test
    public void readSite() throws SQLException {
        SiteManager sm=new SiteManager();
        Vector<Site> sites=sm.getAllSite();
        for (int i=0;i<sites.size();i++){
            System.out.println(sites.elementAt(i).getSiteName());
        }
    }

}
