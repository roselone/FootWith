package edu.thu.cslab.footwith.messenger;

import edu.thu.cslab.footwith.utility.Util;
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

    public static final JSONHelper JSONHelperInstance=new JSONHelper();

    /**
     *
     * @return jsonhelper instance
     */
    public static JSONHelper getJSONHelperInstance(){
        return JSONHelperInstance;
    }

    /**
     * convert vector to json array
     * @param parts
     * @return
     */
    public String convertToString(Vector<Integer> parts){
        JSONArray array=new JSONArray();
        for (int i=0;i<parts.size();i++){
            array.put(parts.get(i));
        }
        return array.toString();
    }

    /**
     * convert json array to vector
     * @param s             /home/roselone/Git/FootWith/messenger
     * @return vector
     * @throws JSONException
     */
    public Vector<Integer> convertToArray(String s) throws JSONException {
        if (Util.isEmpty(s)) return null;
        JSONArray array=new JSONArray(s);
        Vector<Integer> result=new Vector<Integer>();
        for (int i=0;i<array.length();i++) result.add(array.getInt(i));
        return result;
    }

    /**
     * add one int to json array
     * @param s
     * @param one
     * @return json array
     * @throws JSONException
     */
    public String addToArray(String s, int one) throws JSONException {
        Vector<Integer> tmp;
        if (Util.isEmpty(s)) {
            tmp=new Vector<Integer>();
        }else{
            tmp=convertToArray(s);
        }
        tmp.add(one);
        return convertToString(tmp);
    }

    /**
     * delete one from json array
     * @param s
     * @param one
     * @return json array
     * @throws JSONException
     */
    public String deleteFromArray(String s, int one) throws JSONException {
        if (Util.isEmpty(s)) return null;
        Vector<Integer> tmp=convertToArray(s);
        tmp.remove(tmp.indexOf(one));
        return convertToString(tmp);
    }

    /**
     * whether json array contains the one
     * @param s
     * @param one
     * @return isContained
     * @throws JSONException
     */
    public boolean isContained(String s,int one) throws JSONException {
        if (Util.isEmpty(s)) return false;
        Vector<Integer> tmp=convertToArray(s);
        return tmp.contains(one);
    }

}
