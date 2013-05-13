package edu.thu.cslab.footwith.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import edu.thu.cslab.footwith.client.helper.Myself;
import edu.thu.cslab.footwith.client.helper.MyselfAdapter;
import edu.thu.cslab.footwith.client.helper.MyselfNetwork;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;

//import edu.xdu.RL.FootWith.helper.ServerConnector;

public class AboutMe extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutme);
        
        ListView selfList=(ListView)findViewById(R.id.listView_AboutMe);
        
        ArrayList<HashMap<String, String>> listItem=new ArrayList<HashMap<String,String>>();
        
        final MyselfNetwork self=new MyselfNetwork();
        self.requestList();
        listItem=self.getList();
        
        final MyselfAdapter selfAdapter=new MyselfAdapter(AboutMe.this, listItem);
        //final MyselfAdapter selfAdapter=new MyselfAdapter(AboutMe.this);
        selfList.setAdapter(selfAdapter);
        
        
        
        selfList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, String> map=(HashMap<String, String>)adapterView.getAdapter().getItem(position);
				
				String type=(String)map.get("itemType");
				
				if (type.equals("newTrip")){
                    Intent intent = new Intent(arg1.getContext(), AddPlan.class);
                    arg1.getContext().startActivity(intent);
//               		LayoutInflater inflater=(LayoutInflater)AboutMe.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            		final RelativeLayout layout=(RelativeLayout)inflater.inflate(R.layout.aboutme_newtrip, null);
//            		AlertDialog.Builder builder=new AlertDialog.Builder(AboutMe.this);
//            		builder.setTitle("编辑您的旅程").setView(layout);
//            		builder.setPositiveButton("发布", new DialogInterface.OnClickListener() {
//            			private HashMap<String, Object> newMap=new HashMap<String, Object>();
//
//            			private ArrayList<NameValuePair> getParams(){
//            				ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
//
//            				EditText editText=(EditText)layout.findViewById(R.id.aboutme_clickPlaceCon);
//            				String values=editText.getText().toString();
//            				if (values.trim().length()!=0){
//            					params.add(new BasicNameValuePair("Place", values));
//            					newMap.put("itemPlace", values);
//            				}else{
//            					params.add(new BasicNameValuePair("judge", "您没说去哪呀？"));
//            					return params;
//            				}
//            				editText=(EditText)layout.findViewById(R.id.aboutme_clickTimeCon);
//            				values=editText.getText().toString();
//            				int from=Integer.valueOf(values);
//            				editText=(EditText)layout.findViewById(R.id.aboutme_clickTimeConTo);
//            				values=editText.getText().toString();
//            				int to =Integer.valueOf(values);
//
//            				if (from<1 || from>12 || to<1 || to>12 || from >to){
//            					params.add(new BasicNameValuePair("judge","非法的日期"));
//            					return params;
//            				}else {
//            					params.add(new BasicNameValuePair("Time_Start", String.valueOf(from)));
//            					newMap.put("itemTimeFrom", String.valueOf(from));
//            					params.add(new BasicNameValuePair("Time_End", values));
//            					newMap.put("itemTimeTo",values);
//            				}
//            				editText=(EditText)layout.findViewById(R.id.aboutme_clickPeopleCon);
//            				values=editText.getText().toString();
//            				if (values.trim().length()!=0){
//            					params.add(new BasicNameValuePair("Message", values));
//            					newMap.put("itemWant", values);
//            				}else{
//            					params.add(new BasicNameValuePair("judge", "请输入想寻觅的旅伴描述"));
//            					return params;
//            				}
//            				editText=(EditText)layout.findViewById(R.id.aboutme_clickMoreCon);
//            				values=editText.getText().toString();
//            				params.add(new BasicNameValuePair("MoreInfo",values));
//            				newMap.put("itemMore", values);
//
//            				params.add(new BasicNameValuePair("judge", "true"));
//
//            				return params;
//            			}
//
//            			@Override
//						public void onClick(DialogInterface arg0, int arg1) {
//							// TODO Auto-generated method stub
//            				ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
//            				params=getParams();
//            				String judge=params.get(params.size()-1).getValue().toString();
//
//            				if (!judge.equals("true")){
//            					Toast.makeText(AboutMe.this, judge, Toast.LENGTH_SHORT);
//            				}else{
//            					ServerConnector connector=new ServerConnector();
//            					String result=connector.newTripRequest(params);
//            					if (result.equals("Successful")){
//            						newMap.put("itemName", "之行计划");
//            						newMap.put("itemType", "trip");
//            						newMap.put("itemState", "招募中");
//            						self.add(newMap);
//            						selfAdapter.notifyDataSetChanged();
//            					}else {
//            						Toast.makeText(AboutMe.this, result, Toast.LENGTH_SHORT);
//            					}
//            				}
//						}
//            		});
//            		builder.setNegativeButton("算了", new DialogInterface.OnClickListener() {
//
//						@Override
//						public void onClick(DialogInterface arg0, int arg1) {
//							// TODO Auto-generated method stub
//
//						}
//					});
//            		builder.show();
				}
				
				if (type.equals("plan")){
					
				}
                if (type.equals("record")){
                     //arg1.getContext().startActivity(new Intent(arg1.getContext(), Record.class));
                    Intent intent = new Intent(arg1.getContext(), Record_Tab.class);
                    Bundle extras = new Bundle();
                    extras.putSerializable("map", map);
                    intent.putExtras(extras);
                    /*
                    String []keys = (String[]) map.keySet().toArray();
                    for(String key:keys){
                        intent.putExtra(key, map.get(key));
                    }
                    */
                    arg1.getContext().startActivity(intent);
                }
				
			}
		});
        
	}
}
