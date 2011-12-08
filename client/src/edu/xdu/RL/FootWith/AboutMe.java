package edu.xdu.RL.FootWith;

import java.util.ArrayList;
import java.util.HashMap;

import edu.xdu.RL.FootWith.helper.Myself;
import edu.xdu.RL.FootWith.helper.MyselfAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AboutMe extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutme);
        
        ListView selfList=(ListView)findViewById(R.id.listView_AboutMe);
        
        ArrayList<HashMap<String, Object>> listItem=new ArrayList<HashMap<String,Object>>();
        
        final Myself self=new Myself();
        self.requestList();
        listItem=self.getList();
        
        final MyselfAdapter selfAdapter=new MyselfAdapter(AboutMe.this, listItem);
        
        selfList.setAdapter(selfAdapter);
        
        selfList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, Object> map=(HashMap<String, Object>)adapterView.getAdapter().getItem(position);
				
				String type=(String)map.get("itemType");
				
				if (type.equals("newTrip")){
					
				}
				
			}
		});
        
	}
}
