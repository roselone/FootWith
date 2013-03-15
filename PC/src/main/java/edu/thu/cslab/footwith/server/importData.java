package edu.thu.cslab.footwith.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/15/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class importData {
    private FileReader f;
    public importData(){}

    public void openFile(String fileName) throws FileNotFoundException {
        f=new FileReader(fileName);
        return ;
    }

    public void importSite(String fileName) throws IOException, SQLException {
        openFile(fileName);
        BufferedReader bufferedReader=new BufferedReader(f);
        String tmp = null;
        Site site=null;
        SiteManager siteManager=new SiteManager();
        while ((tmp = bufferedReader.readLine()) != null){
            String[] siteInfo = tmp.split(" ");
            site=new Site(siteInfo[0],siteInfo[1],Integer.valueOf(siteInfo[2].charAt(0)-'0'));
            siteManager.addSite(site);
        }
    }

    public void importSite2(String fileName) throws IOException{
        openFile(fileName);
        BufferedReader bufferedReader=new BufferedReader(f);
        String tmp=null;
        Site site=null;
        SiteManager siteManager=new SiteManager();
        while ((tmp = bufferedReader.readLine()) != null){
            String[] tmpInfo=tmp.split(" ");
            String location = tmpInfo[0];
            for (int i=1;i<tmpInfo.length;i++){
                site=new Site(tmpInfo[i],location,5);
                try {
                    siteManager.addSite(site);
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

}
