package edu.xdu.RL.FootWith;

import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.helper.Trip;
import edu.xdu.RL.FootWith.helper.TripAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class home extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        ListView homeList=(ListView)findViewById(R.id.listView_Home);
        
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();

        final Trip trip=new Trip();
        trip.requestTrip();
        listItem=trip.getTrips();
        
        final TripAdapter tripAdapter=new TripAdapter(home.this, listItem);
        
        homeList.setAdapter(tripAdapter);
        homeList.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> adapterView, View itemView, int position,
					long arg3) {
        		final HashMap<String, Object> map=(HashMap<String, Object>)adapterView.getAdapter().getItem(position);
        		
        		String type=(String)map.get("itemType");
        		if (type.equals("systemInfo")){
        			
        		}
        		
        		if (type.equals("trip")){
        			LayoutInflater inflater=(LayoutInflater)home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            		final LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.home_clickitem, null);
            		final String name=(String)map.get("itemName");
    				final String content=(String)map.get("itemContent");
    				
    				TextView textView=(TextView)layout.findViewById(R.id.homeClickItemText);
    				textView.setText(content);

    				new AlertDialog.Builder(home.this)
					.setTitle(name).setView(layout)
					.setPositiveButton("勾搭一下", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
                            Toast.makeText(home.this,  
                                    "勾搭成功！", Toast.LENGTH_SHORT)  
                                    .show();  
						}
					}).setNegativeButton("算了", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
                            Toast.makeText(home.this,  
                                    name+"伤心了...", Toast.LENGTH_SHORT)  
                                    .show();  
						}
					}).show();
        		}
        		
        		if (type.equals("footWith")){
            		LayoutInflater inflater=(LayoutInflater)home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            		final LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.home_clickitem, null);
            		
    				final String content=(String)map.get("itemContent");
    				final String title="和他(她)们说..";
    				final String subcontent=(String)map.get("itemSubContent");
    				final int pos=position;
    				TextView textView=(TextView)layout.findViewById(R.id.homeClickItemText);
    				textView.setText(subcontent);
    				
    				new AlertDialog.Builder(home.this)
					.setTitle(title).setView(layout)
					.setPositiveButton("发送", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							EditText inputEditText=(EditText)layout.findViewById(R.id.homeClickItemEdit);
							String inputText=inputEditText.getText().toString();
							String newContent;
							if (content!=null){
								newContent=content+"\n"+login.userID+":"+inputText;
							}else {
								newContent=login.userID+":"+inputText;
							}
							map.put("itemContent", newContent);
							trip.modify(pos, map);
							tripAdapter.notifyDataSetChanged();
							
                            Toast.makeText(home.this,  
                            		"发送成功", Toast.LENGTH_SHORT)  
                                    .show();  
						}
					}).setNegativeButton("算了", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					}).show();
        		}
			}
        	
		});
        
	}
	
}
