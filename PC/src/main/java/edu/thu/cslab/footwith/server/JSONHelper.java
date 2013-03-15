package edu.thu.cslab.footwith.server;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class JSONHelper {
    public String convertToString(int[] parts){
        String result=null;
        JSONArray array=new JSONArray();
        for (int i=0;i<parts.length;i++){
            array.put(parts[i]);
        }
        return array.toString();
    }

    public Vector<Integer> convertToArray(String s) throws JSONException {
        JSONArray array=new JSONArray(s);
        Vector<Integer> result=new Vector<Integer>();
        for (int i=0;i<array.length();i++) result.add(array.getInt(i));
        return result;
    }

}
