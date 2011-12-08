package edu.xdu.RL.FootWith.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import edu.xdu.RL.FootWith.R;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter{
	
	private ArrayList<HashMap<String, Object>> messageList=new ArrayList<HashMap<String,Object>>();
	private Context context;
	private LayoutInflater mInflater;
	
	public MessageAdapter(Context context,ArrayList<HashMap<String, Object>> list){
		this.messageList=list;
		this.context=context;
		this.mInflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messageList.size();
	}

	@Override
	public HashMap<String, Object> getItem(int position) {
		// TODO Auto-generated method stub
		return messageList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view=null;
		HashMap<String, Object> map=messageList.get(position);
		
		
		String type=(String)map.get("itemType");
		String name=(String)map.get("itemName");
		
		if (type.equals("systemMessage")){
			view=mInflater.inflate(R.layout.messages_listitem2, null);
			TextView itemNameTextView=(TextView)view.findViewById(R.id.message_itemSystem);
			itemNameTextView.setText(name);
		}
		
		if (type.equals("invitationMessage")){
			view=mInflater.inflate(R.layout.messages_listitem2, null);
			TextView itemNameTextView=(TextView)view.findViewById(R.id.message_itemSystem);
			itemNameTextView.setText(name);
		}
		
		if (type.equals("contactMessage")){
			view=mInflater.inflate(R.layout.messages_listitem, null);
			String sex=(String)map.get("sex");
			String content=(String)map.get("itemContent");
			TextView itemNameTextView=(TextView)view.findViewById(R.id.messages_itemName);
			itemNameTextView.setText(name);
			if (sex.equals("male")){
				itemNameTextView.setTextColor(0xFF1E90FF);
			}
			if (sex.equals("female")){
				itemNameTextView.setTextColor(0xFFFF69B4);
			}
			ImageView itemImageImageView=(ImageView)view.findViewById(R.id.messages_itemImage);
			itemImageImageView.setImageResource(R.drawable.news);
			
			TextView itemContentTextView=(TextView)view.findViewById(R.id.messages_itemContent);
			itemContentTextView.setText(content);
			
		}
		
		if (type.equals("replyMessage")){
			view=mInflater.inflate(R.layout.messages_listitem, null);
			String sex=(String)map.get("sex");
			String content=(String)map.get("itemContent");
			TextView itemNameTextView=(TextView)view.findViewById(R.id.messages_itemName);
			itemNameTextView.setText(name);
			TextView itemPreNameTextView=(TextView)view.findViewById(R.id.messages_itemPreName);
			itemPreNameTextView.setText("来自");
			TextView itemSubNameTextView=(TextView)view.findViewById(R.id.messages_itemSubName);
			itemSubNameTextView.setText("的回复");
			if (sex.equals("male")){
				itemNameTextView.setTextColor(0xFF1E90FF);
			}
			if (sex.equals("female")){
				itemNameTextView.setTextColor(0xFFFF69B4);
			}
			ImageView itemImageImageView=(ImageView)view.findViewById(R.id.messages_itemImage);
			itemImageImageView.setImageResource(R.drawable.news);
			
			TextView itemContentTextView=(TextView)view.findViewById(R.id.messages_itemContent);
			itemContentTextView.setText(content);
			
		}
		
		return view;
	}

}
