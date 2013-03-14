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
    public User(){}

    public int getState() {
        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getName(){
        return name;
    }
    public void setName(String new_name){
        name = new_name;
    }
    public boolean checkPasswd(String in_passwd){
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

    public String getPasswd() {
        return passwd;
    }

    public boolean  setPasswd(String new_passwd){
        MessageDigest messageDigest=null;
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
    private String name = new String();
    private String passwd = new String();
    private int state;

    public enum StateType{PLAN, MOVE, DONE};
}
