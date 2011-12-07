package edu.xdu.RL.FootWith;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.helper.Trip;
import edu.xdu.RL.FootWith.helper.TripAdapter;
import edu.xdu.RL.FootWith.helper.intentHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

        Trip trip=new Trip();
        trip.requestTrip();
        listItem=trip.getTrips();
        
        TripAdapter tripAdapter=new TripAdapter(home.this, listItem);
        
        homeList.setAdapter(tripAdapter);
        homeList.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> adapterView, View itemView, int position,
					long arg3) {
				// TODO Auto-generated method stub
        		LayoutInflater inflater=(LayoutInflater)home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        		final LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.home_clickitem, null);
				
        		HashMap<String, Object> map=(HashMap<String, Object>)adapterView.getAdapter().getItem(position);
				final String name=(String)map.get("itemName");
				final String content=(String)map.get("itemContent");
				String sex=(String)map.get("sex");
				final String title="和他(她)们说..";
				final String conString="你想知道沿途的风景么？\n你想了解当地的风情么？";
				
				TextView textView=(TextView)layout.findViewById(R.id.homeClickItemText);
				textView.setText(sex.equals("none")?conString:content);
				
				
				new AlertDialog.Builder(home.this)
					.setTitle(sex.equals("none")?title:name).setView(layout)
					.setPositiveButton("发送", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
                            Toast.makeText(home.this,  
                                    "发送成功！", Toast.LENGTH_SHORT)  
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
        	
		});
        
	}
	
}
