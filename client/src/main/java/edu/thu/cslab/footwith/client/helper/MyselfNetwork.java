package edu.thu.cslab.footwith.client.helper;

import edu.thu.cslab.footwith.client.Login;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 5/9/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyselfNetwork {
    private ArrayList<HashMap<String, String>> selfInfoList=new ArrayList<HashMap<String,String>>();
    public void add(HashMap<String, String> map){
        selfInfoList.add(map);
        return;
    }
    public ArrayList<HashMap<String, String>> getList(){
        return selfInfoList;
    }
    public void modify(int position,HashMap<String, String> map){
        selfInfoList.set(position, map);
    }
    public void requestList(){
        ServerConnector sc = new ServerConnector("planrecord");
        String result = null;
        HashMap<String, String> map0=new HashMap<String, String>();
        map0.put("itemType", "newTrip");
        map0.put("title", "< 点我创建新的计划 >");
        selfInfoList.add(map0);
        HashMap<String,String> userMap=new HashMap<String, String>();
        userMap.put("planList", Login.userPlans);
        userMap.put("recordList", Login.userRecords);
        try {
            result = sc.setRequestParam("getPlanRecord", JSONHelper.getJSONHelperInstance().convertToString(userMap)).doPost();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(!Util.isEmpty(result)){
            HashMap<String, String> result_map = JSONHelper.getJSONHelperInstance().convertToMap(result);
            if(result_map.get("state").equals("successful")){
                HashMap<String, String> planrecord = JSONHelper.getJSONHelperInstance().convertToMap(result_map.get("planrecord"));
                //TODO
                //need to sort
                Object[] keys =  planrecord.keySet().toArray();
                Arrays.sort(keys);
                HashMap<String, String> planrecord_item;
                for(Object key:keys){
                    planrecord_item = JSONHelper.getJSONHelperInstance().convertToMap(planrecord.get(key));
                    if(planrecord_item.containsKey("planID")){
                        planrecord_item.put("itemType", "plan");
                    }else if(planrecord_item.containsKey("recordID")){
                        planrecord_item.put("itemType", "record");
                    }
                    selfInfoList.add(planrecord_item);
                }
                //System.out.println(keys.toString());
                //System.out.println(selfListString.toString());
            }
        }
    }
}
