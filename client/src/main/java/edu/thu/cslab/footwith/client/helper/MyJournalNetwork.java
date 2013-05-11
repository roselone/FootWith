package edu.thu.cslab.footwith.client.helper;

import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/10/13
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyJournalNetwork {
    private ArrayList<HashMap<String, String>> journalList =new ArrayList<HashMap<String,String>>();
    private String journalIDs;
    private String recordID;
    public boolean add(HashMap<String, String> map){
        ServerConnector sc = new ServerConnector("journal");
        String result = null;
        HashMap<String, String> tmpMap = new HashMap<String, String>();
        tmpMap.put("recordID", recordID);
        tmpMap.put("journal", JSONHelper.getJSONHelperInstance().convertToString(map));
        try {
            result = sc.setRequestParam("add", JSONHelper.getJSONHelperInstance().convertToString(tmpMap)).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(!Util.isEmpty(result)){
            HashMap<String, String> result_map = JSONHelper.getJSONHelperInstance().convertToMap(result);
            if(result_map.get("state").equals("successful")){
                journalList.add(map);
                return true;
                //System.out.println(selfListString.toString());
            }else{
                return false;
            }
        }
        return false;
    }
    public ArrayList<HashMap<String, String>> getList(){
        return journalList;
    }
    public boolean modify(int position,HashMap<String, String> map){

        ServerConnector sc = new ServerConnector("journal");
        String result = null;
        HashMap<String, String> tmpMap = new HashMap<String, String>();
        tmpMap.put("journalID", map.get("journalID"));
        tmpMap.put("journal", JSONHelper.getJSONHelperInstance().convertToString(map));
        try {
            result = sc.setRequestParam("modify", JSONHelper.getJSONHelperInstance().convertToString(tmpMap)).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(!Util.isEmpty(result)){
            HashMap<String, String> result_map = JSONHelper.getJSONHelperInstance().convertToMap(result);
            if(result_map.get("state").equals("successful")){
                journalList.set(position, map);
                return true;
                //System.out.println(selfListString.toString());
            }else{
                return false;
            }
        }
        return false;
    }
    public void requestList(String journalIDs, String recordID){
        this.journalIDs = journalIDs;
        this.recordID = recordID;
        ServerConnector sc = new ServerConnector("journal");
        String result = null;

        try {
            result = sc.setRequestParam("query", journalIDs).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(!Util.isEmpty(result)){
            HashMap<String, String> result_map = JSONHelper.getJSONHelperInstance().convertToMap(result);
            if(result_map.get("state").equals("successful")){
                HashMap<String, String> journal = JSONHelper.getJSONHelperInstance().convertToMap(result_map.get("journal"));
                //TODO
                //need to sort
                Object[] keys =  journal.keySet().toArray();
                Arrays.sort(keys);
                HashMap<String, String> journal_item;
                for(Object key:keys){
                    journal_item = JSONHelper.getJSONHelperInstance().convertToMap(journal.get(key));
                    journalList.add(journal_item);
                }
                System.out.println(keys.toString());
                //System.out.println(selfListString.toString());
            }
        }
    }
}
