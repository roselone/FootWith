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
import edu.thu.cslab.footwith.client.Login;
import edu.thu.cslab.footwith.client.R;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class MyselfAdapter extends BaseAdapter{

	private ArrayList<HashMap<String, String>> selfList=new ArrayList<HashMap<String,String>>();
    private ArrayList<HashMap<String, String>> selfListString=new ArrayList<HashMap<String,String>>();
	private Context context;
	private LayoutInflater mInflater;
	
	public MyselfAdapter(Context context,ArrayList<HashMap<String, String>> map){
		this.selfListString=map;
		this.context=context;
		this.mInflater=LayoutInflater.from(context);
	}

    public MyselfAdapter(Context context) {
        this.context = context;
        this.mInflater=LayoutInflater.from(context);
        ServerConnector sc = new ServerConnector("planrecord");
        String result = null;
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
                    selfListString.add(planrecord_item);
                }
                //System.out.println(keys.toString());
                //System.out.println(selfListString.toString());
            }
        }
    }

    @Override
	public int getCount() {
		// TODO Auto-generated method stub
		return selfListString.size();
	}

	@Override
	public HashMap<String, String> getItem(int position) {
		// TODO Auto-generated method stub
		return selfListString.get(position);
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
		//final HashMap<String, Object> map=selfList.get(position);

        final HashMap<String, String> mapString=selfListString.get(position);

		String type=(String)mapString.get("itemType");
		final String name=(String)mapString.get("title");
		final int pos=position;


		if (type.equals("newTrip")){
			view=mInflater.inflate(R.layout.aboutme_listitem2, null);
			TextView itemNameTextView=(TextView)view.findViewById(R.id.aboutme_itemSystem);
			itemNameTextView.setText(name);		
		}
		if (type.equals("plan")){
			view=mInflater.inflate(R.layout.aboutme_listitem, null);
			
			Button stateButton=(Button)view.findViewById(R.id.aboutme_buttonState);
			stateButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
                    mapString.put("itemState", "旅途中");
					selfListString.set(pos, mapString);
					notifyDataSetChanged();
				}
			});
			
			String itemPlace=(String)mapString.get("siteIDs");
            Vector<String> itemPlaceVector = new Vector<String>();
            try {
                itemPlaceVector = JSONHelper.getJSONHelperInstance().convertToArray2(itemPlace);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            StringBuffer stringBuffer = new StringBuffer();
            if(itemPlaceVector!=null){
                for(int i=0; i<itemPlaceVector.size(); i++){
                    stringBuffer.append(itemPlaceVector.get(i));
                    if(i!=itemPlaceVector.size()-1) {
                        stringBuffer.append(";\n");
                    }
                }
                itemPlace = stringBuffer.toString();
            }else{
                itemPlace = "";
            }

			String itemTimeFrom=(String)mapString.get("startTime");
			String itemTimeTo=(String)mapString.get("endTime");
			String itemWant=(String)mapString.get("describe");

			//String itemMore=(String)mapString.get("participants");
            String itemState;
            if(mapString.get("isDone").equals("false")){
                itemState=(String)mapString.get("招募中");
            }else{
                itemState=(String)mapString.get("旅途中");
            }
			//String itemAttention=(String)mapString.get("participants");
			String itemJoin=(String)mapString.get("participants");
            Vector<String> itemJoinVector = new Vector<String>();
            try {
                itemJoinVector = JSONHelper.getJSONHelperInstance().convertToArray2(itemJoin);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            stringBuffer = new StringBuffer();
            if(itemJoinVector!=null){
                for(int i=0; i<itemJoinVector.size(); i++){
                    stringBuffer.append(itemJoinVector.get(i));
                    if(i!=itemJoinVector.size()-1){
                        stringBuffer.append(";");
                    }
                }
                itemJoin = stringBuffer.toString();
            }else{
                itemJoin = "";
            }

            String itemTime=itemTimeFrom.equals(itemTimeTo)?itemTimeFrom+" ":itemTimeFrom+"\n到\n"+itemTimeTo+"";
			
			ImageView imageView=(ImageView)view.findViewById(R.id.aboutme_itemImage);
			imageView.setImageResource(R.drawable.me);
			
			TextView itemNameTextView=(TextView)view.findViewById(R.id.aboutme_itemName);
			itemNameTextView.setText(/*itemPlace+*/name);
			
			TextView itemPlaceTextView=(TextView)view.findViewById(R.id.aboutme_itemPlaceCon);
			itemPlaceTextView.setText(itemPlace);
			
			TextView itemTimeTextView=(TextView)view.findViewById(R.id.aboutme_itemTimeCon);
			itemTimeTextView.setText(itemTime);
			
			TextView itemPeopleTextView=(TextView)view.findViewById(R.id.aboutme_itemPeopleCon);
			itemPeopleTextView.setText(itemWant);
			
			//TextView itemMoreTextView=(TextView)view.findViewById(R.id.aboutme_itemMoreCon);
			//itemMoreTextView.setText(itemMore);
			
			TextView itemStateTextView=(TextView)view.findViewById(R.id.aboutme_itemStateCon);
			itemStateTextView.setText(itemState);
			
			//TextView itemAttentionTextView=(TextView)view.findViewById(R.id.aboutme_attentionPeople);
			//itemAttentionTextView.setText("关注的人: "+itemAttention);
			
			TextView itemJoinTextView=(TextView)view.findViewById(R.id.aboutme_joinPeople);
			itemJoinTextView.setText("加入的人: "+itemJoin);
			
		}

        else if (type.equals("record")){

            view=mInflater.inflate(R.layout.record_listitem, null);
            //view=mInflater.inflate(R.layout.aboutme_listitem, null);

            String itemName=(String)mapString.get("title");
            String itemPlace=(String)mapString.get("siteIDs");
            Vector<String> itemPlaceVector = null;
            try {
                itemPlaceVector = JSONHelper.getJSONHelperInstance().convertToArray2(itemPlace);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            StringBuffer stringBuffer = new StringBuffer();
            if(itemPlaceVector!=null){
                for(int i=0; i<itemPlaceVector.size(); i++){
                    stringBuffer.append(itemPlaceVector.get(i));
                    if(i!=itemPlaceVector.size()-1){
                        stringBuffer.append(";\n");
                    }
                }
                itemPlace = stringBuffer.toString();
            }else{
                itemPlace = "";
            }
            String itemTimeFrom=(String)mapString.get("startTime");
            String itemState=(String)mapString.get("endTime");
            String itemParticipates=(String)mapString.get("userIDs");
            Vector<String> itemJoinVector = null;
            try {
                itemJoinVector = JSONHelper.getJSONHelperInstance().convertToArray2(itemParticipates);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            stringBuffer = new StringBuffer();
            if(itemJoinVector!=null){
                for(int i=0; i<itemJoinVector.size(); i++){
                    stringBuffer.append(itemJoinVector.get(i));
                    if(i!=itemJoinVector.size()-1){
                        stringBuffer.append(";");
                    }
                }
                itemParticipates = stringBuffer.toString();
            }else{
                itemParticipates = "";
            }


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
        else if (type.equals("plan")){
             view = null;

        }
		
		return view;
	}

}
