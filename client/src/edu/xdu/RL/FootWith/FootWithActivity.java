package edu.xdu.RL.FootWith;

import android.R.anim;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

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
        spec.setIndicator("首页",getResources().getDrawable(R.drawable.home));
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab2");  
        spec.setContent(new Intent(this,Messages.class));  
        spec.setIndicator("通知",getResources().getDrawable(R.drawable.news));  
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab3");  
        spec.setContent(new Intent(this,AboutMe.class));  
        spec.setIndicator("我",getResources().getDrawable(R.drawable.me));  
        mainTabHost.addTab(spec); 
        
        TabWidget tabWidget=mainTabHost.getTabWidget();
        
        for (int i=0;i<tabWidget.getChildCount();i++){
        	ImageView iv = (ImageView)tabWidget.getChildTabViewAt(i).findViewById(android.R.id.icon);
        	iv.setPadding(10, 0, 10, 25);
        	TextView tv=(TextView)tabWidget.getChildTabViewAt(i).findViewById(android.R.id.title);
        	tv.setPadding(0, 0, 0, 5);
        }
        mainTabHost.setCurrentTab(0);  
        
    }
}