package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.dao.DBUtil;
import edu.thu.cslab.footwith.utility.Util;
import net.iharder.Base64;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 1:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class PictureManager {
    private static final String tableName = "picture";

    /**
     * add picture
     * @param pictureMap
     * @return picture ID
     * @throws java.sql.SQLException
     */
    public static int addPicture(HashMap<String,String> pictureMap) throws SQLException, IOException {
        String SQLCommand = "insert into " + tableName + " ( userID, title, picturePath, time ) values (" + pictureMap.get("userID")
                + " , '" + pictureMap.get("title") + "' , '" +pictureMap.get("pictureName") +"' , "+ pictureMap.get("time") + ")" ;
        DBUtil du= DBUtil.getDBUtil();
        ResultSet rs=du.executeUpdate(SQLCommand);
        rs.next();
        int pictureID=rs.getInt(1);
        byte[] image=Base64.decode(pictureMap.get("picture"));
        FileOutputStream outputStream=new FileOutputStream("/home/roselone/"+pictureMap.get("pictureName"));
        outputStream.write(image);
        outputStream.close();
        return pictureID;
    }

    public static HashMap<String,String> getPictureInfo(int pictureID,String path) throws SQLException, IOException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand="select * from "+tableName+" where pictureID="+pictureID;
        HashMap<String,String> pictureMap=new HashMap<String, String>();
        ResultSet rs=du.executeQuery(SQLCommand);
        rs.next();
        pictureMap.put("pictureID",String.valueOf(rs.getInt("pictureID")));
        pictureMap.put("userID",String.valueOf(rs.getInt("userID")));
        pictureMap.put("title", Util.string2Json(rs.getString("title")));
        pictureMap.put("time",String.valueOf(rs.getDate("time")));
        String imageUrl=path+"/"+rs.getString("picturePath");
        pictureMap.put("picture", Base64.encodeBytes(getPicture(imageUrl)));
        pictureMap.put("timestamp",String.valueOf(rs.getTimestamp("timestamp")));
        return pictureMap;
    }

    public static byte[] getPicture(String imageUrl) throws SQLException, IOException {
        File image=new File(imageUrl);
        FileInputStream in =new FileInputStream(image);
        byte[] buf=new byte[(int)image.length()];
        in.read(buf);
        return buf;
    }

    public static byte[] getPicture(int pictureID,String path) throws SQLException, IOException {
        DBUtil du=DBUtil.getDBUtil();
        String SQLCommand="select picturePath from "+tableName+" where pictureID="+pictureID;
        ResultSet rs=du.executeQuery(SQLCommand);
        rs.next();
        String imageUrl=path+"/"+rs.getString("picturePath");
        File image=new File(imageUrl);
        FileInputStream in =new FileInputStream(image);
        byte[] buf=new byte[(int)image.length()];
        in.read(buf);
        return buf;
    }

    /**
     * edit picture
     * @param pictureID
     * @param picture
     * @throws java.sql.SQLException
     */
    public static void editPicture(int pictureID,Picture picture) throws  SQLException {
        String SQLCommand = "update " + tableName + " set ";
        boolean flag=false;
        if (picture.getTitle()!=null) { flag=true; SQLCommand += "title = " + picture.getTitle();}
        if (picture.getPicturePath() !=null) { flag=true; SQLCommand += " picturePath = " + picture.getPicturePath(); }
        if (flag){
            SQLCommand+=";";
            DBUtil.getDBUtil().executeUpdate(SQLCommand);
        }
    }

    /**
     * delete picture
     * @param pictureID
     * @throws java.sql.SQLException
     */
    public static void deletePicture(int pictureID) throws SQLException {
        String SQLCommand = "delete from " + tableName + " where pictureID=" + String.valueOf(pictureID) + ";";
        DBUtil.getDBUtil().executeUpdate(SQLCommand);
    }

    /**
     * select picture
     * @param pictureID
     * @return
     * @throws java.sql.SQLException
     */
    public static Picture selectPicture(int pictureID) throws SQLException {
        String SQLCommand = "select * from " + tableName + " where pictureID = " +String.valueOf(pictureID)+";";
        ResultSet rs = DBUtil.getDBUtil().executeQuery(SQLCommand);
        rs.next();
        Picture result=new Picture(rs.getInt("userID"),rs.getString("title"),rs.getString("picturePath"),rs.getDate("time"),rs.getTimestamp("timestamp"));
        result.setPictureID(pictureID);
        return result;
    }

}
