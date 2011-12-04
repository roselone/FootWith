package edu.xdu.RL.FootWith;

import edu.xdu.RL.FootWith.helper.intentHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class FootWithActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        TabHost mainTabHost=(TabHost)findViewById(R.id.tabhost);
        mainTabHost.setup();
        
        TabHost.TabSpec spec=mainTabHost.newTabSpec("tab1");  
        spec.setContent(R.id.tab1);  
        spec.setIndicator("",getResources().getDrawable(R.drawable.home));
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab2");  
        spec.setContent(R.id.tab2);  
        spec.setIndicator("",getResources().getDrawable(R.drawable.news));  
        mainTabHost.addTab(spec);  
          
        spec=mainTabHost.newTabSpec("tab3");  
        spec.setContent(R.id.tab3);  
        spec.setIndicator("",getResources().getDrawable(R.drawable.me));  
        mainTabHost.addTab(spec); 
        
        TabWidget tabWidget=mainTabHost.getTabWidget();
        
        for (int i=0;i<tabWidget.getChildCount();i++){
        	View view = tabWidget.getChildTabViewAt(i);
        }
        mainTabHost.setCurrentTab(0);  
        
    }
}