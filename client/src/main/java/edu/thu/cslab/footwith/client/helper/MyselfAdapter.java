package edu.thu.cslab.footwith.client.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.thu.cslab.footwith.client.R;

import java.util.ArrayList;
import java.util.HashMap;

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
		final HashMap<String, Object> map=selfList.get(position);

		String type=(String)map.get("itemType");
		final String name=(String)map.get("itemName");
		final int pos=position;
		
		if (type.equals("newTrip")){
			view=mInflater.inflate(R.layout.aboutme_listitem2, null);
			TextView itemNameTextView=(TextView)view.findViewById(R.id.aboutme_itemSystem);
			itemNameTextView.setText(name);		
		}
		if (type.equals("trip")){
			view=mInflater.inflate(R.layout.aboutme_listitem, null);
			
			Button stateButton=(Button)view.findViewById(R.id.aboutme_buttonState);
			stateButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					map.put("itemState", "旅途中");
					selfList.set(pos, map);
					notifyDataSetChanged();
				}
			});
			
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

        else if (type.equals("record")){

            view=mInflater.inflate(R.layout.record_listitem, null);
            //view=mInflater.inflate(R.layout.aboutme_listitem, null);

            String itemName=(String)map.get("itemName");
            String itemPlace=(String)map.get("itemPlace");
            String itemTimeFrom=(String)map.get("itemTimeFrom");
            String itemState=(String)map.get("itemState");
            String itemParticipates=(String)map.get("itemParticipates");


            ImageView imageView=(ImageView)view.findViewById(R.id.record_itemimage);
            imageView.setImageResource(R.drawable.me);

            TextView itemNameTextView=(TextView)view.findViewById(R.id.record_listitem_title);
            itemNameTextView.setText(itemName);

            TextView itemPlaceTextView=(TextView)view.findViewById(R.id.record_listitem_sites_con);
            itemPlaceTextView.setText(itemPlace);

            TextView itemTimeTextView=(TextView)view.findViewById(R.id.record_listitem_starttime_con);
            itemTimeTextView.setText(itemTimeFrom);

            TextView itemParticipatesTextView=(TextView)view.findViewById(R.id.record_listitem_users_con);
            itemParticipatesTextView.setText(itemParticipates);

            TextView itemStateTextView=(TextView)view.findViewById(R.id.record_listitem_status_con);
            itemStateTextView.setText(itemState);


        }

		
		return view;
	}

}
