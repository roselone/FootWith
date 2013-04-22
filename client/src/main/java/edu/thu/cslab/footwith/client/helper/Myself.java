package edu.thu.cslab.footwith.client.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class Myself {
	private ArrayList<HashMap<String, Object>> selfInfoList=new ArrayList<HashMap<String,Object>>();

	public void add(HashMap<String, Object> map){
		selfInfoList.add(map);
		return;
	}
	public ArrayList<HashMap<String, Object>> getList(){
		return selfInfoList;
	}
	public void modify(int position,HashMap<String, Object> map){
		selfInfoList.set(position, map);
	}
	public void requestList(){
		HashMap<String, Object> map0=new HashMap<String, Object>();
		map0.put("itemType", "newTrip");
		map0.put("itemName", "< 点我创建新的旅程 >");
		selfInfoList.add(map0);
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("itemType", "trip");
		map1.put("itemName", "之行计划");
		map1.put("itemPlace", "北京");
		map1.put("itemTimeFrom", "1");
		map1.put("itemTimeTo", "2");
		map1.put("itemState", "招募中");
		map1.put("itemWant", "想要找家在西安的大学生同去，最好能带上一个朋友");
		map1.put("itemMore", "如果方便的话，选择飞机");
		map1.put("itemAttention", "刘佳、毛一毛");
		map1.put("itemJoin", "韩梅梅");
		selfInfoList.add(map1);
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("itemType", "trip");
		map2.put("itemName", "之行计划");
		map2.put("itemPlace", "上海");
		map2.put("itemTimeFrom", "2");
		map2.put("itemTimeTo", "4");
		map2.put("itemState", "招募中");
		map2.put("itemWant", "想要找家在西安的大学生同去，最好能带上一个朋友");
		map2.put("itemMore", "如果方便的话，选择飞机");
		map2.put("itemAttention", "刘佳、毛一毛");
		map2.put("itemJoin", "韩梅梅");
		selfInfoList.add(map2);
	}
	
}
