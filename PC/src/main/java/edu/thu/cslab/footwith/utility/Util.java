package edu.thu.cslab.footwith.utility;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/31/13
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    static public boolean isEmpty(String s){
        return (s == null || s.length()==0 || s.trim().equals("") || s.trim().equals("null") || s.trim().equals("NULL"));
    }
}
