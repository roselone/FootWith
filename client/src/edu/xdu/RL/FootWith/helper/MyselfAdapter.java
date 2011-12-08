package edu.xdu.RL.FootWith.helper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyselfAdapter extends BaseAdapter{

	private ArrayList<HashMap<String, Object>> selfList=new ArrayList<HashMap<String,Object>>();
	private Context context;
	private LayoutInflater mInflater;
	
	public MyselfAdapter(Context context,ArrayList<HashMap<String, Object>> map){
		this.selfList=map;
		this.context=context;
		this.mInflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return selfList.size();
	}

	@Override
	public HashMap<String, Object> getItem(int position) {
		// TODO Auto-generated method stub
		return selfList.get(position);
	}

	@Override
	public long getItemId(int id) {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		HashMap<String, Object> map=selfList.get(position);

		String type=(String)map.get("itemType");
		String name=(String)map.get("itemName");
		
		if (type.equals("newTrip")){
			view=mInflater.inflate(R.layout.aboutme_listitem2, null);
			TextView itemNameTextView=(TextView)view.findViewById(R.id.aboutme_itemSystem);
			itemNameTextView.setText(name);		
		}
		if (type.equals("trip")){
			view=mInflater.inflate(R.layout.aboutme_listitem, null);
			String itemPlace=(String)map.get("itemPlace");
			String itemTimeFrom=(String)map.get("itemTimeFrom");
			String itemTimeTo=(String)map.get("itemTimeTo");
			String itemWant=(String)map.get("itemWant");
			String itemMore=(String)map.get("itemMore");
			String itemState=(String)map.get("itemState");
			String itemAttention=(String)map.get("itemAttention");
			String itemJoin=(String)map.get("itemJoin");
			String itemTime=itemTimeFrom.equals(itemTimeTo)?itemTimeFrom+"月份期间":itemTimeFrom+"到"+itemTimeTo+"月份期间";
			
			ImageView imageView=(ImageView)view.findViewById(R.id.aboutme_itemImage);
			imageView.setImageResource(R.drawable.me);
			
			TextView itemNameTextView=(TextView)view.findViewById(R.id.aboutme_itemName);
			itemNameTextView.setText(itemPlace+name);
			
			TextView itemPlaceTextView=(TextView)view.findViewById(R.id.aboutme_itemPlaceCon);
			itemPlaceTextView.setText(itemPlace);
			
			TextView itemTimeTextView=(TextView)view.findViewById(R.id.aboutme_itemTimeCon);
			itemTimeTextView.setText(itemTime);
			
			TextView itemPeopleTextView=(TextView)view.findViewById(R.id.aboutme_itemPeopleCon);
			itemPeopleTextView.setText(itemWant);
			
			TextView itemMoreTextView=(TextView)view.findViewById(R.id.aboutme_itemMoreCon);
			itemMoreTextView.setText(itemMore);
			
			TextView itemStateTextView=(TextView)view.findViewById(R.id.aboutme_itemStateCon);
			itemStateTextView.setText(itemState);
			
			TextView itemAttentionTextView=(TextView)view.findViewById(R.id.aboutme_attentionPeople);
			itemAttentionTextView.setText("关注的人: "+itemAttention);
			
			TextView itemJoinTextView=(TextView)view.findViewById(R.id.aboutme_joinPeople);
			itemJoinTextView.setText("加入的人: "+itemJoin);
			
		}
		
		
		return view;
	}

}
