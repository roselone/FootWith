/**
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
        userID = null;
        nickName = null;
        passwd = null;
        otherInfo = 0;
        plans = null;
        records = null;

    }

    public int getState() throws Exception {
        return state;
    }
    public String getPasswd() {
        return passwd;
    }
    public String getUserID() throws Exception {

        return userID;
    }

    public String getNickName() throws Exception {
        return nickName;
    }

    public int getOtherInfo() throws Exception {
        return otherInfo;
    }

    public String getPlans() throws Exception {
        return plans;
    }

    public String getRecords() throws Exception {
        return records;
    }
    public void setState(int state) throws Exception {

        this.state = state;
    }

    public void setUserID(String userID) throws Exception {
        if(userID  == null || userID.length() == 0)
            throw new Exception("UserID");
        this.userID = userID;
    }

    public void setNickName(String nickName) throws Exception {
        if(nickName  == null || nickName.length() == 0)
            throw new Exception("NickName");
        this.nickName = nickName;
    }

    public void setOtherInfo(int otherInfo) throws Exception {

        this.otherInfo = otherInfo;
    }

    public void setPlans(String plans) throws Exception {
        if(plans  == null )
            throw new Exception("Plans");
        this.plans = plans;
    }

    public void setRecords(String records) throws Exception {
        if(records  == null )
            throw new Exception("Records");
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



    public boolean  setPasswd(String new_passwd) throws Exception {
        MessageDigest messageDigest=null;
        if(new_passwd.length() <= 8){
            throw new Exception("Passwd");
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
    private String userID;
    private String nickName;
    private String passwd;
    private int otherInfo;
    private String plans;
    private String records;

    private int state;

    public enum StateType{PLAN, MOVE, DONE};
}
