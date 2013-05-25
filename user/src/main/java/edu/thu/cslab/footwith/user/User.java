package edu.thu.cslab.footwith.user;
/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private Logger logger=LogManager.getLogger(this.getClass().getName());
    public User(){
        this.userID = -1;
        nickName = "";
        passwd = "";
        otherInfo = -1;
        plans = null;
        records = null;
        like=null;
        marks=null;

    }
    public User(int userID){
        this.userID = userID;
        nickName = "";
        passwd = "";
        otherInfo = -1;
        plans = "";
        records = "";
        like="";
        marks="";
    }

    public User(String userName, String nickName, String passwd, int otherInfo, String plans, String records) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.userID = -1;
        this.userName = userName;
        this.nickName = nickName;
        this.passwd = convertToMD5(passwd);
        this.otherInfo = otherInfo;
        this.plans = plans;
        this.records = records;
    }

    public User(int userID, String userName, String nickName, String passwd, int otherInfo, String plans, String records,
                boolean sex,String like,String marks,String sinaWeiboToken,String sinaExpiresIN) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.userID = userID;
        this.userName = userName;
        this.nickName = nickName;
        this.passwd = convertToMD5(passwd);
        this.otherInfo = otherInfo;
        this.plans = plans;
        this.records = records;
        this.sex=sex;
        this.like=like;
        this.marks=marks;
        this.sinaWeiboToken=sinaWeiboToken;
        this.sinaExpiresIN=sinaExpiresIN;
    }

    public int getState(){
        return state;
    }
    public String getPasswd() {
        return passwd;
    }
    public int getUserID(){
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName(){
        return nickName;
    }

    public int getOtherInfo(){
        return otherInfo;
    }

    public String getPlans(){
        return plans;
    }

    public String getRecords(){
        return records;
    }

    public boolean getSex(){
        return sex;
    }

    public String getLike(){
        return like;
    }

    public String getMarks(){
        return marks;
    }

    public String getSinaWeiboToken(){
        return sinaWeiboToken;
    }

    public String getSinaExpiresIN(){
        return sinaExpiresIN;
    }

    public void setSinaExpiresIN(String in){
        this.sinaExpiresIN=in;
    }


    public void setSinaWeiboToken(String token){
        this.sinaWeiboToken=token;
    }

    public void setState(int state) throws TextFormatException {

        this.state = state;
    }

    public void setUserID(int userID) {
        this.userID=userID;
    }

    public void setUserName(String userName) throws TextFormatException{
        if(Util.isEmpty(userName) || userName.length() == 0 || userName.length()>32)
            throw new TextFormatException("UserName");
        this.userName = userName;
    }

    public void setNickName(String nickName) throws TextFormatException {
        if(Util.isEmpty(nickName) || nickName.length() == 0 || nickName.length()>32)
            throw new TextFormatException("NickName");
        this.nickName = nickName;
    }

    public void setOtherInfo(int otherInfo) throws TextFormatException {
        this.otherInfo = otherInfo;
    }

    public void setPlans(String plans) throws TextFormatException {
        if(!Util.isEmpty(plans) && plans.length()>80)
            throw new TextFormatException("Plans");
        this.plans = plans;
    }

    public void setRecords(String records) throws TextFormatException {
        if(!Util.isEmpty(records) && records.length()>80)
            throw new TextFormatException("Records");
        this.records = records;
    }

    public void setLike(String like) throws TextFormatException {
        if (!Util.isEmpty(like) && like.length()>200)
            throw new TextFormatException("like");
        this.like=like;
    }

    public void setMarks(String marks) throws TextFormatException {
        if (!Util.isEmpty(marks) && marks.length()>200)
            throw new TextFormatException("marks");
        this.marks=marks;
    }

    public void setSex(boolean sex){
        this.sex=sex;
    }



    public boolean checkPasswd(String in_passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String in_passwd_md5 = convertToMD5(in_passwd);
        logger.debug("passwd in MD5:{}",in_passwd_md5);
        if(this.passwd.equals(in_passwd_md5))
            return true;
        else
            return false;
    }

    public String convertToMD5(String s) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String rs=base64en.encode(messageDigest.digest(s.getBytes("UTF-8")));
        return rs;
    }

    public boolean  setPasswd(String passwd) throws TextFormatException {
        if(passwd.length() < 8 || passwd.length() > 30){
            throw new TextFormatException("Passwd");
        }
        this.passwd = passwd;
        return true;

    }
    private int userID;
    private String userName;
    private String nickName;
    private String passwd;
    private int otherInfo;
    private String plans;
    private String records;
    private String like;
    private String marks;
    private boolean sex;
    private String sinaWeiboToken;
    private String sinaExpiresIN;

    private int state;

    public enum StateType{PLAN, MOVE, DONE};
}
