package edu.thu.cslab.footwith.server;
/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */

import edu.thu.cslab.footwith.utility.Util;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    public User(){
        this.userID = -1;
        nickName = "";
        passwd = "";
        otherInfo = -1;
        plans = null;
        records = null;

    }
    public User(int userID){
        this.userID = userID;
        nickName = "";
        passwd = "";
        otherInfo = -1;
        plans = "";
        records = "";

    }

    public User(String userName, String nickName, String passwd, int otherInfo, String plans, String records) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.userID = -1;
        this.userName = userName;
        this.nickName = nickName;
        this.passwd = convertToMD5(passwd);
        this.otherInfo = otherInfo;
        this.plans = plans;
        this.records = records;
        this.state = state;
    }

    public User(int userID, String userName, String nickName, String passwd, int otherInfo, String plans, String records) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.userID = userID;
        this.userName = userName;
        this.nickName = nickName;
        this.passwd = convertToMD5(passwd);
        this.otherInfo = otherInfo;
        this.plans = plans;
        this.records = records;
        this.state = state;
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
    public void setState(int state) throws TextFormatException {

        this.state = state;
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



    public boolean checkPasswd(String in_passwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String in_passwd_md5 = convertToMD5(in_passwd);
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

    private int state;

    public enum StateType{PLAN, MOVE, DONE};
}
