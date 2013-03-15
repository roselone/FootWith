package edu.thu.cslab.footwith; /**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-12
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    public User(){
        this.userID = -1;
        nickName = null;
        passwd = null;
        otherInfo = -1;
        plans = null;
        records = null;

    }
    public User(int userID){
        this.userID = userID;
        nickName = null;
        passwd = null;
        otherInfo = -1;
        plans = null;
        records = null;

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
        if(userName  == null || userName.length() == 0 || userName.length()>32)
            throw new TextFormatException("UserName");
        this.userName = userName;
    }

    public void setNickName(String nickName) throws TextFormatException {
        if(nickName  == null || nickName.length() == 0 || nickName.length()>32)
            throw new TextFormatException("NickName");
        this.nickName = nickName;
    }

    public void setOtherInfo(int otherInfo) throws TextFormatException {

        this.otherInfo = otherInfo;
    }

    public void setPlans(String plans) throws TextFormatException {
        if(plans  == null || plans.length()>80)
            throw new TextFormatException("Plans");
        this.plans = plans;
    }

    public void setRecords(String records) throws TextFormatException {
        if(records  == null || plans.length()>80)
            throw new TextFormatException("Records");
        this.records = records;
    }



    public boolean checkPasswd(String in_passwd) {
        MessageDigest messageDigest=null;
        String in_passwd_md5;
        try{
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(passwd.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e){
            System.out.println("No Such Algorithm Exception caught!");
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        byte [] byteArray = messageDigest.digest();
        in_passwd_md5 = byteArray.toString();
        if(passwd.equals(in_passwd_md5))
            return true;
        else
            return false;
    }



    public boolean  setPasswd(String new_passwd) throws TextFormatException {
        MessageDigest messageDigest=null;
        if(new_passwd.length() < 8 || new_passwd.length() > 30){
            throw new TextFormatException("Passwd");
        }
        try{
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(passwd.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e){
            System.out.println("No Such Algorithm Exception caught!");
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        byte [] byteArray = messageDigest.digest();
        passwd = byteArray.toString();
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
