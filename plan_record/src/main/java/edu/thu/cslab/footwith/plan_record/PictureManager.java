package edu.thu.cslab.footwith.plan_record;

import edu.thu.cslab.footwith.dao.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 1:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class PictureManager {
    private final String tableName = "picture";

    /**
     * add picture
     * @param picture
     * @return picture ID
     * @throws java.sql.SQLException
     */
    public int addPicture(Picture picture) throws SQLException {
        String SQLComment = "insert into " + tableName + " ( userID, title, picturePath, time ) values (" + picture.getUserID()
                + " , '" + picture.getTitle() + "' , '" + picture.getDate() + "')" ;
        DBUtil du= DBUtil.getDBUtil();
        ResultSet rs=du.executeUpdate(SQLComment);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * edit picture
     * @param pictureID
     * @param picture
     * @throws java.sql.SQLException
     */
    public void editPicture(int pictureID,Picture picture) throws  SQLException {
        String SQLComment = "update " + tableName + " set ";
        boolean flag=false;
        if (picture.getTitle()!=null) { flag=true; SQLComment += "title = " + picture.getTitle();}
        if (picture.getPicturePath() !=null) { flag=true; SQLComment += " picturePath = " + picture.getPicturePath(); }
        if (flag){
            SQLComment+=";";
            DBUtil.getDBUtil().executeUpdate(SQLComment);
        }
    }

    /**
     * delete picture
     * @param pictureID
     * @throws java.sql.SQLException
     */
    public void deletePicture(int pictureID) throws SQLException {
        String SQLComment = "delete from " + tableName + " where pictureID=" + String.valueOf(pictureID) + ";";
        DBUtil.getDBUtil().executeUpdate(SQLComment);
    }

    /**
     * select picture
     * @param pictureID
     * @return
     * @throws java.sql.SQLException
     */
    public Picture selectPicture(int pictureID) throws SQLException {
        String SQLComment = "select * from " + tableName + " where pictureID = " +String.valueOf(pictureID)+";";
        ResultSet rs = DBUtil.getDBUtil().executeQuery(SQLComment);
        rs.next();
        Picture result=new Picture(rs.getInt("userID"),rs.getString("title"),rs.getString("picturePath"),rs.getDate("time"));
        result.setPictureID(pictureID);
        return result;
    }

}
