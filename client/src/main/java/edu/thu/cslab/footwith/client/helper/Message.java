package edu.thu.cslab.footwith.client.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class Message {
	private ArrayList<HashMap<String, Object>> messageList=new ArrayList<HashMap<String,Object>>();
	
	public void addMessage(HashMap<String, Object> map){
		messageList.add(map);
		return ;
	}
	
	public ArrayList<HashMap<String, Object>> getMessages(){
		return messageList;
	}
	
	public void modifyMessage(int position,HashMap<String, Object> map){
		messageList.set(position, map);
	}
	
	public void requestMessage(){
		HashMap<String, Object> map0=new HashMap<String, Object>();
		map0.put("itemType", "systemMessage");
		map0.put("itemName","欢迎加入旅伴");
		messageList.add(map0);
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("itemName", "刘佳");
		map1.put("sex", "male");
		map1.put("itemType", "contactMessage");
		map1.put("itemContent", "刘佳:你好！我也在西安，想和你一块去，可以不？\n波波:你想什么时候去？\n刘佳:最好是下个月，那个时候宝鸡很漂亮！");
		messageList.add(map1);
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("itemType", "invitationMessage");
		map2.put("itemName","波波邀请您加入旅程～");
		map2.put("itemState", "unconfirmed");
		messageList.add(map2);
		HashMap<String, Object> map3=new HashMap<String, Object>();
		map3.put("itemType", "replyMessage");
		map3.put("sex", "female");
		map3.put("itemName","韩梅梅");
		map3.put("itemContent", "波波:我也喜欢西藏，能和你一块去么？\n韩梅梅:好呀");
		messageList.add(map3);
	}
	
}
