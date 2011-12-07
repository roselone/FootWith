package edu.xdu.RL.FootWith.helper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.R;

public class Trip {
	private ArrayList<HashMap<String, Object>> tripList=new ArrayList<HashMap<String,Object>>();
	
	public void addTrip(HashMap<String, Object> map){
		tripList.add(map);
	}
	
	public ArrayList<HashMap<String, Object>> getTrips(){
		return tripList;
	}
	
	public void requestTrip(){
    	HashMap<String, Object> map=new HashMap<String, Object>();
    	map.put("itemName", "波波");
    	map.put("sex", "male");
    	map.put("itemSubName", "求勾搭^^");
    	map.put("itemContent", "我是来自广东的波波，今年夏天想去西安玩！\n想要找一个帅哥同去");
    	addTrip(map);
    	HashMap<String, Object> map2=new HashMap<String, Object>();
    	map2.put("itemName", "翔子");
    	map2.put("sex", "female");
    	map2.put("itemSubName", "求勾搭^^");
    	map2.put("itemContent", "我是来自广东的波波，今年夏天想去西安玩！\n想要找一个帅哥同去");
    	addTrip(map2);
    	HashMap<String, Object> map3=new HashMap<String, Object>();
    	map3.put("itemName", "翔子和波波同学");
    	map3.put("sex", "none");
    	addTrip(map3);
    	HashMap<String, Object> map4=new HashMap<String, Object>();
    	map4.put("itemName", "刘佳");
    	map4.put("sex", "male");
    	map4.put("itemSubName", "求勾搭^^");
    	map4.put("itemContent", "我是来自安徽的佳儿，今年1月份到4月份期间想去北京玩！\n想要找多点妹子同去，有妹子在么？");
    	addTrip(map4);
	}
}
