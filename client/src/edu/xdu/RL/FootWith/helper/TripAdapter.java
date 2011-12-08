package edu.xdu.RL.FootWith.helper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.R;
import edu.xdu.RL.FootWith.R.layout;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TripAdapter extends BaseAdapter{
	
	private ArrayList<HashMap<String, Object>> tripArrayList=new ArrayList<HashMap<String,Object>>();
	private Context context;
	private LayoutInflater mInflater;
	
	public TripAdapter(Context context,ArrayList<HashMap<String, Object>> list){
		this.context=context;
		this.tripArrayList=list;
		this.mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tripArrayList.size();
	}

	@Override
	public HashMap<String, Object> getItem(int position) {
		// TODO Auto-generated method stub
		return tripArrayList.get(position);
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
		HashMap<String, Object> map=tripArrayList.get(position);

		String type=(String)map.get("itemType");
		
		String name=(String)map.get("itemName");
		
		if (type.equals("trip")){
			String sex=(String)map.get("sex");
			String subname=(String)map.get("itemSubName");
			String content=(String)map.get("itemContent");
			
			view=mInflater.inflate(R.layout.home_listitem, null);
			
			ImageView image=(ImageView)view.findViewById(R.id.itemImage);
			image.setImageResource(R.drawable.me);
			
			TextView contentTextView=(TextView)view.findViewById(R.id.itemContent);
			contentTextView.setText(content);
			
			TextView nameTextView=(TextView)view.findViewById(R.id.itemName);
			nameTextView.setText(name);
			
			TextView subnameTextView=(TextView)view.findViewById(R.id.itemSubName);
			subnameTextView.setText(subname);
			
			if (sex.equals("male")){
				nameTextView.setTextColor(0xFF1E90FF);
			}
			if (sex.equals("female")){
				nameTextView.setTextColor(0xFFFF69B4);
			}
		}
		if (type.equals("footWith")){
			view=mInflater.inflate(R.layout.home_listitem2, null);
			String content=(String)map.get("itemContent");
			TextView titleTextView=(TextView)view.findViewById(R.id.homeItemBeginTripTitle);
			titleTextView.setText(name+"开始旅行...");
			TextView conTextView=(TextView)view.findViewById(R.id.homeItemBeginTripCon);
			if (content!=null){
				conTextView.setText(content);
			}else{
				conTextView.setHeight(0);
			}
			
		}
		if (type.equals("systemInfo")){
			view=mInflater.inflate(R.layout.home_listitem2, null);
			TextView titleTextView=(TextView)view.findViewById(R.id.homeItemBeginTripTitle);
			titleTextView.setText(name);
			TextView conTextView=(TextView)view.findViewById(R.id.homeItemBeginTripCon);
			conTextView.setHeight(0);
		}

		
		return view;
		
	}
	

}
