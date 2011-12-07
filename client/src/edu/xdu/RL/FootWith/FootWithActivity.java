package edu.xdu.RL.FootWith;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;

public class FootWithActivity extends ActivityGroup {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        TabHost mainTabHost=(TabHost)findViewById(R.id.tabhost);
        mainTabHost.setup(this.getLocalActivityManager());
        
        TabHost.TabSpec spec=mainTabHost.newTabSpec("tab1");  
        spec.setContent(new Intent(this,home.class));  
        spec.setIndicator("",getResources().getDrawable(R.drawable.home));
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab2");  
        spec.setContent(new Intent(this,home.class));  
        spec.setIndicator("",getResources().getDrawable(R.drawable.news));  
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab3");  
        spec.setContent(new Intent(this,home.class));  
        spec.setIndicator("",getResources().getDrawable(R.drawable.me));  
        mainTabHost.addTab(spec); 
        
        TabWidget tabWidget=mainTabHost.getTabWidget();
        
        for (int i=0;i<tabWidget.getChildCount();i++){
        	View view = tabWidget.getChildTabViewAt(i);
        }
        mainTabHost.setCurrentTab(0);  
        
    }
}